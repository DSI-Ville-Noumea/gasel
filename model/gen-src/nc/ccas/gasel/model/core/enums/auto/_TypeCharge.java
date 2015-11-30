package nc.ccas.gasel.model.core.enums.auto;

/** Class _TypeCharge was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _TypeCharge extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -585559110981044837L;
	public static final String ACTIF_PROPERTY = "actif";
    public static final String LIBELLE_PROPERTY = "libelle";
    public static final String LOCKED_PROPERTY = "locked";
    public static final String PARENT_PROPERTY = "parent";

    public static final String ID_PK_COLUMN = "id";

    public void setActif(Boolean actif) {
        writeProperty("actif", actif);
    }
    public Boolean getActif() {
        return (Boolean)readProperty("actif");
    }
    
    
    public void setLibelle(String libelle) {
        writeProperty("libelle", libelle);
    }
    public String getLibelle() {
        return (String)readProperty("libelle");
    }
    
    
    public void setLocked(Boolean locked) {
        writeProperty("locked", locked);
    }
    public Boolean getLocked() {
        return (Boolean)readProperty("locked");
    }
    
    
    public void setParent(nc.ccas.gasel.model.core.enums.CategorieCharge parent) {
        setToOneTarget("parent", parent, true);
    }

    public nc.ccas.gasel.model.core.enums.CategorieCharge getParent() {
        return (nc.ccas.gasel.model.core.enums.CategorieCharge)readProperty("parent");
    } 
    
    
}