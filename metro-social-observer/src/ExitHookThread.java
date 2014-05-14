/**
 * 
 */

/**
 * @author dodev
 *
 */
public class ExitHookThread extends Thread {
	private DynamicDataServer _jsonServer;
	
	public ExitHookThread (DynamicDataServer jsonServer) {
		this._jsonServer = jsonServer;
	}
	
	public void run() {
		Logger.notice("shutting down http json server");
		this._jsonServer.stop();
	}
}
