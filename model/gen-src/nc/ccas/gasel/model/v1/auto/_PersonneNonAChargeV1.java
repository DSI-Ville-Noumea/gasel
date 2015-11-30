package nc.ccas.gasel.model.v1.auto;

/** Class _PersonneNonAChargeV1 was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _PersonneNonAChargeV1 extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5251752562452421445L;
	public static final String DATE_MODIFICATION_PROPERTY = "dateModification";
    public static final String USER_ID_MODIF_PROPERTY = "userIdModif";
    public static final String PERSONNE_PROPERTY = "Personne";
    public static final String EST_AU_CHEF_DE_FAMILLE_PROPERTY = "estAuChefDeFamille";

    public static final String T_PERSONNE_ID_PK_COLUMN = "t_personne_id";

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
    
    
    public void setPersonne(nc.ccas.gasel.model.v1.PersonneV1 Personne) {
        setToOneTarget("Personne", Personne, true);
    }

    public nc.ccas.gasel.model.v1.PersonneV1 getPersonne() {
        return (nc.ccas.gasel.model.v1.PersonneV1)readProperty("Personne");
    } 
    
    
    public void setEstAuChefDeFamille(nc.ccas.gasel.model.v1.AutreLienV1 estAuChefDeFamille) {
        setToOneTarget("estAuChefDeFamille", estAuChefDeFamille, true);
    }

    public nc.ccas.gasel.model.v1.AutreLienV1 getEstAuChefDeFamille() {
        return (nc.ccas.gasel.model.v1.AutreLienV1)readProperty("estAuChefDeFamille");
    } 
    
    
}