package Main;

import java.util.ArrayList;
import java.util.Objects;

public class Person implements Cloneable {
	public Person(String name, int number, ArrayList<String> role) {
		this.name = name;
		this.number = number;
		if(role!=null)
			for (String string : role) {
				this.role.add(string);
			}
	}
	
	public String name;
	public int number;
	public ArrayList<String> role= new ArrayList<>();
	
	public void addRole(String r) {
		role.add(r);
	}
	
	public void removeRole(String r) {
		role.remove(r);
	}
	
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(CloneNotSupportedException e){ 
	        return null; 
	    }
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return number == other.number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public ArrayList<String> getRole() {
		return role;
	}
	public void setRole(ArrayList<String> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", number=" + number + ", role=" + role + "]";
	}
}
