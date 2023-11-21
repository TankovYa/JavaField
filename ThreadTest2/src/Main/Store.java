package Main;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;

public class Store {

	static int CountS = 0;
	
	public ConcurrentHashMap<String, Integer> map;
	public String name;
	
	public String getItem(String item, Integer count, Condition condition) throws InterruptedException {
		
		while(map.get(item)<count) {
			System.out.printf("Товара %d нет на склад %d в кол-ве %d."
					+ "\nСовершаем заказ товара...\n",item,name,count-map.get(item));
			putItem(item, count-map.get(item), condition);
			condition.await();
		}
		map.replace(item, map.get(item)-count);
		System.out.printf("\t-%d: товар %d забрали со склада %d"
				+ "\n\t\tкол-во оставшевося товара: %d\n",count,item,name,map.get(item));
		return (item + " " + count).toString();
	}
	
	public String putItem(String item, Integer count, Condition condition) throws InterruptedException {
		Integer n = map.get(item);
		for(int i=0;i<count;i++) {
			while(map.get(item)>=10) {
				System.out.printf("Товар %d переполнен... Ожидание\n",item);
			}
			map.replace(item, ++n);
			System.out.printf("\t+1 : товар %d доставили на склад %d"
					+ "\n\t\tкол-во товара: %d\n",item,name,map.get(item));
		}
		System.out.printf("Товар %d прибыл на склад %d в кол-ве: %d",item,name,count);
		condition.signal();
		return (item + " " + count).toString();
	}
	
	public Store(String name) {
		this();
		this.name = name;
	}
	
	public Store() {
		CountS++;
		map = new ConcurrentHashMap<>();
		this.name = "Store №" + CountS;
	}
}
