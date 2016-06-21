package nc.ccas.gasel.longAction;

import static nc.ccas.gasel.modelUtils.CommonQueries.unique;

import java.util.List;

import nc.ccas.gasel.BasePageDates;
import nc.ccas.gasel.BasePageLists;
import nc.ccas.gasel.BasePageSort;
import nc.ccas.gasel.BasePageSql;
import nc.ccas.gasel.model.core.Utilisateur;

import org.apache.cayenne.access.DataContext;
import org.apache.log4j.Logger;

public abstract class BaseLongAction implements LongAction {

	private final String title;

	private Result result;

	public BaseLongAction(String title, String userName) {
		this.title = title;

		helper = new ProgressHelper(title, Logger.getLogger(getClass()));

		context = DataContext.createDataContext();
		sql = new BasePageSql(context);

		this.user = unique(context, Utilisateur.class, "login", userName);
	}

	@Override
	public final String getTitle() {
		return title;
	}

	@Override
	public final List<StepProgress> getProgress() {
		return helper.getProgress();
	}

	@Override
	public final Result getResult() {
		return result;
	}

	protected final void setResult(Result result) {
		this.result = result;
	}

	// Tools

	protected static final BasePageDates dates = BasePageDates.INSTANCE;

	protected final BasePageSql sql;

	protected final BasePageLists lists = new BasePageLists();

	protected final DataContext context;

	protected final Utilisateur user;

	protected final ProgressHelper helper;

	protected final <T> BasePageSort<T> sort() {
		return new BasePageSort<T>();
	}

}
