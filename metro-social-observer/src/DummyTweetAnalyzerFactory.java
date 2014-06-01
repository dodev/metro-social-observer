/**
 * @author dodev
 *
 */
public class DummyTweetAnalyzerFactory implements IAnalyzerFactory {


	/* (non-Javadoc)
	 * @see IAnalyzerFactory#getDataSource()
	 */
	@Override
	public IDataSource getDataSource() {
		return new DummyTweetSource();
	}

	/* (non-Javadoc)
	 * @see IAnalyzerFactory#getDataPreprocessor()
	 */
	@Override
	public IDataPreprocessor getDataPreprocessor() {
		return new DummyTweetPreprocessor();
	}

	/* (non-Javadoc)
	 * @see IAnalyzerFactory#getDataProcessor()
	 */
	@Override
	public IDataProcessor getDataProcessor() {
		return new TemplateDetector();
	}

}
