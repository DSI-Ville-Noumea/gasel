package nc.ccas.gasel.model.pe.auto;

import java.util.List;

import nc.ccas.gasel.model.pe.EnfantRAM;

/** Class _AspectDossierEnfant was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _AspectDossierEnfant extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4431905679028932845L;
	public static final String DOSSIER_PROPERTY = "dossier";
    public static final String ENFANTS_RAM_PROPERTY = "enfantsRAM";

    public static final String DOSSIER_ID_PK_COLUMN = "dossier_id";

    public void setDossier(nc.ccas.gasel.model.core.Dossier dossier) {
        setToOneTarget("dossier", dossier, true);
    }

    public nc.ccas.gasel.model.core.Dossier getDossier() {
        return (nc.ccas.gasel.model.core.Dossier)readProperty("dossier");
    } 
    
    
    public void addToEnfantsRAM(nc.ccas.gasel.model.pe.EnfantRAM obj) {
        addToManyTarget("enfantsRAM", obj, true);
    }
    public void removeFromEnfantsRAM(nc.ccas.gasel.model.pe.EnfantRAM obj) {
        removeToManyTarget("enfantsRAM", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.pe.EnfantRAM> getEnfantsRAM() {
        return (List<EnfantRAM>)readProperty("enfantsRAM");
    }
    
    
}
