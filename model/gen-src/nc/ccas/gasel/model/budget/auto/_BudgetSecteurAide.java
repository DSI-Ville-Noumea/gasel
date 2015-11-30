package nc.ccas.gasel.model.budget.auto;

import java.util.List;

import nc.ccas.gasel.model.budget.BudgetTypePublic;

/** Class _BudgetSecteurAide was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _BudgetSecteurAide extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2542593082907189491L;
	public static final String MONTANT_PROPERTY = "montant";
    public static final String RESTANT_PROPERTY = "restant";
    public static final String PAR_PUBLIC_PROPERTY = "parPublic";
    public static final String PARENT_PROPERTY = "parent";
    public static final String SECTEUR_AIDE_PROPERTY = "secteurAide";

    public static final String ID_PK_COLUMN = "id";

    public void setMontant(Double montant) {
        writeProperty("montant", montant);
    }
    public Double getMontant() {
        return (Double)readProperty("montant");
    }
    
    
    public void setRestant(Double restant) {
        writeProperty("restant", restant);
    }
    public Double getRestant() {
        return (Double)readProperty("restant");
    }
    
    
    public void addToParPublic(nc.ccas.gasel.model.budget.BudgetTypePublic obj) {
        addToManyTarget("parPublic", obj, true);
    }
    public void removeFromParPublic(nc.ccas.gasel.model.budget.BudgetTypePublic obj) {
        removeToManyTarget("parPublic", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<nc.ccas.gasel.model.budget.BudgetTypePublic> getParPublic() {
        return (List<BudgetTypePublic>)readProperty("parPublic");
    }
    
    
    public void setParent(nc.ccas.gasel.model.budget.BudgetImpMensuel parent) {
        setToOneTarget("parent", parent, true);
    }

    public nc.ccas.gasel.model.budget.BudgetImpMensuel getParent() {
        return (nc.ccas.gasel.model.budget.BudgetImpMensuel)readProperty("parent");
    } 
    
    
    public void setSecteurAide(nc.ccas.gasel.model.budget.SecteurAide secteurAide) {
        setToOneTarget("secteurAide", secteurAide, true);
    }

    public nc.ccas.gasel.model.budget.SecteurAide getSecteurAide() {
        return (nc.ccas.gasel.model.budget.SecteurAide)readProperty("secteurAide");
    } 
    
    
}