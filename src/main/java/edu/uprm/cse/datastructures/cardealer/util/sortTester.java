package edu.uprm.cse.datastructures.cardealer.util;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarComparator;

public class sortTester<E> {

	   public static void main(String[] args)  {
			Car acar = new Car(1,"Toyota","Rav","4LE",50000,12000);
			Car bcar = new Car(2,"Toyota","Rav","4LE",50000,12000);
			Car ccar = new Car(3,"Toyota","Rav","4ZZ",50000,10000);
			Car dcar = new Car(4,"Toyota","Rav","4XX",50000,11000);
			CircularSortedDoublyLinkedList<Car> list = new CircularSortedDoublyLinkedList<Car>(new CarComparator());
			list.add(acar);
		list.add(bcar);
			list.add(ccar);
//			list.add(dcar);
//			list.add(ccar);
		
			list.remove(1);
			
			System.out.println(list.size());
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).toString());
			}
	
			
	    }
	
	 
}
