import java.util.HashMap;

/**
 * 
 */

/**
 * @author dodev
 *
 */
public class TemplateDetector implements IDataProcessor {

	private AbstractTemplate[] templates;
	private int threshold;
	/**
	 * 
	 */
	public TemplateDetector() {
		this.templates = new AbstractTemplate[]{};
		this.threshold = 0;
	}

	/**
	 * @see IDataProcessor#executeOn(Scheme, Document[])
	 */
	@Override
	public void executeOn(Scheme scheme, Document[] collection) {
		HashMap<Integer, Candidate> candidates = new HashMap<Integer, Candidate>();
		for (AbstractTemplate templ: this.templates) {
			for (Document doc: collection) {
				if (templ.match(doc.getText())) {
					ISchemeObject[] objects = templ.extractObjects(doc.getText(), scheme);
					for (ISchemeObject o: objects) {
						int hash = getCandidateHash(o.getId(), templ.getDelta());
						if (candidates.containsKey(hash)) {
							candidates.get(hash).changeRating(doc.getRating());
						} else {
							candidates.put(hash, new Candidate(o, doc.getRating(), templ.getDelta()));
						}
					}		
				}
			}
		}
		
		for (Candidate candidate: candidates.values()) {
			if (candidate.getRating() >= this.threshold) {
				candidate.getSchemeObject().setWarningLevel(candidate.getWarningDelta());
			}
		}
	}
	
	private int getCandidateHash(int id, int delta) {
		if (delta < 0) {
			id = id * -1;
		}
		
		return delta * 1000 + id;
	}

}
