/**
 * The main file of the application
 */


/**
 * @author dodev
 *
 */
public class MetroSocialObserver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// adds a handler that will terminate all the currently active modules before exiting the application
		Runtime.getRuntime().addShutdownHook(new ExitHookThread());
		
		System.out.println("waiting 5 seconds");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("and exiting");
	}
}
