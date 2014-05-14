import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
	
	public void loadFromExternalStorage() throws Exception {
		// TODO: write the loading implementation
		Configurator conf = Configurator.getInstance();
		String url = conf.get("scheme-url");
		String[] files = conf.get("scheme-files").split(",");
		
		for (int i = 0; i < files.length; i++) {
			String json = readUrl(url + files[i]);
			Logger.notice(json);
			Scheme sc = new Scheme(json);
			this.schemesHash.put(sc.getId(), sc);
		}
	}
	
	public Scheme getSchemeById(int id) {
		if (this.schemesHash.containsKey(id)) {
			return this.schemesHash.get(id);
		}
		return null;
	}
	
	public Scheme[] getSchemesArray() {
		Scheme[] a = {};
		return this.schemesHash.values().toArray(a);
	}
	
	private static String readUrl(String url) throws Exception {
		URL schemeJson = new URL(url);
        URLConnection conn = schemeJson.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                conn.getInputStream())
        );
        
        StringBuilder strBuilder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) { 
            strBuilder.append(inputLine);
        }
        
        in.close();
        
        return strBuilder.toString();
	}
}
