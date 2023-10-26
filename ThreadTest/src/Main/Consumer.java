package Main;

import java.util.Random;

public class Consumer implements Runnable {
	public Consumer(Store store) {
		this.store = store;
	}

	Store store;
	
	@Override
	public void run() {
		Random random = new Random();
		String item = "A";
		switch(random.nextInt(3)) {
		case 0:
			item="A";
			break;
		case 1:
			item="B";
			break;
		case 2:
			item="C";
			break;
		default:
			item="A";
			break;
	}
		store.get(item,random.nextInt(10)+1);
		/*for(int i=0;i<5;i++) {
			store.get();
		}*/
	}
}
