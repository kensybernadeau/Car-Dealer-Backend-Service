package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class CarComparator implements Comparator<Car>{

	public CarComparator(){
		
	}
	/**
     * compares Car c1 against Car c2
     * return a negative number if c2 is greater than C1
     * return  0 if c1 and c2 are equal
     * return a positive number if c2 is less than c1
	 */
	public int compare(Car c1, Car c2) {
		// TODO Auto-generated method stub
		String s1 = c1.getCarBrand()+ c1.getCarModel() + c1.getCarModelOption();
		String s2 = c2.getCarBrand()+ c2.getCarModel() + c2.getCarModelOption();
		return (s1).compareTo(s2);
	}


}
