package nc.ccas.gasel.model.paph.auto;

import java.util.List;

import nc.ccas.gasel.model.paph.ObjectifPAPH;

/** Class _AccompagnementPAPH was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _AccompagnementPAPH extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3866143248913753472L;
	public static final String COMMENTAIRE_PROPERTY = "commentaire";
    public static final String PROJET_PROPERTY = "projet";
    public static final String DOSSIER_PROPERTY = "dossier";
    public static final String OBJECTIFS_PROPERTY = "objectifs";

    public static final String ID_PK_COLUMN = "id";

    public void setCommentaire(String commentaire) {
        writeProperty("commentaire", commentaire);
    }
    public String getCommentaire() {
        return (String)readProperty("commentaire");
    }
    
    
    public void setProjet(String projet) {
        writeProperty("projet", projet);
    }
    public String getProjet() {
        return (String)readProperty("projet");
    }
    
    
    public void setDossier(nc.ccas.gasel.model.paph.DossierPAPH dossier) {
        setToOneTarget("dossier", dossier, true);
    }

    public nc.ccas.gasel.model.paph.DossierPAPH getDossier() {
        return (nc.ccas.gasel.model.paph.DossierPAPH)readProperty("dossier");
    } 
    
    
    public void addToObjectifs(nc.ccas.gasel.model.paph.ObjectifPAPH obj) {
        addToManyTarget("objectifs", obj, true);
    }
    public void removeFromObjectifs(nc.ccas.gasel.model.paph.ObjectifPAPH obj) {
        removeToManyTarget("objectifs", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.paph.ObjectifPAPH> getObjectifs() {
        return (List<ObjectifPAPH>)readProperty("objectifs");
    }
    
    
}