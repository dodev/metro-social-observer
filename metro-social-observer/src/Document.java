/**
 * 
 */

/**
 * @author dodev
 *
 */
public class Document {
	private String text;
	private String[] keywords;
	private int rating;
	
	public Document(String _text, String[] _keywords, int _rating) {
		this.text = _text;
		this.keywords = _keywords;
		this.rating = _rating;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the keywords
	 */
	public String[] getKeywords() {
		return keywords;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

}
