import java.util.List;

/**
 * 
 */

/**
 * @author dodev
 *
 */
public class DummyProcessor implements IDataProcessor {

	/**
	 * 
	 */
	public DummyProcessor() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see IDataProcessor#executeOn(Scheme, Document[])
	 */
	@Override
	public void executeOn(Scheme scheme, Document[] collection) {
//		if (scheme.getId() == 1) {
//			scheme.getLinkById(122).setWarningLevel(1);
//			scheme.getLinkById(123).setWarningLevel(1);
//			scheme.getLinkById(124).setWarningLevel(1);
//			scheme.getLinkById(125).setWarningLevel(1);
//		}
		for (Document d: collection) {
			String[] stationStrs = d.getText().split(",");
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
						l.setWarningLevel(1);
					}
				} else {
					
					Logger.error("couldn't find stations");
				}
				
			} else {
				Logger.error("Couldn't parse string in the document");
			}
			
		}
	}

}
