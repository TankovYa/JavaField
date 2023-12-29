package paralelThread;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();
	
	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock(): " + captured);
		} finally {
			if(captured)
				lock.unlock();
		}
	}
	
	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
		try {
			System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
		} finally {
			if(captured)
				lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final AttemptLocking al = new AttemptLocking();
		al.untimed(); // True -- блокировка доступна
		al.timed(); // True -- блокировка доступна
		// теперь создаем отдельную задачу для получения блокировки:
		new Thread() {
			{ setDaemon(true); }
			public void run() {
				al.lock.lock();
				System.out.println("acquired");
			}
		}.start();
		Thread.yield(); // Предоставляем возможность 2-й задаче
		al.untimed(); // False -- блокировка захвачена задачей
		al.timed(); // False -- блокировка захвачена задачей
	}
}
