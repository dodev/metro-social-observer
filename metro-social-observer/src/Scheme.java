import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */

/**
 * @author dodev
 *
 */
public class Scheme {
	
	private int id;
	private String name;
	private int warningLevel;
	
	private ArrayList<Line> lines;
	private HashMap<Integer, Line> lineHash;
	
	private ArrayList<Station> stations;
	private HashMap<Integer, Station> stationHash;
	
	private ArrayList<Link> links;
	private HashMap<Integer, Link> linkHash;
	
	
	public Scheme(String jsonString) {
		this.lines = new ArrayList<Line>();
		this.lineHash = new HashMap<Integer, Line>();
		
		this.stations = new ArrayList<Station>();
		this.stationHash = new HashMap<Integer, Station>();
		
		this.links = new ArrayList<Link>();
		this.linkHash = new HashMap<Integer, Link>();
		
		// TODO: parse id
		this.id = 1;
		
		// TODO: parse name
		this.name = "moscow";
		
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

}
