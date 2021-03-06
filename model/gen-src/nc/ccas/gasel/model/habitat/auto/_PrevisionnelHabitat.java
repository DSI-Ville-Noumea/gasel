package nc.ccas.gasel.model.habitat.auto;

/** Class _PrevisionnelHabitat was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _PrevisionnelHabitat extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4872989135905896629L;
	public static final String ECHEANCE_PROPERTY = "echeance";
    public static final String REEL_PROPERTY = "reel";
    public static final String CATEGORIE_SORTIE_PROPERTY = "categorieSortie";

    public static final String ID_PK_COLUMN = "id";

    public void setEcheance(java.util.Date echeance) {
        writeProperty("echeance", echeance);
    }
    public java.util.Date getEcheance() {
        return (java.util.Date)readProperty("echeance");
    }
    
    
    public void setReel(java.util.Date reel) {
        writeProperty("reel", reel);
    }
    public java.util.Date getReel() {
        return (java.util.Date)readProperty("reel");
    }
    
    
    public void setCategorieSortie(nc.ccas.gasel.model.habitat.enums.CategorieSortieProtocole categorieSortie) {
        setToOneTarget("categorieSortie", categorieSortie, true);
    }

    public nc.ccas.gasel.model.habitat.enums.CategorieSortieProtocole getCategorieSortie() {
        return (nc.ccas.gasel.model.habitat.enums.CategorieSortieProtocole)readProperty("categorieSortie");
    } 
    
    
}
