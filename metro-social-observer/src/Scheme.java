import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author dodev
 *
 */
public class Scheme implements INamedSchemeObject {
	
	private int id;
	private String stringId;

	private String name;
	private int warningLevel;
	
	private ArrayList<Line> lines;
	private HashMap<Integer, Line> lineHash;
	
	private ArrayList<Station> stations;
	private HashMap<Integer, Station> stationHash;
	
	private ArrayList<Link> links;
	private HashMap<Integer, Link> linkHash;
	private HashMap<Integer, Link> linkStationHash;
	
	
	public Scheme(String jsonString) throws ParseException {
		this.lines = new ArrayList<Line>();
		this.lineHash = new HashMap<Integer, Line>();
		
		this.stations = new ArrayList<Station>();
		this.stationHash = new HashMap<Integer, Station>();
		
		this.links = new ArrayList<Link>();
		this.linkHash = new HashMap<Integer, Link>();
		this.linkStationHash = new HashMap<Integer, Link>();
		
		JSONParser parser = new JSONParser();
		
		JSONObject obj = (JSONObject)parser.parse(jsonString);
		
		this.id = Integer.parseInt(obj.get("id").toString(), 10);
		this.name = (String)obj.get("name");
		this.stringId = (String)obj.get("stringId");
			
		HashMap<String, Object> stationsMap = (HashMap<String, Object>)obj.get("stations");
		for (String key: stationsMap.keySet()) {
			JSONObject stationObj = (JSONObject)stationsMap.get(key);
			int id = Integer.parseInt(key, 10);
			int lineId = this.safeLongToInt((long)stationObj.get("lineId"));
			String stationName = (String)stationObj.get("name");
			
			Station station = new Station(id, stationName, lineId);
			this.stations.add(station);
			this.stationHash.put(id, station);
		}
		
		HashMap<String, Object> linksMap = (HashMap<String, Object>)obj.get("links");
		for (String key: linksMap.keySet()) {
			JSONObject linkObj = (JSONObject)linksMap.get(key);
			boolean isTransfer = (String)linkObj.get("type") == "transfer"? true : false;
			int id = Integer.parseInt(key, 10);
			int fromStId = this.safeLongToInt((long)linkObj.get("fromStationId"));
			int toStId = this.safeLongToInt((long)linkObj.get("toStationId"));
			
			Link link = new Link(id, fromStId, toStId, isTransfer);
			this.links.add(link);
			this.linkHash.put(id, link);
			this.linkStationHash.put(fromStId * 1000 + toStId, link);
			this.linkStationHash.put(toStId * 1000 + fromStId, link);
		}
		
		HashMap<String, Object> linesMap = (HashMap<String, Object>)obj.get("lines");
		for (String key: linesMap.keySet()) {
			JSONObject lineObj = (JSONObject)linesMap.get(key);
			int id = Integer.parseInt(key, 10);
			String lineName = (String)lineObj.get("name");

			JSONArray linkArray = (JSONArray)lineObj.get("linkIds");
			List linkList = new ArrayList<Link>();
			
			for (Object o : linkArray) {
				linkList.add(this.getLinkById(this.safeLongToInt((long)o)));
			}
			
			JSONArray stationArray = (JSONArray)lineObj.get("stationIds");
			List stationList = new ArrayList<Station>();
			
			for (Object o : stationArray) {
				stationList.add(this.getStationById(this.safeLongToInt((long)o)));
			}

			Line line = new Line(id, lineName, stationList, linkList);
			this.lines.add(line);
			this.lineHash.put(id, line);
		}
		
		this.warningLevel = 0;
		
		Logger.notice("loaded scheme " + this.name + " with id " + this.id);
	}

	/**
	 * @return the lines
	 */
	public ArrayList<Line> getLines() {
		return lines;
	}

	/**
	 * @return the stations
	 */
	public ArrayList<Station> getStations() {
		return stations;
	}

	/**
	 * @return the links
	 */
	public ArrayList<Link> getLinks() {
		return links;
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
	
	/**
	 * @return the stringId
	 */
	public String getStringId() {
		return stringId;
	}
	
	@Override
	public String[] getAliases() {		
		return new String[]{name};
	}
	
	public Line getLineById(int id) {
		if (!this.lineHash.containsKey(id)) {
			return null;
		}	
		return this.lineHash.get(id);
	}
	
	public Link getLinkById(int id) {
		if (!this.linkHash.containsKey(id)) {
			return null;
		}	
		return this.linkHash.get(id);
	}
	
	public Station getStationById(int id) {
		if (!this.stationHash.containsKey(id)) {
			return null;
		}	
		return this.stationHash.get(id);
	}
	
	public Link getLinkBetweenStations(Station stA, Station stB) {
		int key = stA.getId() * 1000 + stB.getId();
		if (!this.linkStationHash.containsKey(key)) {
			return null;
		}
		return this.linkStationHash.get(key);
	}
	
	public Station[] getStationsBetween(Station stA, Station stB) {
		if (stA.getLineId() != stB.getLineId()) {
			return null;
		}
		List stationList = this.getLineById(stA.getLineId()).getStations();
		ArrayList<Station> res = new ArrayList<Station>();
		
		int end1 = stationList.indexOf(stA);
		int end2 = stationList.indexOf(stB);
		
		if (end1 == -1 || end2 == -1) {
			Logger.error("Couldn't find stations with names: " + stA.getName() + " and " + stB.getName());
		} else {
			if (end1 > end2) {
				int buf = end1;
				end1 = end2;
				end2 = buf;
			}
			
			for (int i = end1; i <= end2; i++) {
				res.add((Station)stationList.get(i));
			}
		}
		
		Station[] demoArr = {};
		return res.toArray(demoArr);
	}
	
	public Link[] getLinksBetween(Station stA, Station stB) {
		if (stA.getLineId() != stB.getLineId()) {
			return null;
		}
		ArrayList<Link> res = new ArrayList<Link>();
		
		Station[] stationArr = this.getStationsBetween(stA, stB);
		
		for (int i = 0; i < stationArr.length - 1; i++) {
			res.add(this.getLinkBetweenStations(stationArr[i], stationArr[i+1]));
		}
		
		Link[] demoArr = {};
		return res.toArray(demoArr);
	}
	
	private int safeLongToInt(long l) {
	    return (int) Math.max(Math.min(Integer.MAX_VALUE, l), Integer.MIN_VALUE);
	}
}
