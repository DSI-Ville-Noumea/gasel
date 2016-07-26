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

public class ReportHelper {

	private static String viewDir;

	public static void setViewDir(String viewDir) {
		ReportHelper.viewDir = viewDir;
	}

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

	public static class Context {
		private final Map<String, Object> parameters;
		private String view;
		private String extension;
		private ParametersValidation validation;
		private DataTransformation transformation;

		public Context(String view, String extension,
				ParametersValidation validation,
				DataTransformation transformation) {
			this.view = view;
			this.extension = extension;
			this.parameters = new TreeMap<>();
			this.validation = validation;
			this.transformation = transformation;
		}

		public String getView() {
			return view;
		}

		public Map<String, Object> getParameters() {
			return parameters;
		}

		public void putParameters(Map<String, ?> parameters) {
			this.parameters.putAll(parameters);
		}

		public void put(String key, Object value) {
			this.parameters.put(key, value);
		}

		public String getExtension() {
			return extension;
		}

		public ParametersValidation getValidation() {
			return validation;
		}

		public DataTransformation getTransformation() {
			return transformation;
		}
	}

	public ReportHelper() {
	}

	public LongAction.Result renderReport(final Context context) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		context.put("SUBREPORT_DIR", viewDir + "/");
		context.put(JRParameter.REPORT_LOCALE, Locale.FRANCE);

		try {
			renderReport(context, output);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		final byte[] bytes = output.toByteArray();

		return new LongAction.Result() {

			@Override
			public String getFileName() {
				return context.getView() + "." + context.getExtension();
			}

			@Override
			public String getContentType() {
				return getSupportForExtension(context.getExtension())
						.getContentType().getMimeType();
			}

			@Override
			public byte[] getBytes() {
				return bytes;
			}
		};
	}

	private void renderReport(Context context, OutputStream output)
			throws IOException {
		String viewPath = viewDir + "/" + context.getView() + ".jasper";

		ByteArrayOutputStream data = new ByteArrayOutputStream();
		InputStream viewAsStream = new FileInputStream(viewPath);

		Connection connection = null;
		try {
			// render

			if (context.getValidation() != null) {
				context.getValidation().validate(context.getParameters());
			}

			DataNode dataNode = (DataNode) Configuration
					.getSharedConfiguration().getDomain().getDataNodes()
					.iterator().next();
			connection = dataNode.getDataSource().getConnection();
			renderReport(context, viewAsStream, connection, data);

			DataTransformation transformation = context.getTransformation();
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

	private void safeClose(InputStream stream) {
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void renderReport(Context context, InputStream reportStream,
			Connection connection, ByteArrayOutputStream output)
			throws JRException {
		DefaultJasperReportsContext reportsContext = DefaultJasperReportsContext
				.getInstance();

		JRExporter exporter = getReportSupport(context.getExtension())
				.buildExporter(reportsContext);

		JasperFillManager jasperFillManager = JasperFillManager
				.getInstance(reportsContext);
		JasperPrint jasperPrint = jasperFillManager.fill(reportStream,
				context.getParameters(), connection);

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
