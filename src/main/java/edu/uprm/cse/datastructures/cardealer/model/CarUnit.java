package edu.uprm.cse.datastructures.cardealer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

//CarUnit represents a physical car located in the lot of the car dealer
public class CarUnit {
	private long carUnitId; // internal id of the unit
	private long carId; // id of the car object that represents the general for the car. 
                                           // This Car from project 1.
	private String vin; // vehicle identification number
	private String color; // car color
	private String carPlate; // car plate (null until sold)
	private long personId; // id of the person who purchased the car. (null until 
                                                  //purchased)	

  public long getCarUnitId() {
	  return carUnitId;
  }
  public long getCarId() {
	  return carId;
  }
  @JsonProperty("vin")
  public String getVin() {
	  return vin;
  }
  public String getColor() {
	  return color;
  }
  public String getCarPlate() {
	  return carPlate;
  }
  public long getPersonId() {
	  return personId;
  }
 
  public CarUnit(long carUnitId, long carId, String vin, String color, String carPlate, long personId) {
	  this.carUnitId=carUnitId;
	  this.carId=carId;
	  this.vin=vin;
	  this.color=color;
	  this.carPlate=carPlate;
	  this.personId=personId;
	  
  }
//dummy constructor  needed to avoid JSONMappingException
  public CarUnit() {
	  
  }


  @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + (int) (carId ^ (carId >>> 32));
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
		result = prime * result + ((carPlate == null) ? 0 : carPlate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(personId);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarUnit other = (CarUnit) obj;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (carId != other.carId)
			return false;
		if (carPlate == null) {
			if (other.carPlate != null)
				return false;
		} else if (carUnitId!=other.carUnitId)
			return false;
	      else if (carId!=other.carId)
			return false;
		  else if (personId != other.personId)
			return false;
		
		return true;
	}
	
public String toString() {
	return "CarUnit [carUnitId=" + carUnitId + ", carId="+ carId + ", vin="+ vin + ", color=" + color + ", carPlate=" + carPlate + ", personId=" + personId ;
	
}
public static class CarUnitBuilder {
	private long carUnitId; 
	private long carId; 
	private String vin; 
	private String color;
	private String carPlate; 
	private long personId;
	
}

}
