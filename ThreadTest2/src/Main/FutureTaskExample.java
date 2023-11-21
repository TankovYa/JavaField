package Main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

	ExecutorService executor;
	FutureTask<String>[] futureTask;
	Consumer[] consumers;
    private final int    THREAD_COUNT = 10;
    
    private boolean areTasksDone() {
    	boolean isDone = true;
    	for(int i = 0;i < THREAD_COUNT; i++) {
    		if(!futureTask[i].isDone()) {
    			isDone = false;
    			break;
    		}
    	}
    	return isDone;
    }
    
	public FutureTaskExample() {
		futureTask = new FutureTask[THREAD_COUNT];
		consumers = new Consumer[THREAD_COUNT];
		executor = Executors.newFixedThreadPool(THREAD_COUNT);
		
		Store store = new Store();
		for(int i=0;i<THREAD_COUNT;i++) {
			consumers[i] = new Consumer(store);
			System.out.println(consumers[i].count);
			futureTask[i] = new FutureTask<String>(consumers[i]);
			executor.submit(consumers[i]);
		}
		
		while(true) {
			if(areTasksDone()) {
				executor.shutdown();
				System.out.println("executor shutdown");
				return;
			}
			
		}
	}
}
