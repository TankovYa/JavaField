package Main;

public class MainThread {

	public static void main(String[] args) {
		Store store = new Store();
		Producer producer =new Producer(store);
		Consumer consumer =new Consumer(store);
		//new Thread(consumer).start();
		//new Thread(producer).start();
		Thread pThread = new Thread(producer);
		Thread cThread = new Thread(consumer);
		pThread.setDaemon(true);
		cThread.setDaemon(true);
		cThread.start();
		pThread.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
		System.out.println("Главный поток закончился");
		System.exit(0);
	}

}
