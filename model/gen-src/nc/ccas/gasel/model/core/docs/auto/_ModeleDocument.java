package nc.ccas.gasel.model.core.docs.auto;

/** Class _ModeleDocument was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _ModeleDocument extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5855738940384527613L;
	public static final String DATA_PROPERTY = "data";
    public static final String MODIF_DATE_PROPERTY = "modifDate";
    public static final String MODIF_UTILISATEUR_PROPERTY = "modifUtilisateur";

    public static final String ID_PK_COLUMN = "id";

    public void setData(String data) {
        writeProperty("data", data);
    }
    public String getData() {
        return (String)readProperty("data");
    }
    
    
    public void setModifDate(java.util.Date modifDate) {
        writeProperty("modifDate", modifDate);
    }
    public java.util.Date getModifDate() {
        return (java.util.Date)readProperty("modifDate");
    }
    
    
    public void setModifUtilisateur(nc.ccas.gasel.model.core.Utilisateur modifUtilisateur) {
        setToOneTarget("modifUtilisateur", modifUtilisateur, true);
    }

    public nc.ccas.gasel.model.core.Utilisateur getModifUtilisateur() {
        return (nc.ccas.gasel.model.core.Utilisateur)readProperty("modifUtilisateur");
    } 
    
    
}