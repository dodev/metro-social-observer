import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
	
	public Scheme(String jsonString) throws ParseException {
		this.lines = new ArrayList<Line>();
		this.lineHash = new HashMap<Integer, Line>();
		
		this.stations = new ArrayList<Station>();
		this.stationHash = new HashMap<Integer, Station>();
		
		this.links = new ArrayList<Link>();
		this.linkHash = new HashMap<Integer, Link>();
		
		JSONParser parser = new JSONParser();
		
		JSONObject obj = (JSONObject)parser.parse(jsonString);
		
		this.id = Integer.parseInt(obj.get("id").toString(), 10);
		this.name = (String)obj.get("name");
		
		this.warningLevel = 0;
		
		Logger.notice("loaded scheme " + this.name + " with id " + this.id);
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
