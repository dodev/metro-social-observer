/**
 * 
 */

/**
 * @author dodev
 *
 */
public class Line implements INamedSchemeObject {
	
	private int id;
	private String name;
	private int warningLevel;

	/**
	 * 
	 */
	public Line(
			int id,
			String name
			) {

		this.id = id;
		this.name = name;
		
		this.warningLevel = 0;
	}
	
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

	@Override
	public String[] getAliases() {		
		return new String[]{name};
	}

}
