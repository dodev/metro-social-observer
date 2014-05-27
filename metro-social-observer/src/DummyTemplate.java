import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author dodev
 *
 */
public class DummyTemplate extends AbstractTemplate {

	/**
	 * @param regexText
	 * @param delta
	 */
	public DummyTemplate() {
		super("*", 1);
	}

	/* (non-Javadoc)
	 * @see AbstractTemplate#extractObjects(java.lang.String, Scheme)
	 */
	@Override
	public ISchemeObject[] extractObjects(String text, Scheme scheme) {
		String[] stationStrs = text.split(",");
		ArrayList<ISchemeObject> res = new ArrayList<ISchemeObject>(); 
		if (stationStrs.length == 2) {
			String stANormalizedName = stationStrs[0].toLowerCase();
			String stBNormalizedName = stationStrs[1].toLowerCase();
			Station stA = null;
			Station stB = null;
			List<Station> stations = scheme.getStations();
			for (Station st: stations) {
				String normalizedName = st.getName().toLowerCase();
				if (normalizedName.compareTo(stANormalizedName) == 0) {
					stA = st;
				} else if (normalizedName.compareTo(stBNormalizedName) == 0) {
					stB = st;
				}
				
				if (stA != null && stB != null) {
					break;
				}
			}
			
			if (stA != null && stB != null) {				
				Link[] links = scheme.getLinksBetween(stA, stB);
				for (Link l: links) {
					res.add(l);
				}
			} else {
				
				Logger.error("couldn't find stations");
			}
			
		} else {
			Logger.error("Couldn't parse string in the document");
		}
		
		ISchemeObject[] example = {};
		return res.toArray(example);
	}

}
