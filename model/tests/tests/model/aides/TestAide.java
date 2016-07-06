package tests.model.aides;

import static org.junit.Assert.assertEquals;
import nc.ccas.gasel.model.aides.Aide;

import org.junit.Test;

public class TestAide {

	@Test
	public void testJson() {
		Aide a = new Aide();
		a.getExtension().newEec();
		a.getExtension().getEec().setMontantFacture(123);
		a.updateExt();
		
		String extData = a.getExtensionData();
		
		a = new Aide();
		a.setExtensionData(extData);
		assertEquals(new Integer(123), a.getExtension().getEec().getMontantFacture());
	}

}
