package edu.uprm.cse.datastructures.cardealer.model;
//customer of the dealer
public class Person {
	private long personId; // internal id of the person
	private String firstName; // first name
	private String lastName; // lastname
	private Integer age; // age
	private char gender; // gender
    private String phone; // phone number

    public long getPersonId() {
		return personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Integer getAge() {
		return age;
	}
	public char getGender() {
		return gender;
	}
	public String getPhone() {
		return phone;
	}


	//dummy constructor  needed to avoid JSONMappingException
	public Person() {
		
	}
	public Person(long personId, String firstName, String lastName, Integer age, char gender, String phone) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age=age;
		this.gender = gender;
		this.phone = phone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (personId ^ (personId >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		long temp;
		temp = Double.doubleToLongBits(30.0);
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
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (personId != other.personId)
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;

		if(gender!=other.gender)
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		
		return "Person [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + ", age="
		+ age + ", gender=" + gender+ "phone="+phone+ "]";

	}
	
	public static class PersonBuilder {
			private long personId; // internal id of the person
	private String firstName; // first name
	private String lastName; // lastname
	private Integer age; // age
	private char gender; // gender
    private String phone; // phone number
	}
}
