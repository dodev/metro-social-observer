import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpServer;

public class JsonServer {
	private HttpServer _server;
	
	public JsonServer () throws IOException {
		InetSocketAddress addr = new InetSocketAddress(8080);
	    this._server = HttpServer.create(addr, 0);	
	}
	
	public void start() throws IOException {
	    this._server.createContext("/", new RequestHandler());
	    this._server.setExecutor(Executors.newCachedThreadPool());
	    this._server.start();
	    System.out.println("Server is listening on port 8080" );
	}
	
	public void stop() {
		this._server.stop(0);
	}
	
}
