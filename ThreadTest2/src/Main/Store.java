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
	
	public String getItem(String item, Integer count) throws InterruptedException {
		
		while(map.get(item)<count) {
			System.out.printf("Товара %s нет на склад %s в кол-ве %d. \nСовершаем заказ товара...\n",item,name,count-map.get(item));
			putItem(item, count-map.get(item));
		}
		map.replace(item, map.get(item)-count);
		System.out.printf("\t\t-%d: товар %s забрали со склада %s. \n\t\tКол-во оставшевося товара: %d.\n",count,item,name,map.get(item));
		return (item + " " + count).toString();
	}
	
	public String putItem(String item, Integer count) throws InterruptedException {
		Integer n = map.get(item);
		for(int i=0;i<count;i++) {
			while(map.get(item)>=10) {
				System.out.printf("Товар %s переполнен... Ожидание\n",item);
			}
			map.replace(item, ++n);
			System.out.printf("\t+1 : товар %s доставили на склад %s, \n\t\tКол-во товара: %d.\n",item,name,map.get(item));
		}
		System.out.printf("Товар %s прибыл на склад %s в кол-ве: %d.\n",item,name,count);
		return (item + " " + count).toString();
	}
	
	public Store(String name) {
		this();
		this.name = name;
	}
	
	public Store() {
		CountS++;
		map = new ConcurrentHashMap<>();
		map.put("A", 0);
		map.put("B", 0);
		map.put("C", 0);
		this.name = "Store №" + CountS;
	}
}
