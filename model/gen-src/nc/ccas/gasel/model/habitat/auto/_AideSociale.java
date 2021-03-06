package nc.ccas.gasel.model.habitat.auto;

/** Class _AideSociale was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _AideSociale extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3665792822864934574L;
	public static final String MONTANT_PROPERTY = "montant";
    public static final String PERCUE_PROPERTY = "percue";
    public static final String NATURE_PROPERTY = "nature";
    public static final String ORGANISME_PROPERTY = "organisme";

    public static final String ID_PK_COLUMN = "id";

    public void setMontant(Integer montant) {
        writeProperty("montant", montant);
    }
    public Integer getMontant() {
        return (Integer)readProperty("montant");
    }
    
    
    public void setPercue(java.util.Date percue) {
        writeProperty("percue", percue);
    }
    public java.util.Date getPercue() {
        return (java.util.Date)readProperty("percue");
    }
    
    
    public void setNature(nc.ccas.gasel.model.habitat.enums.NatureAideSociale nature) {
        setToOneTarget("nature", nature, true);
    }

    public nc.ccas.gasel.model.habitat.enums.NatureAideSociale getNature() {
        return (nc.ccas.gasel.model.habitat.enums.NatureAideSociale)readProperty("nature");
    } 
    
    
    public void setOrganisme(nc.ccas.gasel.model.habitat.enums.OrganismeAideSociale organisme) {
        setToOneTarget("organisme", organisme, true);
    }

    public nc.ccas.gasel.model.habitat.enums.OrganismeAideSociale getOrganisme() {
        return (nc.ccas.gasel.model.habitat.enums.OrganismeAideSociale)readProperty("organisme");
    } 
    
    
}
