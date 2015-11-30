package nc.ccas.gasel.model.pi.auto;

import java.util.List;

import nc.ccas.gasel.model.pi.ArreteJF;
import nc.ccas.gasel.model.pi.ControleEntretien;
import nc.ccas.gasel.model.pi.Paiement;

/** Class _AttributionJF was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _AttributionJF extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6539437365619120911L;
	public static final String DATE_PROPERTY = "date";
    public static final String ARRETES_PROPERTY = "arretes";
    public static final String CONTROLES_PROPERTY = "controles";
    public static final String DEMANDE_PROPERTY = "demande";
    public static final String PAIEMENTS_PROPERTY = "paiements";
    public static final String PARCELLE_PROPERTY = "parcelle";
    public static final String TYPE_PREVU_PROPERTY = "typePrevu";

    public static final String ID_PK_COLUMN = "id";

    public void setDate(java.util.Date date) {
        writeProperty("date", date);
    }
    public java.util.Date getDate() {
        return (java.util.Date)readProperty("date");
    }
    
    
    public void addToArretes(nc.ccas.gasel.model.pi.ArreteJF obj) {
        addToManyTarget("arretes", obj, true);
    }
    public void removeFromArretes(nc.ccas.gasel.model.pi.ArreteJF obj) {
        removeToManyTarget("arretes", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.pi.ArreteJF> getArretes() {
        return (List<ArreteJF>)readProperty("arretes");
    }
    
    
    public void addToControles(nc.ccas.gasel.model.pi.ControleEntretien obj) {
        addToManyTarget("controles", obj, true);
    }
    public void removeFromControles(nc.ccas.gasel.model.pi.ControleEntretien obj) {
        removeToManyTarget("controles", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.pi.ControleEntretien> getControles() {
        return (List<ControleEntretien>)readProperty("controles");
    }
    
    
    public void setDemande(nc.ccas.gasel.model.pi.DemandeJF demande) {
        setToOneTarget("demande", demande, true);
    }

    public nc.ccas.gasel.model.pi.DemandeJF getDemande() {
        return (nc.ccas.gasel.model.pi.DemandeJF)readProperty("demande");
    } 
    
    
    public void addToPaiements(nc.ccas.gasel.model.pi.Paiement obj) {
        addToManyTarget("paiements", obj, true);
    }
    public void removeFromPaiements(nc.ccas.gasel.model.pi.Paiement obj) {
        removeToManyTarget("paiements", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.pi.Paiement> getPaiements() {
        return (List<Paiement>)readProperty("paiements");
    }
    
    
    public void setParcelle(nc.ccas.gasel.model.pi.Parcelle parcelle) {
        setToOneTarget("parcelle", parcelle, true);
    }

    public nc.ccas.gasel.model.pi.Parcelle getParcelle() {
        return (nc.ccas.gasel.model.pi.Parcelle)readProperty("parcelle");
    } 
    
    
    public void setTypePrevu(nc.ccas.gasel.model.pi.enums.TypeParcelle typePrevu) {
        setToOneTarget("typePrevu", typePrevu, true);
    }

    public nc.ccas.gasel.model.pi.enums.TypeParcelle getTypePrevu() {
        return (nc.ccas.gasel.model.pi.enums.TypeParcelle)readProperty("typePrevu");
    } 
    
    
}