package nc.ccas.gasel.model.habitat.auto;

/** Class _AffectationRehabilitation was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _AffectationRehabilitation extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3326164110781934534L;
	public static final String CHARGES_PROPERTY = "charges";
    public static final String MONTANT_ASSURANCE_HABITATION_PROPERTY = "montantAssuranceHabitation";
    public static final String TRAITE_PROPERTY = "traite";
    public static final String ASSURANCE_HABITATION_PROPERTY = "assuranceHabitation";
    public static final String QUARTIER_PROPERTY = "quartier";
    public static final String TYPE_PROPERTY = "type";
    public static final String TYPE_EMPRUNT_PROPERTY = "typeEmprunt";

    public static final String ID_PK_COLUMN = "id";

    public void setCharges(Integer charges) {
        writeProperty("charges", charges);
    }
    public Integer getCharges() {
        return (Integer)readProperty("charges");
    }
    
    
    public void setMontantAssuranceHabitation(Integer montantAssuranceHabitation) {
        writeProperty("montantAssuranceHabitation", montantAssuranceHabitation);
    }
    public Integer getMontantAssuranceHabitation() {
        return (Integer)readProperty("montantAssuranceHabitation");
    }
    
    
    public void setTraite(Integer traite) {
        writeProperty("traite", traite);
    }
    public Integer getTraite() {
        return (Integer)readProperty("traite");
    }
    
    
    public void setAssuranceHabitation(nc.ccas.gasel.model.habitat.enums.AssuranceHabitation assuranceHabitation) {
        setToOneTarget("assuranceHabitation", assuranceHabitation, true);
    }

    public nc.ccas.gasel.model.habitat.enums.AssuranceHabitation getAssuranceHabitation() {
        return (nc.ccas.gasel.model.habitat.enums.AssuranceHabitation)readProperty("assuranceHabitation");
    } 
    
    
    public void setQuartier(nc.ccas.gasel.model.mairie.Quartier quartier) {
        setToOneTarget("quartier", quartier, true);
    }

    public nc.ccas.gasel.model.mairie.Quartier getQuartier() {
        return (nc.ccas.gasel.model.mairie.Quartier)readProperty("quartier");
    } 
    
    
    public void setType(nc.ccas.gasel.model.habitat.enums.TypeLogement type) {
        setToOneTarget("type", type, true);
    }

    public nc.ccas.gasel.model.habitat.enums.TypeLogement getType() {
        return (nc.ccas.gasel.model.habitat.enums.TypeLogement)readProperty("type");
    } 
    
    
    public void setTypeEmprunt(nc.ccas.gasel.model.habitat.enums.TypeEmprunt typeEmprunt) {
        setToOneTarget("typeEmprunt", typeEmprunt, true);
    }

    public nc.ccas.gasel.model.habitat.enums.TypeEmprunt getTypeEmprunt() {
        return (nc.ccas.gasel.model.habitat.enums.TypeEmprunt)readProperty("typeEmprunt");
    } 
    
    
}