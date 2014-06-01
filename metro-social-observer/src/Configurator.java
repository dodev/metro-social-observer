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
		this.hash.put("server-port", "8080");
		this.hash.put("scheme-url", "http://localhost/schemes/");
		//this.hash.put("scheme-files", "1.json,2.json");
		this.hash.put("scheme-files", "1.json");
		this.hash.put("sleep-time-ms", "20000");
		this.hash.put("is-alive-check-time-ms", "1000");
		this.hash.put("retweet-scale-ratio", "10");
		this.hash.put("favorite-scale-ratio", "5");
		this.hash.put("dummy-data-url", "http://localhost/dummy-data/");
		this.hash.put("dummy-tweet-url", "http://localhost/dummy-tweets/");
		
		// TODO: load from file
	}
	
	public String get(String key) {
		if (this.hash.containsKey(key)) {
			return hash.get(key);
		}
		
		return "";	
	}
}
