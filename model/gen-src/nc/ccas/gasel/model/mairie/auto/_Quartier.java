package nc.ccas.gasel.model.mairie.auto;

import java.util.List;

import nc.ccas.gasel.model.mairie.Voie;

/** Class _Quartier was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _Quartier extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -899115423452310720L;
	public static final String LIQUAR_PROPERTY = "liquar";
    public static final String VOIES_PROPERTY = "voies";

    public static final String QUARTI_PK_COLUMN = "quarti";

    public void setLiquar(String liquar) {
        writeProperty("liquar", liquar);
    }
    public String getLiquar() {
        return (String)readProperty("liquar");
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