import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 * 
 */

/**
 * @author dodev
 *
 */
public class DummySource implements IDataSource {

	/**
	 * 
	 */
	public DummySource() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see IDataSource#executeRequest(Scheme)
	 */
	@Override
	public Object[] executeRequest(Scheme target) {
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			String jsonString = this.readUrl("http://localhost/dummy-tweets/" + target.getId() + ".json");
			JSONParser parser = new JSONParser();
			JSONArray array = (JSONArray)parser.parse(jsonString);
			
			for (Object o : array) {
				String msg = (String)o;
				list.add(msg);
			}
			
		} catch (Exception e) {
			
			Logger.error(e.toString());
		}
		
		String[] example = {};
		return list.toArray(example);
	}
	
	private String readUrl(String url) throws Exception {
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
