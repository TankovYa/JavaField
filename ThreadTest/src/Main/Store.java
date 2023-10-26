package Main;

import java.util.HashMap;

public class Store implements Runnable {
	HashMap<String, Integer> items = new HashMap<>();
	public Store() {
		items.put("A", 0);
		items.put("B", 0);
		items.put("C", 0);
	}

	
	public synchronized void get(String item,Integer count) {
		while(items.get(item)<count) {
			try {
				System.out.println("Товара "+item+" нет на складе в кол-ве "+count+"."
						+ "\nСовершаем заказ товара...");
				put(item, count-items.get(item));
				wait();
			} catch (InterruptedException e) {}
		}
		items.replace(item, items.get(item)-count);
		System.out.println("\t-"+count+": товар "+item+" забрали"
				+ "\n\t\tкол-во оставшевося товара: " + items.get(item));
		notify();
	}
	
	public synchronized void put(String item,Integer count) {
		Integer n = items.get(item);
		for(int i=0;i<count;i++) {
			while(items.get(item)>=10) {
				try {
					System.out.println("Склад "+item+" переполнен... Ожидание ");
					wait();
				}catch (InterruptedException e) {}
			}
			items.replace(item, ++n);
			System.out.println("\t+1 : товар "+item+" доставили"
					+ "\n\t\tкол-во товара: " + items.get(item));
		}
		System.out.println("Товар "+item+" прибыл на склад в кол-ве: "+count);
		notify();
	}
	/*
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
	}*/

	@Override
	public void run() {
		
	}
}
