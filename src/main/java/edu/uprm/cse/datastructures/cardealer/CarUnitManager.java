package edu.uprm.cse.datastructures.cardealer;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uprm.cse.datastructures.cardealer.model.CarUnit;
import edu.uprm.cse.datastructures.cardealer.model.CarUnitComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
@Path("/carunit")
public class CarUnitManager {
	
	 private static CarUnitComparator comp = new CarUnitComparator();
	  private static CircularSortedDoublyLinkedList<CarUnit> cList = new CircularSortedDoublyLinkedList<>(comp);
      /**
       * 
       * @return all the current carUnit on the CircularSortedDoublyLinkedList
       */
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public CarUnit[] getAllCarUnits() {
		  //convert the circularSortedDoublyLinkedList into an array of CarUnit an return it
	    return cList.toArray(new CarUnit[cList.size()]);
	  }
      /**
       * 
       * @param id of the carUnit
       * @return the carUnit with id equals to id passed as parameter
       */
	  @GET
	  @Path("{id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public CarUnit getCarUnit(@PathParam("id") long id){
	      //look for a CarUnit index with id and call the method get(int index)
		  for(int i=0; i<cList.size();i++) {
			 if( cList.get(i).getCarUnitId()== id) {
				return cList.get(i);
				
			 }
		  }
		
		    throw new NotFoundException(new JsonError("Error", "Customer " + id + " not found"));
	  
	  }
	  /**
	   * add the car unit passed as parameter to the current CircularSortedDoublyLinkedList
	   * @param carUnit 
	   * @return status response
	   */
	  @POST
	  @Path("/add")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response addCarUnit(CarUnit carUnit){
	  
	   cList.add(carUnit);
	   System.out.println("carUnit added " + carUnit);
	    return Response.status(201).build();
	 
	  
	  }
      /**
       * update the CarUnit in the list with id equals to of the CarUnit passed as parameter
       * @param carUnit
       * @return
       */
	  @PUT
	  @Path("/{id}/update")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response updateCarUnit(CarUnit carUnit){
		 //looks for a CarUnit index with id same as the carUnit pass in the parameter and calls method remove(int index)
		  //then add the modified carUnit 
		  for(int i=0; i<cList.size();i++) {
				 if( cList.get(i).getCarUnitId()==carUnit.getCarUnitId()) {
					 cList.remove(i);
					 cList.add(carUnit);
					 return Response.status(Response.Status.OK).build();
				 }
				 
			  }
		  
		 return Response.status(Response.Status.NOT_FOUND).build();
	 
	  }
	  /**
	   * Deletes CarUnit with id equals to the id passed as parameter
	   * @param id
	   * @return status response
	   */
	  @DELETE
	  @Path("/{id}/delete")
	  public Response deleteCarUnit(@PathParam("id") long id){
		 //looks for a CarUnit index with id and calls method remove(int index)
		  for(int i=0; i<cList.size();i++) {
			  System.out.println( cList.get(i).getCarUnitId()==id);
				 if( cList.get(i).getCarUnitId()==id) {
					 cList.remove(i);			
					 return Response.status(Response.Status.OK).build();
				 }
				 
			  }
		  
		 return Response.status(Response.Status.NOT_FOUND).build();
	  }

}
