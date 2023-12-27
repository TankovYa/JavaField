package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for(int i = 0; i < 5; i++ ) 
			executor.execute(new LiftOff());
		executor.shutdown();
	}

}
