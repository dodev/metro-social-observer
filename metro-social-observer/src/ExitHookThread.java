/**
 * 
 */

/**
 * @author dodev
 *
 */
public class ExitHookThread extends Thread {
	private DynamicDataServer dynamicServer;
	private Analyzer analyzer;
	
	public ExitHookThread (DynamicDataServer dServer, Analyzer analyzer) {
		this.dynamicServer = dServer;
		this.analyzer = analyzer;
	}
	
	public void run() {
		Logger.notice("shutting down http dynamic server");
		this.dynamicServer.stop();
		Logger.notice("shutting down http analyzer server");
		try {
			this.analyzer.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Logger.error(e.toString());
			e.printStackTrace();
		}
	}
}
