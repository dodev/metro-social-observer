/**
 * 
 */

/**
 * @author dodev
 *
 */
public class Line {
	
	

	/**
	 * 
	 */
	public Line() {
		
		// TODO: populate
		
		this.id = 1;
		this.name = "";
		
		this.warningLevel = 0;
	}
	
	private int id;
	private String name;
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
	
	public String getName(){
		return name;		
	}

}
