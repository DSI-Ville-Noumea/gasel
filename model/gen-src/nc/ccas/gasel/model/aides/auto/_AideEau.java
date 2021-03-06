package nc.ccas.gasel.model.aides.auto;

/** Class _AideEau was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _AideEau extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -821846601646752782L;
	public static final String DEPASSEMENT_M3_PROPERTY = "depassementM3";
    public static final String MONTANT_DEJA_PAYE_PROPERTY = "montantDejaPaye";
    public static final String PERIODE_PRESTATION_PROPERTY = "periodePrestation";
    public static final String POLICE_PROPERTY = "police";
    public static final String PRISE_EN_CHARGE_CONSO_PROPERTY = "priseEnChargeConso";
    public static final String RESTANT_DU_PROPERTY = "restantDu";

    public static final String ID_PK_COLUMN = "id";

    public void setDepassementM3(Double depassementM3) {
        writeProperty("depassementM3", depassementM3);
    }
    public Double getDepassementM3() {
        return (Double)readProperty("depassementM3");
    }
    
    
    public void setMontantDejaPaye(Integer montantDejaPaye) {
        writeProperty("montantDejaPaye", montantDejaPaye);
    }
    public Integer getMontantDejaPaye() {
        return (Integer)readProperty("montantDejaPaye");
    }
    
    
    public void setPeriodePrestation(String periodePrestation) {
        writeProperty("periodePrestation", periodePrestation);
    }
    public String getPeriodePrestation() {
        return (String)readProperty("periodePrestation");
    }
    
    
    public void setPolice(String police) {
        writeProperty("police", police);
    }
    public String getPolice() {
        return (String)readProperty("police");
    }
    
    
    public void setPriseEnChargeConso(Integer priseEnChargeConso) {
        writeProperty("priseEnChargeConso", priseEnChargeConso);
    }
    public Integer getPriseEnChargeConso() {
        return (Integer)readProperty("priseEnChargeConso");
    }
    
    
    public void setRestantDu(Integer restantDu) {
        writeProperty("restantDu", restantDu);
    }
    public Integer getRestantDu() {
        return (Integer)readProperty("restantDu");
    }
    
    
}
