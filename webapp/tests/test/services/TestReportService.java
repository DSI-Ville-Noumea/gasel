package test.services;

import static nc.ccas.gasel.modelUtils.CayenneUtils.collectIds;
import static nc.ccas.gasel.modelUtils.CommonQueries.select;
import static nc.ccas.gasel.modelUtils.CommonQueries.unique;
import static org.apache.cayenne.DataObjectUtils.intPKForObject;
import static org.apache.cayenne.access.DataContext.createDataContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nc.ccas.gasel.longAction.LongAction;
import nc.ccas.gasel.longAction.ReportHelper;
import nc.ccas.gasel.longAction.ReportHelper.Context;
import nc.ccas.gasel.model.aides.Arrete;
import nc.ccas.gasel.model.aides.Bon;

import org.apache.tapestry.TestBase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestReportService extends TestBase {

	public static void main(String[] args) throws Exception {
		TestReportService s = new TestReportService();
		s.testRenderBon();
	}

	private static final String ARRETE = "2014/29";

	// private static final String ARRETE = "2015/test/001";

	@BeforeTest
	public void setupReportHelper() {
		ReportHelper.setViewDir(new File("src/main/webapp/WEB-INF/reports")
				.getAbsolutePath());
	}

	@Test
	public void testRenderBon() throws Exception {
		doReportTest("edition-bons", "pdf", "BONS_ID", Collections.emptyList());

		List<Bon> values = select(Bon.class, "numero like '201603000%'");
		List<Bon> slice = new ArrayList<>();
		for (int i = 0; i < 4 && i < values.size(); i++) {
			slice.add(values.get(i));
		}
		assertNotEquals(0, slice.size());
		doReportTest("edition-bons", "pdf", "BONS_ID", collectIds(slice));
	}

	@Test
	public void testArreteAlimentation() throws Exception {
		testArrete("arrete-alimentation");
	}

	@Test
	public void testArreteSolidarite() throws Exception {
		testArrete("arrete-solidarite");
	}

	@Test
	public void testArreteAvance() throws Exception {
		testArrete("arrete-avance");
	}

	@Test
	public void testArreteOM() throws Exception {
		testArrete("arrete-om");
	}

	@Test
	public void testArreteCde() throws Exception {
		testArrete("arrete-cde");
	}

	@Test
	public void testArreteEEC() throws Exception {
		testArrete("arrete-eec", "94740");
	}

	@Test
	public void testArreteUrgences() throws Exception {
		testArrete("arrete-urgences");
	}

	@Test
	public void testArreteGenerique() throws Exception {
		testArrete("arrete-generique");
		testArrete("arrete-generique-sans-factures");
	}

	private void testArrete(String view) throws IOException,
			FileNotFoundException {
		testArrete(view, ARRETE);
	}

	private void testArrete(String view, String numero) throws IOException,
			FileNotFoundException {
		Arrete arrete = unique(createDataContext(), Arrete.class, //
				"numero", numero);
		System.out.println(view + " for " + arrete);
		doReportTest(view, "pdf", "ARRETE_ID", intPKForObject(arrete));
		// for (String format : ReportService.getSupportedFormats()) {
		// doReportTest(view, format, "ARRETE_ID", intPKForObject(arrete));
		// }
	}

	private void doReportTest(String reportView, String format, String key,
			Object value) throws IOException, FileNotFoundException {
		Context context = new ReportHelper.Context(reportView, format, null,
				null);
		context.put(key, value);
		context.put("titre", "TITRE TEST");
		context.put("article1", "Article 1 text...:");
		context.put("infosImputation",
				"Info imputation\n- test\n- fonction: test");
		context.put("avecFactures", true);

		LongAction.Result result = new ReportHelper().renderReport(context);

		// Record output for control
		FileOutputStream out = new FileOutputStream("test-output/" + reportView
				+ "." + format);
		out.write(result.getBytes());
		out.close();
	}

}
