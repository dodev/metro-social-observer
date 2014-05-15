import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class DynamicDataServer {
	private HttpServer _server;
	private int port;
	private HashMap<String, Integer> supportedSchemes;
	private IterationStorage responseCache;
	private static String jsonError1 = "{\"error\":{\"code\":1, \"text\":\"Scheme parameter is required\"}}";
	private static String jsonError2 = "{\"error\":{\"code\":2, \"text\":\"Scheme not found\"}}";
	private static String jsonError3 = "{\"error\":{\"code\":3, \"text\":\"Server not ready\"}}";
	
	public DynamicDataServer () throws IOException {
		this.port = Integer.parseInt(Configurator.getInstance().get("server-port"),10);
		InetSocketAddress addr = new InetSocketAddress(this.port);
	    this._server = HttpServer.create(addr, 0);
	    Scheme[] arr = SchemeStorage.getInstance().getSchemesArray();
	    this.supportedSchemes = new HashMap<String, Integer>();
	    
	    for (int i = 0; i < arr.length; i++) {
	    	this.supportedSchemes.put(arr[i].getName(), arr[i].getId());
	    }
	    
	    this.responseCache = IterationStorage.getInstance();
	}
	
	public void start() throws IOException {
	    this._server.createContext("/", new RequestHandler());
	    this._server.setExecutor(Executors.newCachedThreadPool());
	    this._server.start();
	    Logger.notice("Server is listening on port " + this.port);
	}
	
	public void stop() {
		Logger.notice("Starting server stopping procedure");
		this._server.stop(1);
		Logger.notice("Ending server stopping procedure");
	}
	
	private class RequestHandler implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			String timestamp = new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
			Logger.notice("[http-server]/" + timestamp + ": hangling request");
		    
	    	Map<String, Object> parameters = new HashMap<String, Object>();
	        URI requestedUri = exchange.getRequestURI();
	        String query = requestedUri.getRawQuery();
	        this.parseQuery(query, parameters);
	    	
	    	Headers responseHeaders = exchange.getResponseHeaders();
	    	responseHeaders.set("Content-Type", "application/json");
            responseHeaders.set("Access-Control-Allow-Origin", "*");

	    	exchange.sendResponseHeaders(200, 0);
	    	OutputStream responseBody = exchange.getResponseBody();
	    	
	    	if (parameters.containsKey("scheme")) {
	    		
	    		String schemeName = (String)parameters.get("scheme");
	    		Logger.notice("[http-server]/" + timestamp + ": fetching data for scheme " + schemeName);
	    		if (supportedSchemes.containsKey(schemeName)) {
	    			int schemeId = supportedSchemes.get(schemeName);
	    			String response = responseCache.getLatestResponseForId(schemeId);
	    			if (response != null) {
	    				responseBody.write("{\"warnings\":".getBytes());
	    				responseBody.write(response.getBytes());
	    				responseBody.write("}".getBytes());
	    			} else {
	    				responseBody.write(jsonError3.getBytes());
	    			}
	    		} else {
	    			responseBody.write(jsonError2.getBytes());
	    		}
	    	} else {
	    		responseBody.write(jsonError1.getBytes());
	    	}

	    	responseBody.close();
		    
		}
		
		private void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {
	         if (query != null) {
	             String pairs[] = query.split("[&]");

	             for (String pair : pairs) {
	                 String param[] = pair.split("[=]");

	                 String key = null;
	                 String value = null;
	                 if (param.length > 0) {
	                     key = URLDecoder.decode(param[0],
	                         System.getProperty("file.encoding"));
	                 }

	                 if (param.length > 1) {
	                     value = URLDecoder.decode(param[1],
	                         System.getProperty("file.encoding"));
	                 }

	                 if (parameters.containsKey(key)) {
	                     Object obj = parameters.get(key);
	                     if(obj instanceof List<?>) {
	                         List<String> values = (List<String>)obj;
	                         values.add(value);
	                     } else if(obj instanceof String) {
	                         List<String> values = new ArrayList<String>();
	                         values.add((String)obj);
	                         values.add(value);
	                         parameters.put(key, values);
	                     }
	                 } else {
	                     parameters.put(key, value);
	                 }
	             }
	         }
	    }
	}
}
