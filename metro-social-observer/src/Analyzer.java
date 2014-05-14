import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author dodev
 *
 */
public class Analyzer {

	/**
	 * 
	 */
	
	private ArrayList<Thread> workers;
	private SchemeStorage schemeStorage;
	private IterationStorage iterationStorage;
	private int isAliveCheckTime;
	private boolean isAlive;
	
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
		
		this.factory = new DummyFactory();
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
					
					// TODO: add error handling
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
			// TODO: create scheme object traversal procedure
			Warning[] a = {};
			return res.toArray(a);
		}
	}
}
