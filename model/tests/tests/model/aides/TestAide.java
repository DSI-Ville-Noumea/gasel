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
		
		byte[] bytes = a.getExtensionData();
		
		a = new Aide();
		a.setExtensionData(bytes);
		assertEquals(new Integer(123), a.getExtension().getEec().getMontantFacture());
	}

}
