package nc.ccas.gasel.model.budget.auto;

/** Class _LigneVirement was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _LigneVirement extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7141043621045089342L;
	public static final String DESTINATION_ID_PROPERTY = "destinationId";
    public static final String DESTINATION_TYPE_PROPERTY = "destinationType";
    public static final String MONTANT_PROPERTY = "montant";
    public static final String SOURCE_ID_PROPERTY = "sourceId";
    public static final String SOURCE_TYPE_PROPERTY = "sourceType";

    public static final String ID_PK_COLUMN = "id";

    public void setDestinationId(Integer destinationId) {
        writeProperty("destinationId", destinationId);
    }
    public Integer getDestinationId() {
        return (Integer)readProperty("destinationId");
    }
    
    
    public void setDestinationType(Integer destinationType) {
        writeProperty("destinationType", destinationType);
    }
    public Integer getDestinationType() {
        return (Integer)readProperty("destinationType");
    }
    
    
    public void setMontant(Double montant) {
        writeProperty("montant", montant);
    }
    public Double getMontant() {
        return (Double)readProperty("montant");
    }
    
    
    public void setSourceId(Integer sourceId) {
        writeProperty("sourceId", sourceId);
    }
    public Integer getSourceId() {
        return (Integer)readProperty("sourceId");
    }
    
    
    public void setSourceType(Integer sourceType) {
        writeProperty("sourceType", sourceType);
    }
    public Integer getSourceType() {
        return (Integer)readProperty("sourceType");
    }
    
    
}