package edu.uprm.cse.datastructures.cardealer.model;
//An appointment indicates that a given Appointment will be serviced on a given year.
public class Appointment {
	private long appointmentId; // internal id of the appointment
	private long carUnitId; // id of the car to be serviced
	private String job; // description of the job to be done (i.e.: “oil change”)
	private double bill; // cost of the service (initially 0).
//	private String day; // changed to int after recieving testers for proyect
	private int day;
    //getters
	public long getAppointmentId() {
		return appointmentId;
	}
	public long getCarUnitId() {
		return carUnitId;
	}
	public String getJob() {
		return job;
	}
	public double getBill() {
		return bill;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day=day;
	}
//	public String getDay() {
//		return day;
//	}

	//constructor
	public Appointment(long appointmentId, long carUnitId, String job, double bill, int day) {
		this.appointmentId = appointmentId;
		this.carUnitId = carUnitId;
		this.job = job;
		this.bill = bill;
		this.day = day;
		
	}
	//added after testers for p2 where received
	public Appointment(long appointmentId, long carUnitId, String job, double bill) {
		this.appointmentId = appointmentId;
		this.carUnitId = carUnitId;
		this.job = job;
		this.bill = bill;
	
		
	}
	//dummy constructor  needed to avoid JSONMappingException
	public Appointment() {
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (appointmentId !=other.appointmentId) {
			if (other.carUnitId != other.carUnitId)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (bill != other.bill)
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		String sday = null;
		if (this.day==0) sday ="Monday";
		else if(this.day==1) sday="Tuesday";
		else if(this.day==2) sday="Wednesday";
		else if (this.day==3) sday="Thursday";
		else if (this.day==4) sday ="Friday";
		return "Appointment  [appointmentID=" + appointmentId + ", carUnitId=" + carUnitId + ", job=" + job + ", bill="
		+ bill+ ", day="+ sday +"]";

	}
	public static class AppointmentBuilder {
		private long appointmentId; 
		private long carUnitId; 
		private String job; 
		private double bill; 
		private String day;
	}
	
}
