package Main;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable{
	
	static int CountC = 0;
	static Random random = new Random();
	
	int number;
	Store store;
	String item;
	Integer count;

	public Consumer(Store store, String item, Integer count) {
		this(store);
		this.item = item;
		this.count = count;
	}
	
	public Consumer(Store store) {
		CountC++;
		number = CountC;
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
		this.item = item;
		this.count = random.nextInt(10)+1;
		this.store = store;
	}

	@Override
	public void run() {
		String result = "Cosumer " + number +" заказал " + item + " " + count;
		System.out.println(result);
		result = "Cosumer " + number +"не получил";
		try {
			result = "Cosumer " + number +" получил " + store.getItem(item,count);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
