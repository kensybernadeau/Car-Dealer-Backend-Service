package edu.uprm.cse.datastructures.cardealer;

import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uprm.cse.datastructures.cardealer.model.Appointment;
import edu.uprm.cse.datastructures.cardealer.model.CarComparator;
import edu.uprm.cse.datastructures.cardealer.util.LinkedPositionalList;
import edu.uprm.cse.datastructures.cardealer.util.Position;
@Path("/appointment")
public class AppointmentManager {
	private static CarComparator comp = new CarComparator();
	private static LinkedPositionalList<Appointment>[] week = LPArray(5); //appList - appointment list array
	// creates linked positional array of length Days, so  you can have 5 days or an entire month 
	//this depending on the application
	private static LinkedPositionalList<Appointment>[] LPArray(int Days){
		@SuppressWarnings("unchecked")
		LinkedPositionalList<Appointment>[] appointments = new LinkedPositionalList[Days];
		for (int i = 0; i < appointments.length; i++) {
			appointments[i] = new LinkedPositionalList<Appointment>();
		}
		return appointments;
	}  


	@SuppressWarnings("unchecked")
	@GET
	//@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment[] getAllAppointments() {
		//arrayList where all the appointments in the array of LinkedPositionalList representing days 
		//are going to be transferred from each LinkedPositionalList(in weeks[]) to one single ArrayList of Appointments
		ArrayList<Appointment> appointmentsArrList = new ArrayList<Appointment>();
		//this loops traverse the days of the week which are LinkedPositionalLists
		for(int i=0; i<week.length;i++)
			//this iterators traverse the appointments in the current LinkedPositionalList of Appointment
			for (Appointment a : week[i]) { 
				appointmentsArrList.add(a);
			}

		// converts the ArrayList into an Array of Appointments and return it
		return appointmentsArrList.toArray(new Appointment[appointmentsArrList.size()]);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment getAppointment(@PathParam("id") long id){
		//first loop iterates over the 5 list in the array
		//inner loop iterates over the elements of type appointment
		//in each LinkedPositionalList 
		for(int i=0; i<week.length;i++) {		 

			//iterates over the positions of each LinkedPositiona list and compare the positions elements(Appointments)  AppoinmentId's 
			// to the id pass through the param if match return the position element of type Appointment
			for(Appointment a:week[i]) {
				// System.out.println("day: " + i + " Appointment: " + a); // for testing purposes
				// System.out.println(a.getAppointmentId()==id);           //for testing purposes
				if(a.getAppointmentId()==id)
					return a;			  }
		}


		throw new NotFoundException(new JsonError("Error", "Appointment " + id + " not found"));

	}

