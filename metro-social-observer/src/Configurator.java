import java.util.HashMap;

/**
 * 
 */

/**
 * @author dodev
 *
 */
public class Configurator {
	
	private static Configurator instance = null;
	
	public static Configurator getInstance() {
		if (instance == null) {
			instance = new Configurator();
		}
		return instance;
	}
	
	private HashMap<String, String> hash;
	
	private Configurator() {
		this.hash = new HashMap<String, String>();
	}
	
	public String get(String key) {
		if (this.hash.containsKey(key)) {
			return hash.get(key);
		}
		
		return null;	
	}
}
