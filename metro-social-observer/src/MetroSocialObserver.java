public class MetroSocialObserver {
  public static void main(String[] args) {
	  try {
		  // init scheme storage
		  SchemeStorage.getInstance().loadFromExternalStorage();
		  
		  DynamicDataServer server = new DynamicDataServer();
		  
		  Thread exitHook = new ExitHookThread(server);
		  Runtime.getRuntime().addShutdownHook(exitHook);
		  
		  server.start();
		  Logger.notice("Staring server and waiting 5sec");
		  Thread.sleep(50000);
		  
		  Logger.notice("Stopping server and waiting 5sec");
		  server.stop();
		  Logger.notice("And shutting down");
	} catch (Exception e) {
		  Logger.error(e.toString());
		  e.printStackTrace();
	}
  }
}

