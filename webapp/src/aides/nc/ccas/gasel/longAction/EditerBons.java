package nc.ccas.gasel.longAction;

import static com.asystan.common.cayenne_new.QueryFactory.createAnd;
import static com.asystan.common.cayenne_new.QueryFactory.createBetween;
import static com.asystan.common.cayenne_new.QueryFactory.createEquals;
import static java.util.Collections.singletonMap;
import static nc.ccas.gasel.longAction.ReportHelper.registerTransformation;
import static nc.ccas.gasel.longAction.ReportHelper.registerValidation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import nc.ccas.gasel.BasePageSort;
import nc.ccas.gasel.model.aides.Aide;
import nc.ccas.gasel.model.aides.Bon;
import nc.ccas.gasel.model.aides.EtatBon;
import nc.ccas.gasel.model.aides.StatutAide;
import nc.ccas.gasel.modelUtils.CayenneUtils;
import nc.ccas.gasel.modelUtils.CommonQueries;
import nc.ccas.gasel.services.DataTransformation;
import nc.ccas.gasel.services.reports.ParametersValidation;

import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.query.SQLTemplate;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class EditerBons extends BaseLongAction {

	static {
		// We must rotate the result for proper printing.
		registerTransformation("edition-bons:pdf", new DataTransformation() {
			@Override
			public void transformData(byte[] data, OutputStream output)
					throws IOException {
				PDDocument doc = PDDocument
						.load(new ByteArrayInputStream(data));
				for (Object o : doc.getDocumentCatalog().getAllPages()) {
					PDPage page = (PDPage) o;

					page.setRotation(180);
				}

				try {
					doc.save(output);
				} catch (COSVisitorException e) {
					throw new RuntimeException(e);
				} finally {
					doc.close();
				}
			}
		});

		// We must validate here since Jasper reports have no validation
		// AND $X{IN, ...} don't handle empty list correctly : when the
		// list is empty, we expect "x IN () == false" but it's true,
		// thus renders every single line of the table...
		//
		// Here, we just try to fallback to an intuitive behaviour :
		// render nothing!
		//
		registerValidation("edition-bons", new ParametersValidation() {
			@Override
			public void validate(Map<String, Object> parameters)
					throws IllegalArgumentException {
				Collection<?> bons = (Collection<?>) parameters.get("BONS_ID");
				if (bons == null || bons.isEmpty()) {
					parameters.put("BONS_ID", Collections.singleton(-1));
				}
			}
		});
	}

	public static final String FREQS = "(" + StatutAide.PLURIMENSUELLE + ","
			+ StatutAide.OCCASIONNELLE + ")";

	private static final String AIDES_AVEC_BON_A_CREER = "SELECT a.*"
			+ "  FROM aide a" //
			+ "  JOIN nature_aide na ON (na.id = a.nature_id)\n" //
			+ " WHERE statut_id IN " + FREQS
			+ "   AND (debut <= $fin OR debut IS NULL)"
			+ "   AND (fin >= $debut OR fin   IS NULL)"
			+ "   {{filtre-imputation}}"
			+ "   AND count_aide_bons_a_editer(a.id,$debut,$fin) > 0";

	public static SQLTemplate filtreImputation(String query,
			Integer imputationId) {
		String condition;
		if (imputationId == null) {
			condition = "";
		} else {
			condition = "AND na.imputation_id = " + imputationId;
		}
		return new SQLTemplate(Aide.class, //
				query.replace("{{filtre-imputation}}", condition));
	}

	private final Date mois;
	private final Integer imputationId;
	private final ReportHelper reportHelper;

	public EditerBons(String userName, Date mois, Integer imputationId,
			ReportHelper reportHelper) {
		super("Génération des bons de " + dates.formatMoisTexte.format(mois),
				userName);
		this.mois = mois;
		this.imputationId = imputationId;
		this.reportHelper = reportHelper;
	}

	@Override
	public void run() {
		DataContext.bindThreadDataContext(context);

		Date debut = dates.debutMois(mois);
		Date fin = dates.finMois(mois);

		// ------------------------------------------------------------
		helper.start("Recherche des aides avec bons à créer");
		List<Aide> aides = sql.query().objects(
				filtreImputation(AIDES_AVEC_BON_A_CREER, imputationId),
				sql.params().set("debut", debut).set("fin", fin));

		CommonQueries.prefetch(context, aides, "dossier", "dossier.dossier",
				"dossier.dossier.chefFamille");

		aides = new BasePageSort<>(aides) //
				.by("dossier.dossier.chefFamille.nom") //
				.by("dossier.dossier.chefFamille.prenom") //
				.by(new Comparator<Aide>() {
					public int compare(Aide o1, Aide o2) {
						return sql.idOf(o1.getDossier()).compareTo(
								sql.idOf(o2.getDossier()));
					}
				}).results();

		// ------------------------------------------------------------
		helper.start("Création des bons manquants", aides.size() + 1);
		// +1 car phase de commit
		for (Aide aide : aides) {
			aide.creerBons(debut, user, aide.getDossier().getDossier()
					.getChefFamille());
			helper.progressBy(1);
		}
		commit();

		// ------------------------------------------------------------
		helper.start("Récupération de la liste définitive des bons");
		List<Bon> bons = sql.query(
				Bon.class,
				createAnd(
						createBetween("debut", dates.debutMois(mois),
								dates.finMois(mois)),
						createEquals("etat", EtatBon.CREE)));

		if (bons.isEmpty()) {
			throw new RuntimeException("Aucun bon à éditer.");
		}
		if (bons.size() > 5000) {
			throw new RuntimeException("Trop de bons à éditer (" + bons.size()
					+ ").");
		}

		// ------------------------------------------------------------
		helper.start("Rendu du rapport");
		List<Integer> bonIds = CayenneUtils.collectIds(bons);
		setResult(reportHelper.renderReport("edition-bons", "pdf",
				singletonMap("BONS_ID", bonIds)));

		// ------------------------------------------------------------
		helper.start("Marquer les bons comme édités", bons.size() + 1);
		// +1 car phase de commit

		Date date = new Date();
		for (Bon bon : bons) {
			bon.setEtat(EtatBon.EDITE);
			bon.modified(user, date);
			helper.progressBy(1);
		}
		commit();

		// ------------------------------------------------------------
		helper.finished();
	};

	private void commit() {
		try {
			context.commitChanges();
		} catch (Exception ex) {
			context.rollbackChanges();
			throw new RuntimeException(ex);
		}
	}

}
