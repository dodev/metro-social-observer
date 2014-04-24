/**
 * 
 */

/**
 * @author dodev
 *
 */
public class ExitHookThread extends Thread {
	private JsonServer _jsonServer;
	
	public ExitHookThread (JsonServer jsonServer) {
		this._jsonServer = jsonServer;
	}
	
	public void run() {
		Logger.notice("shutting down http json server");
		this._jsonServer.stop();
	}
}
