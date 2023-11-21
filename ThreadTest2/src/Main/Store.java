package Main;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Store {

	static int CountS = 0;
	
	public ConcurrentHashMap<String, Integer> map;
	public String name;
	
	public boolean getItem(String item, Integer count, Callable<String> callable) {
		
		while(map.get(item)<count) {
			System.out.printf("������ %d ��� �� ����� %d � ���-�� %d."
					+ "\n��������� ����� ������...\n",item,name,count);
			putItem(item, count-map.get(item), callable);
		}
		map.replace(item, map.get(item)-count);
		System.out.printf("\t-%d: ����� %d ������� �� ������ %d"
				+ "\n\t\t���-�� ����������� ������: %d\n",count,item,name,map.get(item));
		return true;
	}
	
	public boolean putItem(String item, Integer count, Callable<String> callable) {
		Integer n = map.get(item);
		for(int i=0;i<count;i++) {
			while(map.get(item)>=10) {
				System.out.printf("�����  ����������... ��������\n",item);
			}
			map.replace(item, ++n);
			System.out.printf("\t+1 : ����� %d ��������� �� ����� %d"
					+ "\n\t\t���-�� ������: %d\n",item,name,map.get(item));
		}
		System.out.printf("����� %d ������ �� ����� %d � ���-��: %d",item,name,count);
		return true;
	}
	
	public Store(String name) {
		super();
		this.name = name;
	}
	
	public Store() {
		CountS++;
		map = new ConcurrentHashMap<>();
		this.name = "Store �" + CountS;
	}
}
