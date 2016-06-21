package nc.ccas.gasel.longAction;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import nc.ccas.gasel.services.DataTransformation;
import nc.ccas.gasel.services.reports.ParametersValidation;
import nc.ccas.gasel.services.reports.ReportSupport;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.conf.Configuration;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tapestry.web.WebContext;

public class ReportHelper {

	private static Map<String, DataTransformation> transformations = new TreeMap<>();
	private static Map<String, ParametersValidation> validations = new TreeMap<>();

	private static Map<String, ReportSupport> formats = new TreeMap<>();
	static {
		formats.put("pdf", new ReportSupport("text/pdf", JRPdfExporter.class));

		// Formats OpenDocument
		formats.put("odt", new ReportSupport(
				"application/vnd.oasis.opendocument.text", JROdtExporter.class));
		formats.put("ods", new ReportSupport(
				"application/vnd.oasis.opendocument.spreadsheet",
				JROdsExporter.class));

		// Formats ms office
		formats.put("docx", new ReportSupport("application/msword",
				JRDocxExporter.class));
		formats.put("xlsx", new ReportSupport("application/msexcel",
				JRXlsxExporter.class));
		formats.put("xls", new ReportSupport("application/msexcel",
				JRXlsExporter.class));
	}

	/**
	 * 
	 * @param viewName
	 *            viewName + ":" + format. Ex: <code>edition-bons:pdf</code>.
	 * @param dataTransformation
	 *            The transformation to apply.
	 */
	public synchronized static void registerTransformation(String viewName,
			DataTransformation dataTransformation) {
		transformations.put(viewName, dataTransformation);
	}

	/**
	 * 
	 * @param viewName
	 *            viewName. Ex: <code>edition-bons</code>.
	 * @param validation
	 *            The validation to apply.
	 */
	public synchronized static void registerValidation(String viewName,
			ParametersValidation validation) {
		validations.put(viewName, validation);
	}

	public static ReportSupport getSupportForExtension(String ext) {
		return formats.get(ext);
	}

	public static Set<String> getSupportedFormats() {
		return new TreeSet<>(formats.keySet());
	}

	public static boolean supportsFormat(String format) {
		return formats.containsKey(format);
	}

	public static ReportSupport getReportSupport(String ext) {
		if (!formats.containsKey(ext)) {
			throw new IllegalArgumentException("No renderer for extension \""
					+ ext + "\"");
		}
		return getSupportForExtension(ext);
	}

	private final WebContext context;

	public ReportHelper(WebContext context) {
		this.context = context;
	}

	public LongAction.Result renderReport(final String reportName,
			final String extension, Map<String, ?> parameters) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {
			renderReport(context, reportName, extension, parameters, output);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		final byte[] bytes = output.toByteArray();

		return new LongAction.Result() {

			@Override
			public String getFileName() {
				return reportName + "." + extension;
			}

			@Override
			public String getContentType() {
				return getSupportForExtension(extension).getContentType()
						.getMimeType();
			}

			@Override
			public byte[] getBytes() {
				return bytes;
			}
		};
	}

	private void renderReport(WebContext context, String view, String ext,
			Map<String, ?> parameters, OutputStream output) throws IOException {
		String viewDir = context.getRealPath("/WEB-INF/reports");
		String viewPath = viewDir + "/" + view + ".jasper";

		ByteArrayOutputStream data = new ByteArrayOutputStream();
		InputStream viewAsStream = new FileInputStream(viewPath);

		Connection connection = null;
		try {
			// render

			Map<String, Object> params = convertMap(parameters, viewDir);
			validateParameters(view, params);

			DataNode dataNode = (DataNode) Configuration
					.getSharedConfiguration().getDomain().getDataNodes()
					.iterator().next();
			connection = dataNode.getDataSource().getConnection();
			renderReport(ext, parameters, viewAsStream, connection, params,
					data);

			DataTransformation transformation = transformations.get(view + ":"
					+ ext);
			if (transformation == null) {
				transformation = DataTransformation.IDENTITY;
			}
			transformation.transformData(data.toByteArray(), output);

		} catch (JRException | SQLException e) {
			throw new RuntimeException(e);
		} finally {
			safeClose(connection);
			safeClose(viewAsStream);
		}
	}

	private void validateParameters(String view, Map<String, Object> parameters) {
		if (validations.containsKey(view)) {
			validations.get(view).validate(parameters);
		}
	}

	private void safeClose(InputStream stream) {
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Map<String, Object> convertMap(Map<String, ?> map, String viewDir) {
		Map<String, Object> newMap = new TreeMap<>(map);
		if (!newMap.containsKey("SUBREPORT_DIR")) {
			newMap.put("SUBREPORT_DIR", viewDir + "/");
		}
		if (!newMap.containsKey(JRParameter.REPORT_LOCALE)) {
			newMap.put(JRParameter.REPORT_LOCALE, Locale.FRANCE);
		}
		return newMap;
	}

	private void renderReport(String extension, Map<String, ?> parameters,
			InputStream reportStream, Connection connection,
			Map<String, Object> reportParameters, ByteArrayOutputStream output)
			throws JRException {
		DefaultJasperReportsContext reportsContext = DefaultJasperReportsContext
				.getInstance();

		JRExporter exporter = getReportSupport(extension).buildExporter(
				reportsContext);

		JasperFillManager jasperFillManager = JasperFillManager
				.getInstance(reportsContext);
		JasperPrint jasperPrint = jasperFillManager.fill(reportStream,
				reportParameters, connection);

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
		exporter.exportReport();
	}

	private void safeClose(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
