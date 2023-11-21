package Main;

import java.util.Random;
import java.util.concurrent.Callable;

public class Consumer implements Callable<String>{
	
	static int CountC = 0;

	Store store;
	
	public Consumer(Store store) {
		CountC++;
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
		store.getItem(item,random.nextInt(10)+1,this);
		return null;
	}
	
	public String call(String item,Integer count) throws Exception{
		store.getItem(item,count,this);
		return null;
	}
}
