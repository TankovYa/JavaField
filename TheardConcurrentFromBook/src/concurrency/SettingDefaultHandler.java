package concurrency;

import java.util.concurrent.*;

class ExceptionThread implements Runnable{

	public void run() {
		throw new RuntimeException();
	}
	
}

public class SettingDefaultHandler {
	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}
}
