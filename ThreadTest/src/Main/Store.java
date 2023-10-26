package Main;

public class Store {
	private int item = 0;
	public synchronized void get() {
		while(item < 1) {
			try {
				System.out.println("Ожидание товара... Ожидание");
				wait();
			}catch (InterruptedException e) {}
		}
		item--;
		System.out.println("\t-1: товар забрали"
				+ "\n\t\tкол-во товара: " + item);
		notify();
	}
	public synchronized void put() {
		while(item>=3) {
			try {
				System.out.println("Склад переполнен... Ожидание");
				wait();
			}catch (InterruptedException e) {}
		}
		item++;
		System.out.println("\t+1: товар доставили"
				+ "\n\t\tкол-во товара: " + item);
		notify();
	}
}
