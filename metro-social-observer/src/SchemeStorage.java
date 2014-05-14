import java.util.HashMap;

/**
 * 
 */

/**
 * @author dodev
 *
 */
public class SchemeStorage {
	
	private static SchemeStorage instance = null;
	
	public static SchemeStorage getInstance() {
		if (instance == null) {
			instance = new SchemeStorage ();
		}
		
		return instance;
	}
	
	private HashMap<Integer, Scheme> schemesHash;
	
	public SchemeStorage() {
		this.schemesHash = new HashMap<Integer, Scheme>();
	}
	
	public void loadFromExternalStorage() {
		// TODO: write the loading implementation
	}
	
	public Scheme getSchemeById(int id) {
		if (this.schemesHash.containsKey(id)) {
			return this.schemesHash.get(id);
		}
		return null;
	}
	
	public Scheme[] getSchemesArray() {
		return (Scheme[]) this.schemesHash.values().toArray();
	}
}
