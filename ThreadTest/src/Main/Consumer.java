package Main;

public class Consumer implements Runnable {
	public Consumer(Store store) {
		this.store = store;
	}

	Store store;
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			store.get();
		}
	}
}
