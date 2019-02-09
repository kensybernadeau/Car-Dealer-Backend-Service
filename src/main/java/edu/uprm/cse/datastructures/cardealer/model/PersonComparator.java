package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person>{
    public PersonComparator() {
    	
    }
    /**
     * compares lastName and firstName of p1 and p1
     * return a negative number if p2 is greater than p1
     * return  0 if p1 and p2 are equal
     * return a positive number if p2 is less than p1
     */
	@Override
	public int compare(Person p1, Person p2) {
		// TODO Auto-generated method stub
		
		String s1 = p1.getLastName() + p1.getFirstName();
		String s2 = p2.getLastName() + p2.getFirstName();
		return (s1).compareTo(s2);
	}

}
