package nc.ccas.gasel.services.reports;

import static java.util.Collections.singletonMap;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import nc.ccas.gasel.longAction.LongAction;
import nc.ccas.gasel.longAction.LongActions;
import nc.ccas.gasel.longAction.ReportHelper;
import nc.ccas.gasel.services.OutputService;

import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.util.ContentType;
import org.apache.tapestry.web.WebContext;

public class ReportService extends OutputService {

	public static final String NAME = "reports";

	public static final String FORMAT_PARAM = "format";

	public static void invoke(IRequestCycle cycle, String view, String key,
			Object value) {
		invoke(cycle, NAME, view, singletonMap(key, value));
	}

	public static void invoke(IRequestCycle cycle, String view, String key,
			Object value, String format) {
		invokeWithParameters(cycle, view, format, singletonMap(key, value));
	}

	public static void invokeWithParameters(IRequestCycle cycle, String view,
			String format, Map<String, Object> reportParameters) {
		if (!ReportHelper.supportsFormat(format)) {
			throw new IllegalArgumentException("Unsupported format: " + format);
		}
		Map<String, Object> parameters = new TreeMap<>(reportParameters);
		parameters.put(FORMAT_PARAM, format);
		invoke(cycle, NAME, view, parameters);

	}

	public static void invoke(IRequestCycle cycle, String view,
			Map<String, ?> data) {
		invoke(cycle, NAME, view, data);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getExtension(IRequestCycle cycle, Map<String, ?> parameters) {
		if (parameters.containsKey(FORMAT_PARAM)) {
			return (String) parameters.get(FORMAT_PARAM);
		}
		return "pdf";
	}

	@Override
	public ContentType getContentType(IRequestCycle cycle,
			Map<String, ?> parameters) {
		String ext = getExtension(cycle, parameters);
		return ReportHelper.getReportSupport(ext).getContentType();
	}

	@Override
	public boolean doesViewExists(String viewName) {
		return true; // we don't have this info here
	}

	private class RenderReportAction implements LongAction {

		private String view;
		private Map<String, ?> parameters;
		private String extension;
		private Result result;

		RenderReportAction(String view, String extension,
				Map<String, ?> parameters) {
			this.view = view;
			this.extension = extension;
			this.parameters = parameters;
		}

		@Override
		public void run() {
			ReportHelper helper = new ReportHelper(context);
			result = helper.renderReport(view, extension, parameters);
		}

		@Override
		public String getTitle() {
			return "Rendu du rapport " + view;
		}

		@Override
		public List<StepProgress> getProgress() {
			return null;
		}

		@Override
		public Result getResult() {
			return result;
		}
	}

	@Override
	protected void render(OutputStream output, IRequestCycle cycle,
			String view, Map<String, ?> parameters) throws IOException {
		String actionId = LongActions.start(new RenderReportAction(view,
				getExtension(cycle, parameters), parameters));
		cycle.sendRedirect(cycle.getAbsoluteURL("/long-actions/" + actionId));
	}

	private WebContext context;

	public void setContext(WebContext context) {
		this.context = context;
	}

}
