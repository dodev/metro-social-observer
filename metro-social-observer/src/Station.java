/**
 * 
 */

/**
 * @author dodev
 *
 */
public class Station implements INamedSchemeObject {

	private int id;
	private String name;
	private int warningLevel;
	private int lineId;

	/**
	 * 
	 */
	public Station(
			int id,
			String name,
			int lineId
			) {
		this.id = id;
		this.name = name;
		this.lineId = lineId;
		
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

	/**
	 * @return the lineId
	 */
	public int getLineId() {
		return lineId;
	}
}
