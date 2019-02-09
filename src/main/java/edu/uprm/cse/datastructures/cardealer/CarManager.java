package edu.uprm.cse.datastructures.cardealer;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

@Path("/cars")
public class CarManager {
  private static CarComparator comp = new CarComparator();
  private static CircularSortedDoublyLinkedList<Car> cList = new CircularSortedDoublyLinkedList<>(comp);
  /**
   * 
   * @return all the current Cars in the CircularSortedDoublyLinkedList as an array of Car
   */
  @GET
  //@Produces(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_JSON)
  public Car[] getAllCars() {
    return cList.toArray(new Car[cList.size()]);
  }
  /**
   * return Car in list with id equals to that passed in parameter
   * @param id
   * @return Car or status response
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Car getCarByID(@PathParam("id") long id){
      //look for a Car index with id and call the method get(int index)
	  for(int i=0; i<cList.size();i++) {
		 if( cList.get(i).getCarId()== id) {
			return cList.get(i);
			
		 }
	  }
	
	    throw new NotFoundException(new JsonError("Error", "Customer " + id + " not found"));
  
  }
  /**
   * return Car in list with brand equals to that passed in parameter
   * @param brand
   * @return status response
   */
 @GET
 @Path("/brand/{brand}")
 //@Consumes("text/plain")
 @Produces(MediaType.APPLICATION_JSON)
 public Car[] getCarByBrand(@PathParam("brand") String brand) {
	   // ArrayList<Car> brands = new ArrayList<>();
	 CircularSortedDoublyLinkedList<Car> cList2 = new CircularSortedDoublyLinkedList<>(comp);
	    for(int i=0;i<cList.size();i++)
	    	if((cList.get(i).getCarBrand().toLowerCase()).equals(brand.toLowerCase()))
	    		cList2.add(cList.get(i));
	    		return cList2.toArray(new Car[cList2.size()]);
	    		    		
	
 }
  /**
   * return Car in list with year equals to that passed in parameter
   * @param carYear
   * @return
   */
  @GET
@Path("year/{year}")
  @Produces(MediaType.APPLICATION_JSON)
  public Car[] getCarByYear(@PathParam("year") long carYear){
      //look for a Car index with id and call the method get(int index)
	  CircularSortedDoublyLinkedList<Car> cList2 = new CircularSortedDoublyLinkedList<>(comp);
	  for(int i=0; i<cList.size();i++) {
		 if( cList.get(i).getCarYear()== carYear) {
			 cList2.add(cList.get(i));
	    		
			
		 }
	  }
	  return cList2.toArray(new Car[cList2.size()]);
	 
  
  }
  /**
   * add the car  passed as parameter to the current CircularSortedDoublyLinkedList
   * @param car
   * @return
   */
  @POST
  @Path("/add")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addCustomer(Car car){
  
    cList.add(car);
   
    return Response.status(201).build();

  }
  /**
   * update the car  with equals id of that passed as parameter
   * @param car
   * @return
   */
  @PUT
  @Path("{id}/update")
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateCar(Car car){
	 //looks for a Car index with id same as the car pass in the parameter and calls method remove(int index)
	  //then add the new car 
	  for(int i=0; i<cList.size();i++) {
			 if( cList.get(i).getCarId()==car.getCarId()) {
				 cList.remove(i);
				 cList.add(car);
				 return Response.status(Response.Status.OK).build();
			 }
			 
		  }
	  
	 return Response.status(Response.Status.NOT_FOUND).build();
 
  }
  /**
   * Delete Car with equals id of that passed in parameter
   * @param id
   * @return
   */
  @DELETE
  @Path("/{id}/delete")
  public Response deleteCar(@PathParam("id") long id){
	 //looks for a Car index with id and calls method remove(int index)
	  for(int i=0; i<cList.size();i++) {
			 if( cList.get(i).getCarId()==id) {
				 cList.remove(i);			
				 return Response.status(Response.Status.OK).build();
			 }
			 
		  }
	  
	 return Response.status(Response.Status.NOT_FOUND).build();
  }
    
}