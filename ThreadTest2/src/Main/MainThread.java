package Main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class MainThread {
	
	public static void main(String[] args) {
		Store store = new Store();
		Producer producer =new Producer(store);
		Consumer consumer =new Consumer(store);
		for(int i=0;i<10;i++) {
			
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		System.out.println(store.map);
	}

}
