package nc.ccas.gasel.model.habitat.auto;

import java.util.List;

import nc.ccas.gasel.model.habitat.ObservationSiteRHI;

/** Class _SiteRHI was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _SiteRHI extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8191587276530494205L;
	public static final String FAMILLE_ESTIMEES_PROPERTY = "familleEstimees";
    public static final String INSCRIPTION_PROPERTY = "inscription";
    public static final String NOM_PROPERTY = "nom";
    public static final String PROJET_PROPERTY = "projet";
    public static final String RESORBE_PROPERTY = "resorbe";
    public static final String OBSERVATIONS_PROPERTY = "observations";

    public static final String ID_PK_COLUMN = "id";

    public void setFamilleEstimees(Integer familleEstimees) {
        writeProperty("familleEstimees", familleEstimees);
    }
    public Integer getFamilleEstimees() {
        return (Integer)readProperty("familleEstimees");
    }
    
    
    public void setInscription(java.util.Date inscription) {
        writeProperty("inscription", inscription);
    }
    public java.util.Date getInscription() {
        return (java.util.Date)readProperty("inscription");
    }
    
    
    public void setNom(String nom) {
        writeProperty("nom", nom);
    }
    public String getNom() {
        return (String)readProperty("nom");
    }
    
    
    public void setProjet(String projet) {
        writeProperty("projet", projet);
    }
    public String getProjet() {
        return (String)readProperty("projet");
    }
    
    
    public void setResorbe(Boolean resorbe) {
        writeProperty("resorbe", resorbe);
    }
    public Boolean getResorbe() {
        return (Boolean)readProperty("resorbe");
    }
    
    
    public void addToObservations(nc.ccas.gasel.model.habitat.ObservationSiteRHI obj) {
        addToManyTarget("observations", obj, true);
    }
    public void removeFromObservations(nc.ccas.gasel.model.habitat.ObservationSiteRHI obj) {
        removeToManyTarget("observations", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.habitat.ObservationSiteRHI> getObservations() {
        return (List<ObservationSiteRHI>)readProperty("observations");
    }
    
    
}