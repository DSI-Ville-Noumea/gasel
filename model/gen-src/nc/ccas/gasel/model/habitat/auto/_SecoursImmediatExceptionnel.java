package nc.ccas.gasel.model.habitat.auto;

/** Class _SecoursImmediatExceptionnel was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _SecoursImmediatExceptionnel extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1341059422158350345L;
	public static final String MONTANT_PROPERTY = "montant";
    public static final String DOSSIER_PROPERTY = "dossier";
    public static final String INSTITUTION_PROPERTY = "institution";
    public static final String MOTIF_PROPERTY = "motif";

    public static final String ID_PK_COLUMN = "id";

    public void setMontant(Integer montant) {
        writeProperty("montant", montant);
    }
    public Integer getMontant() {
        return (Integer)readProperty("montant");
    }
    
    
    public void setDossier(nc.ccas.gasel.model.habitat.AspectSIE dossier) {
        setToOneTarget("dossier", dossier, true);
    }

    public nc.ccas.gasel.model.habitat.AspectSIE getDossier() {
        return (nc.ccas.gasel.model.habitat.AspectSIE)readProperty("dossier");
    } 
    
    
    public void setInstitution(nc.ccas.gasel.model.habitat.enums.Institution institution) {
        setToOneTarget("institution", institution, true);
    }

    public nc.ccas.gasel.model.habitat.enums.Institution getInstitution() {
        return (nc.ccas.gasel.model.habitat.enums.Institution)readProperty("institution");
    } 
    
    
    public void setMotif(nc.ccas.gasel.model.habitat.enums.MotifSIE motif) {
        setToOneTarget("motif", motif, true);
    }

    public nc.ccas.gasel.model.habitat.enums.MotifSIE getMotif() {
        return (nc.ccas.gasel.model.habitat.enums.MotifSIE)readProperty("motif");
    } 
    
    
}