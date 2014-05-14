/**
 * 
 */

/**
 * @author dodev
 *
 */
public class Link {

	/**
	 * 
	 */
	public Link() {
		
		// TODO: populate
		
		this.id = 1;
		
		this.warningLevel = 0;
	}
	
	private int id;
	private int warningLevel;
	
	/**
	 * @return the warningLevel
	 */
	public int getWarningLevel() {
		return warningLevel;
	}

	/**
	 * @param warningLevel the warningLevel to set
	 */
	public void setWarningLevel(int warningLevel) {
		this.warningLevel = warningLevel;
	}

	public int getId() {
		return id;
	}
}
