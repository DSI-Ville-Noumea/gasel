package nc.ccas.gasel.model.v1.auto;

import java.util.List;

import nc.ccas.gasel.model.v1.DossierV1;
import nc.ccas.gasel.model.v1.TypeAideV1;

/** Class _TypePublicV1 was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _TypePublicV1 extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7370475756298667212L;
	public static final String CODE_PROPERTY = "code";
    public static final String DATE_MODIFICATION_PROPERTY = "dateModification";
    public static final String USER_ID_MODIF_PROPERTY = "userIdModif";
    public static final String ENUMERATION_PROPERTY = "enumeration";
    public static final String T_DOSSIER_ARRAY_PROPERTY = "tDossierArray";
    public static final String T_TYPEAIDE_ARRAY_PROPERTY = "tTypeaideArray";

    public static final String T_ENUM_ID_PK_COLUMN = "t_enum_id";

    public void setCode(String code) {
        writeProperty("code", code);
    }
    public String getCode() {
        return (String)readProperty("code");
    }
    
    
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
    
    
    public void addToTDossierArray(nc.ccas.gasel.model.v1.DossierV1 obj) {
        addToManyTarget("tDossierArray", obj, true);
    }
    public void removeFromTDossierArray(nc.ccas.gasel.model.v1.DossierV1 obj) {
        removeToManyTarget("tDossierArray", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.v1.DossierV1> getTDossierArray() {
        return (List<DossierV1>)readProperty("tDossierArray");
    }
    
    
    public void addToTTypeaideArray(nc.ccas.gasel.model.v1.TypeAideV1 obj) {
        addToManyTarget("tTypeaideArray", obj, true);
    }
    public void removeFromTTypeaideArray(nc.ccas.gasel.model.v1.TypeAideV1 obj) {
        removeToManyTarget("tTypeaideArray", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.v1.TypeAideV1> getTTypeaideArray() {
        return (List<TypeAideV1>)readProperty("tTypeaideArray");
    }
    
    
}
