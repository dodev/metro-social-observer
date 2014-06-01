import java.util.ArrayList;

import twitter4j.*;

/**
 * @author dodev
 *
 */
public class TwitterSearchSource implements IDataSource {

	private Twitter twitter;
	/**
	 * 
	 */
	public TwitterSearchSource() {
		this.twitter = TwitterFactory.getSingleton();
	}

	/* (non-Javadoc)
	 * @see IDataSource#executeRequest(Scheme)
	 */
	@Override
	public Object[] executeRequest(Scheme target) {
		ArrayList<Status> tweets = new ArrayList<Status>();
		try {
		  Query query = new Query("#метро");
          QueryResult result;
          do {             
        	  result = twitter.search(query);
        	  tweets.addAll(result.getTweets());
          } while ((query = result.nextQuery()) != null);
		} catch (TwitterException e) {
			Logger.error(e.toString());
		}
		Object[] example = {};
		return tweets.toArray(example);
	}
}
