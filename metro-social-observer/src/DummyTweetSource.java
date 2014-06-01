import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 
 */

/**
 * @author dodev
 *
 */
public class DummyTweetSource implements IDataSource {
	
	private String sourceUrl;
	private JSONParser parser;

	public DummyTweetSource() {
		Configurator conf = Configurator.getInstance();
		this.sourceUrl = conf.get("dummy-tweet-url");
		this.parser = new JSONParser();
	}

	/* (non-Javadoc)
	 * @see IDataSource#executeRequest(Scheme)
	 */
	@Override
	public Object[] executeRequest(Scheme target) {
		ArrayList<DummyTweet> list = new ArrayList<DummyTweet>();
		
		try {
			String jsonString = this.readUrl(this.sourceUrl + target.getId() + ".json");	
			JSONArray array = (JSONArray)this.parser.parse(jsonString);
			
			for (Object o : array) {
				JSONObject tweetObj = (JSONObject)o;

				list.add(this.extractTweet(tweetObj));
			}
			
		} catch (Exception e) {
			
			Logger.error(e.toString());
		}
		
		DummyTweet[] example = {};
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
	
	private int safeLongToInt(long l) {
	    return (int) Math.max(Math.min(Integer.MAX_VALUE, l), Integer.MIN_VALUE);
	}
	
	private DummyTweet extractTweet(JSONObject tweetObj) {
		int retweetCount = this.safeLongToInt((long)tweetObj.get("retweet-count"));
		int favoriteCount = this.safeLongToInt((long)tweetObj.get("favorite-count"));
		String text = (String)tweetObj.get("text");
		JSONArray hashtagArr = (JSONArray)tweetObj.get("hashtags");
		ArrayList<String> hashtagList = new ArrayList<String>();
		for (Object o : hashtagArr) {
			hashtagList.add((String)o);
		}
		
		String[] example = {};
		return new DummyTweet(
				text,
				retweetCount,
				favoriteCount,
				hashtagList.toArray(example)
			);
	}
}
