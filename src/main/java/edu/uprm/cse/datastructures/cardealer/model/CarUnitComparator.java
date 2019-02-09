package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class CarUnitComparator implements Comparator<CarUnit>{
	public CarUnitComparator() {
		
	}
	/**
     * compares lastName and firstName of cu1 and cu2
    * return a negative number if cu2 is greater than cu1
     * return  0 if cu1 and cu2 are equal
     * return a positive number if cu2 is less than cu1
     */
	@Override
	public int compare(CarUnit cu1, CarUnit cu2) {
		// TODO Auto-generated method stub
		String s1 = cu1.getVin();
		String s2 =cu2.getVin();
		return (s1).compareTo(s2);
	}

}
