import twitter4j.*;
import java.util.ArrayList;

/**
 * @author dodev
 *
 */
public class Twitter4jPreprocessor implements IDataPreprocessor {

	private int retweetScaleRatio;
	private int favoriteScaleRatio;
	/**
	 * 
	 */
	public Twitter4jPreprocessor() {
		Configurator conf = Configurator.getInstance();
		this.retweetScaleRatio = Integer.parseInt(conf.get("retweet-scale-ratio"), 10);
		this.favoriteScaleRatio = Integer.parseInt(conf.get("favorite-scale-ratio"), 10);
	}

	/* (non-Javadoc)
	 * @see IDataPreprocessor#executeOn(java.lang.Object[])
	 */
	@Override
	public Document[] executeOn(Object[] rawDocuments) {
		ArrayList<Document> res = new ArrayList<Document>();
		for (Object o : rawDocuments) {
			Status tweet = (Status)o;

			res.add(new Document(
					this.normalizeText(tweet.getText()),
					this.getKeywords(tweet),
					this.getRating(tweet)
				));
		}
		Document[] example = {};
		return res.toArray(example);
	}
	
	private String[] getKeywords(Status tweet) {
		HashtagEntity[] tags = tweet.getHashtagEntities();
		String[] keywords = new String[tags.length];
		for (int i = 0; i < tags.length; i++) {
			keywords[i] = tags[i].getText();
		}
		return keywords;
	}

	private int getRating(Status tweet) {
		return tweet.getRetweetCount() * this.retweetScaleRatio +
				tweet.getFavoriteCount() * this.favoriteScaleRatio;
	}
	
	private String normalizeText(String text) {
		return text.toLowerCase().replaceAll("[@#\"\'\u00AB\u00BB]", " ");
	}
}
