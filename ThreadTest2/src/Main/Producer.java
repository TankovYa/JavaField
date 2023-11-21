package Main;

import java.util.Random;
import java.util.concurrent.Callable;

public class Producer implements Callable<String>{
	
	static int CountP = 0;

	Store store;
	
	public Producer(Store store) {
		CountP++;
		this.store = store;
	}

	@Override
	public String call() throws Exception {
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
		store.putItem(item,(random.nextInt(10)+1),this);
		return null;
	}
	
	public String call(String item,Integer count) throws Exception{
		store.putItem(item,count,this);
		return null;
	}
}
