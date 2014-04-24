public class MetroSocialObserver {
  public static void main(String[] args) {
	  try {
		  JsonServer server = new JsonServer();
		  
		  Thread exitHook = new ExitHookThread(server);
		  Runtime.getRuntime().addShutdownHook(exitHook);
		  
		  server.start();
		  System.out.println("Staring server and waiting 5sec");
		  Thread.sleep(5000);
		  
		  System.out.println("Stopping server and waiting 5sec");
		  server.stop();
		  Thread.sleep(5000);
		  System.out.println("And shutting down");
	} catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	}
  }
}

