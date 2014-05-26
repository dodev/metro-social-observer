import java.util.ArrayList;
import java.util.List;

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
	private List stations;
	private List links;

	/**
	 * 
	 */
	public Line(
			int id,
			String name,
			List stations,
			List links
			) {

		this.id = id;
		this.name = name;
		this.stations = stations;
		this.links = links;
		
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
	 * @return the stations
	 */
	public List getStations() {
		return stations;
	}

	/**
	 * @return the links
	 */
	public List getLinks() {
		return links;
	}
}