	//new method for search appointment by day 
	@SuppressWarnings("unchecked")
	@GET
	@Path("/day/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment[] getAppointment(@PathParam("day") int day){
        //the next conditionals convert the string of day to an int that represent the day in the array of LinkedPositionalList<Appointment> week
//		int dayIndex=-1;
//		if((day.toLowerCase()).equals("monday")) {
//			dayIndex=0;
//			//System.out.println("monday");
//		}
//
//		if((day.toLowerCase()).equals("tuesday")) {
//			dayIndex=1;
//			// System.out.println("tuesday");
//		}
//		else if((day.toLowerCase()).equals("wednesday")) {
//			dayIndex=2;
//			// System.out.println("wednesday");
//		}
//		else if((day.toLowerCase()).equals("thursday")) {
//			dayIndex=3;
//			// System.out.println("thursday");
//		}
//		else if((day.toLowerCase()).equals("friday")) {
//			dayIndex=4;
//			// System.out.println("friday");
//		}
//		else if(!(dayIndex>=0||dayIndex<=4)) {
//            if(Integer.parseInt(day)>=0&&Integer.parseInt(day)<=4)
//			dayIndex=Integer.parseInt(day);
//			
//		}
//		else if(dayIndex<0|| dayIndex>4)	    throw new NotFoundException(new JsonError("Error", "Appointments in " + day + " not found"));

		//arrayList where all the appointments in the array of LinkedPositionalList representing the day send as parameter
		//are going to be transferred from the respective LinkedPositionalList(in weeks[]) to the ArrayList of Appointments
		ArrayList<Appointment> appointmentsArrList = new ArrayList<Appointment>();
		for (Appointment a : week[day]) { 
			appointmentsArrList.add(a);
		}

		// converts the ArrayList into an Array of Appointments and return it
		return appointmentsArrList.toArray(new Appointment[appointmentsArrList.size()]);	  
	}




	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointment(Appointment appointment){
		//the conditionals find the corresponding day of the appointment and
		//always add at the last position so the first position is the first element to be traverse by the iterator
		if((appointment.getDay())==0) {  
		
			week[0].addLast(appointment);
			// System.out.println("add in monday"); //for testing purposes

			return Response.status(201).build();// always add after the last position in the current list
		}
		else if((appointment.getDay())==1 ) { 
			
			week[1].addLast(appointment);
			// System.out.println("add in tuesday");
			return Response.status(201).build();
		}
		else if( (appointment.getDay())==2) { 
			
			week[2].addLast(appointment);
			// System.out.println("add in wednesday");
			return Response.status(201).build();
		}
		else  if((appointment.getDay())==3)  {
			
			week[3].addLast(appointment);
			//  System.out.println("add in thursday"); 
			return Response.status(201).build();
		}
		else if((appointment.getDay())==4 )  {
		
			week[4].addLast(appointment);
			//  System.out.println("add in friday");
			return Response.status(201).build();
		}

		else throw new NotFoundException(new JsonError("Error", "Appointment " + appointment + " not added"));



	}
	
	@POST
	@Path("/add/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointment(Appointment appointment,@PathParam("day") int day){
		//the conditionals find the corresponding day of the appointment and
		//always add at the last position so the first position is the first element to be traverse by the iterator
	     //conditionals to set the instance of the day of the appointment 
		 if(day==0) appointment.setDay(0);
		 else if(day==1) appointment.setDay(1);
		 else if(day==2) appointment.setDay(2);
		 else if(day==3) appointment.setDay(3);
		 else if(day==4) appointment.setDay(4);
			
		 week[day].addLast(appointment);
			
			return Response.status(201).build();	

	    

	}


	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAppointment( Appointment appointment){

		for(int i=0;i<week.length;i++) {

			for(Position<Appointment> a : week[i].positions()) {
				//find the element with the id of the appointment passed in parameter if the day is not changed
				// it sets the urrent position instances to those of the appointment passed in parameter
				if((a.getElement()).getAppointmentId()==appointment.getAppointmentId() && ((a.getElement()).getDay())==(appointment.getDay())) {					  				  
					week[i].set(a, appointment);
					return Response.status(Response.Status.OK).build();
				}
				//if the day is changed removes position and add the new appointment in the corresponding day
				else if(!(((a.getElement()).getDay())==(appointment.getDay()))) {
					week[i].remove(a);
					this.addAppointment(appointment);
					return Response.status(Response.Status.OK).build();
				}
			}
		}


		return Response.status(Response.Status.NOT_FOUND).build();

	}

	@SuppressWarnings("unchecked")
	@DELETE
	@Path("/{id}/delete")
	public Response deleteAppointment(@PathParam("id") long id){
		boolean flag =false;
		//iterates over the days(LinkedPositionalList<Appointment>) of the array week
		for(int i=0;i<week.length;i++) {
			//iterates over the positions in each day of the array week until it finds the id pass as parameter
			for(Position<Appointment> a:((LinkedPositionalList<Appointment>) week[i]).positions()) {
				if(a.getElement().getAppointmentId()==id) {
					//once the appointment with the id is find it is removed
					((LinkedPositionalList<Appointment>) week[i]).remove(a);
					flag=true;
				} 
			}
		}
		if(flag) {
			return Response.status(Response.Status.OK).build();
		}
		else
			return Response.status(Response.Status.NOT_FOUND).build();
	}

}
