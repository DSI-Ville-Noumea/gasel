package nc.ccas.gasel.pages.compta;

import java.util.Date;
import java.util.List;
import java.util.Map;

import nc.ccas.gasel.BasePage;
import nc.ccas.gasel.longAction.EditerBons;
import nc.ccas.gasel.model.budget.Imputation;
import nc.ccas.gasel.model.budget.NatureAide;
import nc.ccas.gasel.model.core.enums.TypePublic;
import nc.ccas.gasel.reports.ReportUtils;

import org.apache.cayenne.DataRow;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.Persist;

public abstract class EditionBons extends BasePage {

	private static final String QUERY = "SELECT	na.imputation_id AS imputation_id,\n" //
			+ "	na.id AS nature_id,\n" //
			+ "	a.public_id AS public_id,\n" //
			+ "	COUNT(*) AS count_aides,\n" //
			+ "	SUM(count_aide_bons_a_editer(a.id,$debut,$fin)\n" //
			+ "	) AS count_bons,\n" //
			+ "	SUM(a.montant\n" //
			+ "	   * count_aide_bons_a_editer(a.id,$debut,$fin)\n" //
			+ "	   / a.quantite_mensuelle\n" //
			+ "	) AS montant\n" //
			+ "\n" //
			+ "  FROM aide a\n" //
			+ "  JOIN nature_aide na ON (na.id = a.nature_id)\n" //
			+ "  JOIN imputation i ON (i.id = na.imputation_id)\n" //
			+ "  JOIN type_public tp ON (tp.id = a.public_id)\n" //
			+ " WHERE a.statut_id IN "
			+ EditerBons.FREQS //
			+ "   AND (debut <= $fin OR debut IS NULL)"
			+ "   AND (fin >= $debut OR fin   IS NULL)"
			+ "   {{filtre-imputation}}"
			+ "   AND count_aide_bons_a_editer(a.id,$debut,$fin) > 0\n" //
			+ "GROUP BY na.imputation_id, na.id, a.public_id,\n" //
			+ "         i.libelle, na.libelle, tp.libelle\n" //
			+ "ORDER BY i.libelle, na.libelle, tp.libelle";

	@Persist("workflow")
	public abstract Date getMois();

	@Persist("workflow")
	public abstract Imputation getImputation();

	private List<Map<String, Object>> _tableau;

	public List<Map<String, Object>> getTableau() {
		if (_tableau == null) {
			List<DataRow> rows = sql.query().rows(
					EditerBons.filtreImputation(QUERY,
							sql.idOf(getImputation())), sql.params() //
							.set("debut", dates.debutMois(getMois())) //
							.set("fin", dates.finMois(getMois())) //
					);
			_tableau = sql.translator(rows) //
					.translate("imputation_id", Imputation.class) //
					.translate("nature_id", NatureAide.class) //
					.translate("public_id", TypePublic.class) //
			.result;
		}
		return _tableau;
	}

	public Map<String, Object> getTotal() {
		return ReportUtils.cumule(getTableau());
	}

	@Override
	protected void cleanupAfterRender(IRequestCycle cycle) {
		super.cleanupAfterRender(cycle);
		_tableau = null;
	}

	/*
	 * Edition
	 */

	public void editer(IRequestCycle cycle) {
		startLongAction(cycle, new EditerBons(getLogin().getUserName(),
				getMois(), sql.idOf(getImputation()), reportHelper()));
	}
}
