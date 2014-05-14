public class MetroSocialObserver {
  public static void main(String[] args) {
	  try {
		  // init scheme storage
		  SchemeStorage.getInstance().loadFromExternalStorage();
		  DynamicDataServer server = new DynamicDataServer();
		  Analyzer analyzer = new Analyzer();
		  
		  Thread exitHook = new ExitHookThread(server, analyzer);
		  Runtime.getRuntime().addShutdownHook(exitHook);
		  
		  server.start();
		  analyzer.start();
		  analyzer.waitWhileAlive();

		  Logger.notice("And shutting down");
	} catch (Exception e) {
		  Logger.error(e.toString());
		  e.printStackTrace();
	}
  }
}

