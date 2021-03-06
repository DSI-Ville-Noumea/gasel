package nc.ccas.gasel.model.v1.auto;

import java.util.List;

import nc.ccas.gasel.model.v1.AideV1;

/** Class _FrequenceAideV1 was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _FrequenceAideV1 extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5409087485575247887L;
	public static final String DATE_MODIFICATION_PROPERTY = "dateModification";
    public static final String USER_ID_MODIF_PROPERTY = "userIdModif";
    public static final String ENUMERATION_PROPERTY = "enumeration";
    public static final String T_AIDE_ARRAY_PROPERTY = "tAideArray";

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
    
    
    public void setEnumeration(nc.ccas.gasel.model.v1.EnumerationV1 enumeration) {
        setToOneTarget("enumeration", enumeration, true);
    }

    public nc.ccas.gasel.model.v1.EnumerationV1 getEnumeration() {
        return (nc.ccas.gasel.model.v1.EnumerationV1)readProperty("enumeration");
    } 
    
    
    public void addToTAideArray(nc.ccas.gasel.model.v1.AideV1 obj) {
        addToManyTarget("tAideArray", obj, true);
    }
    public void removeFromTAideArray(nc.ccas.gasel.model.v1.AideV1 obj) {
        removeToManyTarget("tAideArray", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.v1.AideV1> getTAideArray() {
        return (List<AideV1>)readProperty("tAideArray");
    }
    
    
}
