package Main;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person("Bob", 1, null);
		Person p2 = new Person("Steve", 2, null);
		Person p3 = new Person("Tom", 1, null);
		// Test equals and hashCode
		HashSet<Person> setPersons=new HashSet<Person>();
		setPersons.add(p1);
		setPersons.add(p2);
		setPersons.add(p3);
		
		System.out.print("\nHashCode\n\tp1 = "+p1.hashCode()+"\n\tp2 = "+p2.hashCode()+"\n\tp3 = "+p3.hashCode());
		System.out.print("\np1&p2 = "+p1.equals(p2));
		System.out.print("\np1&p3 = "+p1.equals(p3));
		System.out.print("\np2&p3 = "+p2.equals(p3));
		System.out.print("\nHashSet\n\t "+setPersons);
	}

}
