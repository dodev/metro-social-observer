import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IterationStorage {
	
	private static IterationStorage instance = null;
	
	public static IterationStorage getInstance() {
		if (instance == null) {
			instance = new IterationStorage();
		}
		
		return instance;
	}
	
	private List<Iteration> iterations;
	private HashMap<Integer, String> responseCache;
	
	private IterationStorage() {
		this.iterations = new ArrayList<Iteration>();
		this.responseCache = new HashMap<Integer, String>();	
	}
	
	public Iteration createIteration(Scheme scheme) {
		Iteration iteration = new Iteration(scheme);
		
		iterations.add(iteration);
		
		return iteration;
	}
	
	public void finishIteration(Iteration iteration) throws IOException {
		StringWriter writer = new StringWriter();
		iteration.getJsonResultArray().writeJSONString(writer);
		String respText = writer.toString();
		
		this.responseCache.put(iteration.getScheme().getId(), respText);
	}
}
