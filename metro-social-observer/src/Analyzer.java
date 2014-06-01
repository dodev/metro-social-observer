import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Analyzer implements the infrastructure for the the data processing modules
 */

/**
 * @author dodev
 *
 */
public class Analyzer {

	/**
	 * Storage representation properties
	 */	
	private SchemeStorage schemeStorage;
	private IterationStorage iterationStorage;
	
	/**
	 * Workflow control properties
	 */
	private ArrayList<Thread> workers;
	private int isAliveCheckTime;
	private boolean isAlive;
	
	/**
	 * The IAnalyzerFactory class family
	 */
	private IAnalyzerFactory factory;
	private IDataSource dataSource;
	private IDataPreprocessor preprocessor;
	private IDataProcessor processor;
	
	public Analyzer() {
		Configurator conf = Configurator.getInstance();
		this.iterationStorage = IterationStorage.getInstance();
		
		this.workers = new ArrayList<Thread>();
		int sleepTime = Integer.parseInt(conf.get("sleep-time-ms"), 10);
		this.isAliveCheckTime = Integer.parseInt(conf.get("is-alive-check-time-ms"), 10);
		this.isAlive = false;
		
		//this.factory = new DummyFactory();
		//this.factory = new TwitterSearchAnalyzerFactory();
		this.factory = new DummyTweetAnalyzerFactory();
		this.dataSource = factory.getDataSource();
		this.preprocessor = factory.getDataPreprocessor();
		this.processor = factory.getDataProcessor();
		
		this.schemeStorage = SchemeStorage.getInstance();
		Scheme[] schemes = this.schemeStorage.getSchemesArray();
		
		for (int i = 0; i < schemes.length; i++) {
			workers.add(new Thread(new Worker(schemes[i], sleepTime)));
		}	
	}
	
	public void start(){
		for (Thread worker: this.workers) {
			worker.start();
		}
		this.isAlive = true;
	}
	
	public void stop() throws InterruptedException {
		for (Thread worker: this.workers) {
			worker.interrupt();
			worker.join();
		}
		this.isAlive = false;
	}
	
	public void waitWhileAlive() throws InterruptedException {
		while(this.isAlive) {
			Thread.sleep(this.isAliveCheckTime);
		}
	}
	
	private class Worker implements Runnable {
		
		private Scheme scheme;
		private int sleepTime;
		
		public Worker(Scheme sc, int st) {
			this.scheme = sc;
			this.sleepTime = st;
		}

		@Override
		public void run() {
			try {
				while (true) {
					Logger.notice("getting some work done for scheme " + this.scheme.getName());
					
					Iteration iteration = iterationStorage.createIteration(this.scheme);
					
					Object[] rawDocs = dataSource.executeRequest(this.scheme);
					iteration.setRawCollection(rawDocs);
					
					Document[] collection = preprocessor.executeOn(rawDocs);
					iteration.setCollection(collection);
					
					processor.executeOn(scheme, collection);
					Warning[] results = this.traverseScheme();
					iteration.setResults(results);
					
					try {
						iterationStorage.finishIteration(iteration);
					} catch (IOException e) {
						Logger.error(e.toString());
						e.printStackTrace();
					}
					
					Thread.sleep(sleepTime);
				}
			} catch (InterruptedException e) {
				Logger.notice("ending work for scheme " + this.scheme.getName());
			}			
		}
		
		private Warning[] traverseScheme() {
			ArrayList<Warning> res = new ArrayList<Warning>();
			
			ArrayList<Line> lines = scheme.getLines();
			for (Line line: lines) {
				if (line.getWarningLevel() > 0) {
					res.add(new Warning("line", line.getId(), line.getWarningLevel()));
				}
			}
			
			Collection<Link> links = scheme.getLinks();
			for (Link link: links) {
				if (link.getWarningLevel() > 0) {
					res.add(new Warning("link", link.getId(), link.getWarningLevel()));
				}
			}
			
			Collection<Station> stations = scheme.getStations();
			for (Station station: stations) {
				if (station.getWarningLevel() > 0) {
					res.add(new Warning("station", station.getId(), station.getWarningLevel()));
				}
			}
			
			Warning[] a = {};
			return res.toArray(a);
		}
	}
}
