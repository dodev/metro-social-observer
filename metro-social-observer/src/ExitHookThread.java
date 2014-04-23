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
		System.out.println("shutting down http json server");
		this._jsonServer.stop();
	}
}
