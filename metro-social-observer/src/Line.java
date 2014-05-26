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
	private Scheme scheme;

	/**
	 * 
	 */
	public Line(
			int id,
			String name,
			List stations,
			List links,
			Scheme scheme
			) {

		this.id = id;
		this.name = name;
		this.stations = stations;
		this.links = links;
		this.scheme = scheme;
		
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
	
	public Station[] getStationsBetween(Station stA, Station stB) {
		ArrayList<Station> res = new ArrayList<Station>();
		
		int end1 = this.stations.indexOf(stA);
		int end2 = this.stations.indexOf(stB);
		
		if (end1 == -1 || end2 == -1) {
			Logger.error("Couldn't find stations with names: " + stA.getName() + " and " + stB.getName());
		} else {
			if (end1 > end2) {
				int buf = end1;
				end1 = end2;
				end2 = buf;
			}
			
			for (int i = end1; i < end2; i++) {
				res.add((Station)this.stations.get(i));
			}
		}
		
		Station[] demoArr = {};
		return res.toArray(demoArr);
	}
	
	public Link[] getLinksBetween(Station stA, Station stB) {
		ArrayList<Link> res = new ArrayList<Link>();
		
		Station[] stationArr = this.getStationsBetween(stA, stB);
		
		for (int i = 0; i < stationArr.length - 1; i++) {
			this.scheme.getLinkBetweenStations(stationArr[i], stationArr[i-1]);
		}
		
		Link[] demoArr = {};
		return res.toArray(demoArr);
	}
}
