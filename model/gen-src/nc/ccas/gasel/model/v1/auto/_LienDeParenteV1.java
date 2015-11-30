package nc.ccas.gasel.model.v1.auto;

/** Class _LienDeParenteV1 was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _LienDeParenteV1 extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2100439804360163855L;
	public static final String DATE_MODIFICATION_PROPERTY = "dateModification";
    public static final String USER_ID_MODIF_PROPERTY = "userIdModif";
    public static final String AUTRE_LIEN_PROPERTY = "AutreLien";
    public static final String LIEN_ACHARGE_PROPERTY = "LienACharge";
    public static final String ENUMERATION_PROPERTY = "enumeration";

    public static final String T_ENUM_ID_PK_COLUMN = "t_enum_id";

    public void setDateModification(java.util.Date dateModification) {
        writeProperty("dateModification", dateModification);
    }
    public java.util.Date getDateModification() {
        return (java.util.Date)readProperty("dateModification");
    }
    
    
    public void setUserIdModif(String userIdModif) {
        writeProperty("userIdModif", userIdModif);
    }
    public String getUserIdModif() {
        return (String)readProperty("userIdModif");
    }
    
    
    public void setAutreLien(nc.ccas.gasel.model.v1.AutreLienV1 AutreLien) {
        setToOneTarget("AutreLien", AutreLien, true);
    }

    public nc.ccas.gasel.model.v1.AutreLienV1 getAutreLien() {
        return (nc.ccas.gasel.model.v1.AutreLienV1)readProperty("AutreLien");
    } 
    
    
    public void setLienACharge(nc.ccas.gasel.model.v1.LienAChargeV1 LienACharge) {
        setToOneTarget("LienACharge", LienACharge, true);
    }

    public nc.ccas.gasel.model.v1.LienAChargeV1 getLienACharge() {
        return (nc.ccas.gasel.model.v1.LienAChargeV1)readProperty("LienACharge");
    } 
    
    
    public void setEnumeration(nc.ccas.gasel.model.v1.EnumerationV1 enumeration) {
        setToOneTarget("enumeration", enumeration, true);
    }

    public nc.ccas.gasel.model.v1.EnumerationV1 getEnumeration() {
        return (nc.ccas.gasel.model.v1.EnumerationV1)readProperty("enumeration");
    } 
    
    
}