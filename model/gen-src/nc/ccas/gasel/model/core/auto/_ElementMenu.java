package nc.ccas.gasel.model.core.auto;

/** Class _ElementMenu was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _ElementMenu extends org.apache.cayenne.CayenneDataObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1767236895397822748L;

	public static final String PAGE_PROPERTY = "page";

    public static final String ID_PK_COLUMN = "id";

    public void setPage(String page) {
        writeProperty("page", page);
    }
    public String getPage() {
        return (String)readProperty("page");
    }
    
    
}