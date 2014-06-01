import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * 
 */

/**
 * @author dodev
 *
 */
public class TrainTemplate extends AbstractTemplate {
	
	private int st1Start;

	/**
	 * @param regexText
	 * @param delta
	 */
	public TrainTemplate() {
		super("поезда не ходят от [\\p{L}\\-\\.]+[ ]{0,1}[\\p{L}\\-\\.]+ до [\\p{L}\\-\\.]+[ ]{0,1}[\\p{L}\\-\\.]+", 1);
		
		this.st1Start = "поезда не ходят от ".length();	
	}

	/* (non-Javadoc)
	 * @see AbstractTemplate#extractObjects(java.lang.String, Scheme)
	 */
	@Override
	public ISchemeObject[] extractObjects(String text, Scheme scheme) {
		ArrayList<ISchemeObject> res = new ArrayList<ISchemeObject>(); 
		
		Matcher matcher = this.regex.matcher(text);
		matcher.find();
		String matchedGroup = matcher.group();
		int st1End = matchedGroup.indexOf(" до ");
		String station1 = matchedGroup.substring(this.st1Start, st1End).trim();
		String station2 = matchedGroup.substring(st1End + 4).trim();
		
		Logger.notice(station1);
		Logger.notice(station2);

		Station stA = null;
		Station stB = null;
		List<Station> stations = scheme.getStations();
		for (Station st: stations) {
			String normalizedName = st.getName().toLowerCase();
			if (this.strcmpRus(normalizedName, station1)) {
				stA = st;
			} else if (this.strcmpRus(normalizedName, station2)) {
				stB = st;
			}
			
			if (stA != null && stB != null) {
				break;
			}
		}
		
		if (stA != null && stB != null) {				
			Link[] links = scheme.getLinksBetween(stA, stB);
			if (links != null) {
				for (Link l: links) {
					res.add(l);
				}
			}
		} else {
			
			Logger.error("couldn't find stations");
		}	

		ISchemeObject[] example = {};
		return res.toArray(example);
	}

	private boolean strcmpRus(String str1, String str2) {
		int fullScale = str1.length();
		int ignoreChars = 1;
		float border = sumBetween(fullScale, ignoreChars, fullScale - ignoreChars);
		int sum = 0;
		int length = str1.length() > str2.length() ? str2.length() : str1.length();
		for (int i = 0; i < length; i++) {
			if (str1.charAt(i) == str2.charAt(i)) {
				sum += fullScale - i;
			}
		}
		return (float)sum > border;
	}
	
	private float sumBetween(int a, int b, int num) {
		return ((a + b) / 2) * num;
	}
}
