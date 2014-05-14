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
	private int isAliveCheckTime;
	private boolean isAlive;
	
	public Analyzer() {
		Configurator conf = Configurator.getInstance();
		this.workers = new ArrayList<Thread>();
		int sleepTime = Integer.parseInt(conf.get("sleep-time-ms"), 10);
		this.isAliveCheckTime = Integer.parseInt(conf.get("is-alive-check-time-ms"), 10);
		this.isAlive = false;
		
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
					
					// TODO: do work here
					
					Thread.sleep(sleepTime);
				}
			} catch (InterruptedException e) {
				Logger.notice("ending work for scheme " + this.scheme.getName());
			}
						
		}
		
	}
}
