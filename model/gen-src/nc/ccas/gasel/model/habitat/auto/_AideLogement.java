package nc.ccas.gasel.model.habitat.auto;

/** Class _AideLogement was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _AideLogement extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -178420913456663322L;
	public static final String ACCORD_COMMISSION_PROPERTY = "accordCommission";
    public static final String DATE_COMMISSION_PROPERTY = "dateCommission";
    public static final String DATE_EFFET_PROPERTY = "dateEffet";
    public static final String DEBUT_PROPERTY = "debut";
    public static final String FIN_PROPERTY = "fin";
    public static final String MONTANT_AL_PROPERTY = "montantAL";
    public static final String MONTANT_ALTOTAL_PROPERTY = "montantALTotal";
    public static final String MONTANT_DG_PROPERTY = "montantDG";
    public static final String MOTIF_REFUS_PROPERTY = "motifRefus";
    public static final String DEMANDE_PROPERTY = "demande";
    public static final String DEROGATION_PROPERTY = "derogation";
    public static final String NATURE_PROPERTY = "nature";

    public static final String ID_PK_COLUMN = "id";

    public void setAccordCommission(Boolean accordCommission) {
        writeProperty("accordCommission", accordCommission);
    }
    public Boolean getAccordCommission() {
        return (Boolean)readProperty("accordCommission");
    }
    
    
    public void setDateCommission(java.util.Date dateCommission) {
        writeProperty("dateCommission", dateCommission);
    }
    public java.util.Date getDateCommission() {
        return (java.util.Date)readProperty("dateCommission");
    }
    
    
    public void setDateEffet(java.util.Date dateEffet) {
        writeProperty("dateEffet", dateEffet);
    }
    public java.util.Date getDateEffet() {
        return (java.util.Date)readProperty("dateEffet");
    }
    
    
    public void setDebut(java.util.Date debut) {
        writeProperty("debut", debut);
    }
    public java.util.Date getDebut() {
        return (java.util.Date)readProperty("debut");
    }
    
    
    public void setFin(java.util.Date fin) {
        writeProperty("fin", fin);
    }
    public java.util.Date getFin() {
        return (java.util.Date)readProperty("fin");
    }
    
    
    public void setMontantAL(Integer montantAL) {
        writeProperty("montantAL", montantAL);
    }
    public Integer getMontantAL() {
        return (Integer)readProperty("montantAL");
    }
    
    
    public void setMontantALTotal(Integer montantALTotal) {
        writeProperty("montantALTotal", montantALTotal);
    }
    public Integer getMontantALTotal() {
        return (Integer)readProperty("montantALTotal");
    }
    
    
    public void setMontantDG(Integer montantDG) {
        writeProperty("montantDG", montantDG);
    }
    public Integer getMontantDG() {
        return (Integer)readProperty("montantDG");
    }
    
    
    public void setMotifRefus(String motifRefus) {
        writeProperty("motifRefus", motifRefus);
    }
    public String getMotifRefus() {
        return (String)readProperty("motifRefus");
    }
    
    
    public void setDemande(nc.ccas.gasel.model.habitat.DemandeAideLogement demande) {
        setToOneTarget("demande", demande, true);
    }

    public nc.ccas.gasel.model.habitat.DemandeAideLogement getDemande() {
        return (nc.ccas.gasel.model.habitat.DemandeAideLogement)readProperty("demande");
    } 
    
    
    public void setDerogation(nc.ccas.gasel.model.habitat.DerogationAideLogement derogation) {
        setToOneTarget("derogation", derogation, true);
    }

    public nc.ccas.gasel.model.habitat.DerogationAideLogement getDerogation() {
        return (nc.ccas.gasel.model.habitat.DerogationAideLogement)readProperty("derogation");
    } 
    
    
    public void setNature(nc.ccas.gasel.model.habitat.enums.NatureAideLogement nature) {
        setToOneTarget("nature", nature, true);
    }

    public nc.ccas.gasel.model.habitat.enums.NatureAideLogement getNature() {
        return (nc.ccas.gasel.model.habitat.enums.NatureAideLogement)readProperty("nature");
    } 
    
    
}
