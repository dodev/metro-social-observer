/**
 * 
 */

/**
 * @author dodev
 *
 */
public interface IAnalyzerFactory {
	IDataSource getDataSource();
	
	IDataPreprocessor getDataPreprocessor();
	
	IDataProcessor getDataProcessor();
}
