import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
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
		System.out.println("Starting server stopping procedure");
		this._server.stop(1);
		System.out.println("Ending server stopping procedure");
	}
	
	private class RequestHandler implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			System.out.println("hangling request");
			String requestMethod = exchange.getRequestMethod();
		    if (requestMethod.equalsIgnoreCase("GET")) {
		    	Headers responseHeaders = exchange.getResponseHeaders();
		    	responseHeaders.set("Content-Type", "text/plain");
		    	exchange.sendResponseHeaders(200, 0);

		    	OutputStream responseBody = exchange.getResponseBody();
		    	Headers requestHeaders = exchange.getRequestHeaders();
		    	Set<String> keySet = requestHeaders.keySet();
		    	Iterator<String> iter = keySet.iterator();
		    	while (iter.hasNext()) {
		    		String key = iter.next();
		    		List<String> values = requestHeaders.get(key);
		    		String s = key + " = " + values.toString() + "\n";
		    		responseBody.write(s.getBytes());
		    	}
		    	responseBody.close();
		    }
		}
	}
}
