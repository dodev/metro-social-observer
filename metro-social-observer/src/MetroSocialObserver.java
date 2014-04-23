import java.io.IOException;

public class MetroSocialObserver {
  public static void main(String[] args) {
	  
	  
	  try {
		  JsonServer server = new JsonServer();
		  server.start();
		  
		  Thread exitHook = new ExitHookThread(server);
		  Runtime.getRuntime().addShutdownHook(exitHook);
	} catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	}
  }
}

