/**
 * 
 */

/**
 * @author dodev
 *
 */
public class DummyFactory implements IAnalyzerFactory {

	/**
	 * A class for generating debugging instances of the analyzer architecture
	 */

	/* (non-Javadoc)
	 * @see IAnalyzerFactory#getDataSource()
	 */
	@Override
	public IDataSource getDataSource() {
//		return new TwitterSearchSource();
		return new DummySource();
	}

	/* (non-Javadoc)
	 * @see IAnalyzerFactory#getDataPreprocessor()
	 */
	@Override
	public IDataPreprocessor getDataPreprocessor() {
//		return new Twitter4jPreprocessor();
		return new DummyPreprocessor();
	}

	/* (non-Javadoc)
	 * @see IAnalyzerFactory#getDataProcessor()
	 */
	@Override
	public IDataProcessor getDataProcessor() {
		return new TemplateDetector();
	}

}
