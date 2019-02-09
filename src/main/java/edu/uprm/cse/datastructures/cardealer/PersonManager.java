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

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarComparator;
import edu.uprm.cse.datastructures.cardealer.model.Person;
import edu.uprm.cse.datastructures.cardealer.model.PersonComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
@Path("/person")
public class PersonManager {
	 private static PersonComparator comp = new PersonComparator();
	  private static CircularSortedDoublyLinkedList<Person> cList = new CircularSortedDoublyLinkedList<>(comp);
      /**
       * return all the current persons on  a list
       * @return an array of Person that were stored on the CircularDoublyLinkedList<Person> cList
       */
	  @GET
	  //@Produces(MediaType.APPLICATION_XML)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Person[] getAllPersons() {
	    return cList.toArray(new Person[cList.size()]);
	  }
	  /**
	   * search for persons with lastName in all appointments
	   * @param lastName 
	   * @return array of Person with equal lastName
	   */
	  @GET
	  @Path("lastname/{lastName}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Person[] getPerson(@PathParam("lastName")String lastName) {
		  CircularSortedDoublyLinkedList<Person> cList2 = new CircularSortedDoublyLinkedList<>(comp);
		    for(int i=0;i<cList.size();i++)
		    	if((cList.get(i).getLastName().toLowerCase()).equals(lastName.toLowerCase()))
		    		cList2.add(cList.get(i));
		    		return cList2.toArray(new Person[cList2.size()]);
	  }
      /**
       * Search for persons id equal to is pass as parameter
       * @param id
       * @return Person with id 
       */
	  @GET
	  @Path("{id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Person getPerson(@PathParam("id") long id){
	      //look for a Person index with id and call the method get(int index)
		  for(int i=0; i<cList.size();i++) {
			 if( cList.get(i).getPersonId()== id) {
				
				return cList.get(i);
				
			 }
			 }
		
		    throw new NotFoundException(new JsonError("Error", "Customer " + id + " not found"));
	  
	  }
	  /**
	   * Adds Person to the list sorted by lastName and firstName
	   * @param person
	   * @return status response
	   */
	  @POST
	  @Path("/add")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response addPerson(Person person){
	      boolean alreadyInList=false;
	      for(int i=0; i<cList.size();i++) 
				 if( cList.get(i).getPersonId()== person.getPersonId()) alreadyInList=true;
		 else alreadyInList=false;
	     
	       if(!alreadyInList) 
	    	   cList.add(person);	      
	     return  Response.status(201).build();
	       
	  }
	  /**
	   * Update person with equal id of person passed as parameter
	   * @param person
	   * @return status response
	   */
	  @PUT
	  @Path("{id}/update")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response updatePerson(Person person){
		 //looks for a Person index with id same as the car pass in the parameter and calls method remove(int index)
		  //then add the new car 
		  for(int i=0; i<cList.size();i++) {
				 if( cList.get(i).getPersonId()==person.getPersonId()) {
					 cList.remove(i);
					 cList.add(person);
					 return Response.status(Response.Status.OK).build();
				 }
				 
			  }
		  
		  return Response.status(Response.Status.NOT_FOUND).build();
	 
	  }
	  /**
	   * Delete person with equal id of that passed as parameter
	   * @param id
	   * @return status response
	   */
	  @DELETE
	  @Path("/{id}/delete")
	  public Response deletePerson(@PathParam("id") long id){
		 //looks for a Person index with id and calls method remove(int index)
		  for(int i=0; i<cList.size();i++) {
				 if( cList.get(i).getPersonId()==id) {
					 cList.remove(i);			
					 return Response.status(Response.Status.OK).build();
				 }
				 
			  }
		  
		 return Response.status(Response.Status.NOT_FOUND).build();
	  }

}
