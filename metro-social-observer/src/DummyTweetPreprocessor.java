import java.util.ArrayList;

/**
 * @author dodev
 *
 */
public class DummyTweetPreprocessor implements IDataPreprocessor {

	private int retweetScaleRatio;
	private int favoriteScaleRatio;
	/**
	 * 
	 */
	public DummyTweetPreprocessor() {
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
			DummyTweet tweet = (DummyTweet)o;

			res.add(new Document(
					this.normalizeText(tweet.getText()),
					tweet.getHashtags(),
					this.getRating(tweet)
				));
		}
		Document[] example = {};
		return res.toArray(example);
	}

	private int getRating(DummyTweet tweet) {
		return tweet.getRetweetCount() * this.retweetScaleRatio +
				tweet.getFavoriteCount() * this.favoriteScaleRatio;
	}
	
	private String normalizeText(String text) {
		return text.toLowerCase().replaceAll("[@#\"\'\u00AB\u00BB]", "");
	}
}
