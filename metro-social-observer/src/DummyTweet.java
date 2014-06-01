/**
 * 
 */

/**
 * @author dodev
 *
 */
public class DummyTweet {

	private String text;
	private int retweetCount;
	private int favoriteCount;
	private String[] hashtags;

	/**
	 * 
	 */
	public DummyTweet(
			String text,
			int retweetCount,
			int favoriteCount,
			String[] hashtags
			) {
		this.text = text;
		this.retweetCount = retweetCount;
		this.favoriteCount = favoriteCount;
		this.hashtags = hashtags;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the retweetCount
	 */
	public int getRetweetCount() {
		return retweetCount;
	}

	/**
	 * @return the favoriteCount
	 */
	public int getFavoriteCount() {
		return favoriteCount;
	}

	/**
	 * @return the hashtags
	 */
	public String[] getHashtags() {
		return hashtags;
	}

}
