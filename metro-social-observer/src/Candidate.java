/**
 * 
 */

/**
 * @author dodev
 *
 */
public class Candidate {

	private ISchemeObject schemeObject;
	private int rating;
	private int warningDelta;

	/**
	 * 
	 */
	public Candidate(ISchemeObject obj, int rating, int warningDelta) {
		this.schemeObject = obj;
		this.rating = rating;
		this.warningDelta = warningDelta;
	}

	/**
	 * @return the schemeObject
	 */
	public ISchemeObject getSchemeObject() {
		return schemeObject;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @return the warningDelta
	 */
	public int getWarningDelta() {
		return warningDelta;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public void changeRating(int ratingDelta) {
		this.rating += ratingDelta;
	}

}
