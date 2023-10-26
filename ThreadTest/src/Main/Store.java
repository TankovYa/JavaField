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
				System.out.println("������ "+item+" ��� �� ������ � ���-�� "+count+"."
						+ "\n��������� ����� ������...");
				put(item, count-items.get(item));
				wait();
			} catch (InterruptedException e) {}
		}
		items.replace(item, items.get(item)-count);
		System.out.println("\t-"+count+": ����� "+item+" �������"
				+ "\n\t\t���-�� ����������� ������: " + items.get(item));
		notify();
	}
	
	public synchronized void put(String item,Integer count) {
		Integer n = items.get(item);
		for(int i=0;i<count;i++) {
			while(items.get(item)>=10) {
				try {
					System.out.println("����� "+item+" ����������... �������� ");
					wait();
				}catch (InterruptedException e) {}
			}
			items.replace(item, ++n);
			System.out.println("\t+1 : ����� "+item+" ���������"
					+ "\n\t\t���-�� ������: " + items.get(item));
		}
		System.out.println("����� "+item+" ������ �� ����� � ���-��: "+count);
		notify();
	}
	/*
	private int item = 0;
	public synchronized void get() {
		while(item < 1) {
			try {
				System.out.println("�������� ������... ��������");
				wait();
			}catch (InterruptedException e) {}
		}
		item--;
		System.out.println("\t-1: ����� �������"
				+ "\n\t\t���-�� ������: " + item);
		notify();
	}
	public synchronized void put() {
		while(item>=3) {
			try {
				System.out.println("����� ����������... ��������");
				wait();
			}catch (InterruptedException e) {}
		}
		item++;
		System.out.println("\t+1: ����� ���������"
				+ "\n\t\t���-�� ������: " + item);
		notify();
	}*/

	@Override
	public void run() {
		
	}
}
