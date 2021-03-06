package nc.ccas.gasel.model.mairie.auto;

import java.util.List;

import nc.ccas.gasel.model.mairie.CodePostal;
import nc.ccas.gasel.model.mairie.Voie;

/** Class _Commune was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _Commune extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4372192107767397241L;
	public static final String ARTICLE_PROPERTY = "article";
    public static final String LIBELLE_PROPERTY = "libelle";
    public static final String CODES_POSTAUX_PROPERTY = "codesPostaux";
    public static final String DEPARTEMENT_PROPERTY = "departement";
    public static final String VOIES_PROPERTY = "voies";

    public static final String CODCOM_PK_COLUMN = "codcom";

    public void setArticle(String article) {
        writeProperty("article", article);
    }
    public String getArticle() {
        return (String)readProperty("article");
    }
    
    
    public void setLibelle(String libelle) {
        writeProperty("libelle", libelle);
    }
    public String getLibelle() {
        return (String)readProperty("libelle");
    }
    
    
    public void addToCodesPostaux(nc.ccas.gasel.model.mairie.CodePostal obj) {
        addToManyTarget("codesPostaux", obj, true);
    }
    public void removeFromCodesPostaux(nc.ccas.gasel.model.mairie.CodePostal obj) {
        removeToManyTarget("codesPostaux", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.mairie.CodePostal> getCodesPostaux() {
        return (List<CodePostal>)readProperty("codesPostaux");
    }
    
    
    public void setDepartement(nc.ccas.gasel.model.mairie.Departement departement) {
        setToOneTarget("departement", departement, true);
    }

    public nc.ccas.gasel.model.mairie.Departement getDepartement() {
        return (nc.ccas.gasel.model.mairie.Departement)readProperty("departement");
    } 
    
    
    public void addToVoies(nc.ccas.gasel.model.mairie.Voie obj) {
        addToManyTarget("voies", obj, true);
    }
    public void removeFromVoies(nc.ccas.gasel.model.mairie.Voie obj) {
        removeToManyTarget("voies", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.mairie.Voie> getVoies() {
        return (List<Voie>)readProperty("voies");
    }
    
    
}
