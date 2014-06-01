/**
 * @author dodev
 *
 */
public class TwitterSearchAnalyzerFactory implements IAnalyzerFactory {

	public IDataSource getDataSource() {
		return new TwitterSearchSource();
	}

	/* (non-Javadoc)
	 * @see IAnalyzerFactory#getDataPreprocessor()
	 */
	@Override
	public IDataPreprocessor getDataPreprocessor() {
		return new Twitter4jPreprocessor();
	}

	/* (non-Javadoc)
	 * @see IAnalyzerFactory#getDataProcessor()
	 */
	@Override
	public IDataProcessor getDataProcessor() {
		return new TemplateDetector();
	}

}
