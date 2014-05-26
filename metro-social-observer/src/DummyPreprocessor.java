import java.util.ArrayList;

/**
 * 
 */

/**
 * @author dodev
 *
 */
public class DummyPreprocessor implements IDataPreprocessor {

	/**
	 * 
	 */
	/* (non-Javadoc)
	 * @see IDataPreprocessor#executeOn(java.lang.Object[])
	 */
	@Override
	public Document[] executeOn(Object[] rawDocuments) {
		ArrayList<Document> list = new ArrayList<Document>();
		
		for(Object o: rawDocuments) {
			String text = (String)o;
			
			list.add(new Document(text, null, 0));
		}
		
		Document[] example = {};
		return list.toArray(example);
	}

}
