package nc.ccas.gasel.services.reports;

import java.util.List;

import nc.ccas.gasel.longAction.LongAction;
import nc.ccas.gasel.longAction.LongActions;
import nc.ccas.gasel.longAction.ReportHelper;

import org.apache.tapestry.IRequestCycle;

public class RenderReportAction implements LongAction {

	public static void invoke(IRequestCycle cycle, ReportHelper.Context context) {
		if (!ReportHelper.supportsFormat(context.getExtension())) {
			throw new IllegalArgumentException("Unsupported format: "
					+ context.getExtension());
		}

		String actionId = LongActions.start(new RenderReportAction(context));
		cycle.sendRedirect(cycle.getAbsoluteURL("/long-actions/" + actionId));
	}

	private final ReportHelper.Context context;

	private Result result;

	RenderReportAction(ReportHelper.Context context) {
		this.context = context;
	}

	@Override
	public void run() {
		ReportHelper helper = new ReportHelper();
		result = helper.renderReport(this.context);
	}

	@Override
	public String getTitle() {
		return "Rendu du rapport " + context.getView();
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