/**
 * 
 */
import org.json.simple.*;

/**
 * @author dodev
 *
 */
public class Iteration {
	
	private Object[] rawCollection;
	/**
	 * @return the rawCollection
	 */
	public Object[] getRawCollection() {
		return rawCollection;
	}

	/**
	 * @param rawCollection the rawCollection to set
	 */
	public void setRawCollection(Object[] rawCollection) {
		this.rawCollection = rawCollection;
	}

	/**
	 * @return the collection
	 */
	public Document[] getCollection() {
		return collection;
	}

	/**
	 * @param collection the collection to set
	 */
	public void setCollection(Document[] collection) {
		this.collection = collection;
	}

	/**
	 * @return the results
	 */
	public Warning[] getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(Warning[] results) {
		this.results = results;
		this.timeFinished = System.currentTimeMillis() / 1000L;
		Logger.notice("Iteration finished at " + this.timeFinished);
	}

	/**
	 * @return the timeStarted
	 */
	public long getTimeStarted() {
		return timeStarted;
	}

	/**
	 * @return the timeFinished
	 */
	public long getTimeFinished() {
		return timeFinished;
	}

	private Document[] collection;
	private Warning[] results;
	
	private long timeStarted;
	private long timeFinished;
	
	private Scheme scheme;
	
	/**
	 * @return the scheme
	 */
	public Scheme getScheme() {
		return scheme;
	}

	public Iteration(Scheme sc) {
		this.scheme = sc;
		this.timeStarted = System.currentTimeMillis() / 1000L;
		Logger.notice("Iteration started at " + this.timeStarted);
	}

	public JSONArray getJsonResultArray() {
		JSONArray arr = new JSONArray();
		
		for (int i = 0; i < this.results.length; i++) {
			arr.add(this.results[i].getJsonObject());
		}
		
		return arr;
	}
}
