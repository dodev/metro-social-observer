public class MetroSocialObserver {
  public static void main(String[] args) {
	  try {
		  DynamicDataServer server = new DynamicDataServer();
		  
		  Thread exitHook = new ExitHookThread(server);
		  Runtime.getRuntime().addShutdownHook(exitHook);
		  
		  server.start();
		  Logger.notice("Staring server and waiting 5sec");
		  Thread.sleep(5000);
		  
		  Logger.notice("Stopping server and waiting 5sec");
		  server.stop();
		  Thread.sleep(5000);
		  Logger.notice("And shutting down");
	} catch (Exception e) {
		  Logger.error(e.toString());
		  e.printStackTrace();
	}
  }
}

