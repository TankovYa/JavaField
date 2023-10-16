package Main;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person("Bob", 1, null);
		Person p2 = new Person("Steve", 2, null);
		Person p3 = new Person("Tom", 1, null);
		Person person = new Person("Clone", 0, null);
		// Test equals and hashCode
		HashSet<Person> setPersons=new HashSet<Person>();
		setPersons.add(p1);
		setPersons.add(p2);
		setPersons.add(p3);
		System.out.print("\nHashCode:\n\tp1 = "+p1.hashCode()+"\n\tp2 = "+p2.hashCode()+"\n\tp3 = "+p3.hashCode());
		System.out.print("\np1&p2 = "+p1.equals(p2));
		System.out.print("\np1&p3 = "+p1.equals(p3));
		System.out.print("\np2&p3 = "+p2.equals(p3));
		System.out.print("\nHashSet:\n\t "+setPersons);
		//Test toString
		System.out.print("\ntoString:\n\tp1 = "+p1.toString()+"\n\tp2 = "+p2.toString()+"\n\tp3 = "+p3.toString());
		//Test clone
		p1.addRole("admin");
		person.addRole("writer");
		System.out.print("\nBefore Persons:\n\t"+p1.toString()+"\n\tClone "+person.toString());
		person= (Person) p1.clone();
		System.out.print("\nAfter Persons:\n\t"+p1.toString()+"\n\tClone "+person.toString());
		p1.addRole("Reader");
		System.out.print("\nUpdate person1:\n\t"+p1.toString()+"\n\tClone "+person.toString());
		person.setNumber(3);
		System.out.print("\nUpdate personClone:\n\t"+p1.toString()+"\n\tClone "+person.toString());
		p1.removeRole("admin");
		System.out.print("\nUpdate person1:\n\t"+p1.toString()+"\n\tClone "+person.toString());
		//Test Class
		Pet pet;
		String[] petStrings= {
				"Main.Cat",
				"Main.Dog",
				"Main.Man"
		};
		for (String name : petStrings) {
			try {
				System.out.print("\n\nClass "+name);
				Class<?> class1 = Class.forName(name);
				System.out.print("\nCreate Pet");
				pet=(Pet)class1.newInstance();
				System.out.print("\nnewInstance: "+pet.getClass());
				pet.voice();
			} catch (ClassNotFoundException e) {
				System.out.print("\nNotFoundClass "+name);
			}catch (InstantiationException e) {
				e.printStackTrace();
			}catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

}
