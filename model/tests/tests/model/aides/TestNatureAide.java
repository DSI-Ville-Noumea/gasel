package tests.model.aides;

import junit.framework.TestCase;
import nc.ccas.gasel.model.budget.NatureAide;

import org.junit.Test;

public class TestNatureAide extends TestCase {

	@Test
	public void testIsElec() throws Exception {
		NatureAide n = new NatureAide();
		n.setLibelle("Electricité (bon)");
		assertTrue(n.isElec());
		n.setLibelle("Electricité (chèque)");
		assertTrue(n.isElec());
	}

}
