package Main;

public class Producer implements Runnable {
	public Producer(Store store) {
		this.store = store;
	}
	Store store;
	
	@Override
	public void run() {
		for(int i = 0;i<5;i++) {
			store.put();
		}
	}
} 