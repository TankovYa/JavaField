package Main;

public class Store {
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
	}
}
