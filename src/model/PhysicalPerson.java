package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import utils.Utils;

public class PhysicalPerson extends Person { 

	private static final long serialVersionUID = 1L;
	
	private String middleName;
	private ArrayList<String> surnames;
	private Date born;
	private Date died;
	private Gender gender;
	private ArrayList<Person> employers;
	
	public PhysicalPerson() {
		super();
		surnames = new ArrayList<>();
		employers = new ArrayList<>();
		participations = new ArrayList<>();
	}
	
	public PhysicalPerson setName(String name) {
		return (PhysicalPerson) super.setName(name);
	}
	
	public PhysicalPerson setArtisticName(String artisticName) {
		return (PhysicalPerson) super.setArtisticName(artisticName);
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public PhysicalPerson setMiddleName(String middleName) {
		this.middleName = middleName;
		return this;
	}
	
	public String[] getSurnames() {
		return surnames.toArray(new String[0]);
	}

	public PhysicalPerson setSurname(String surname){
		surnames.clear();
		surnames.add(surname);
		return this;
	}

	public PhysicalPerson addSurname(String surname) {
		surnames.add(surname);
		return this;
	}
	
	
	public Date getBorn() {
		return (Date) born.clone();
	}
	
	public PhysicalPerson setBorn(Date born) {
		this.born = born;
		return this;
	}
	
	
	public Date getDies() {
		return (Date) died.clone();
	}
	
	public PhysicalPerson setDied(Date died) {
		this.died = died;
		return this;
	}
	
	
	public Gender getGender() {
		return gender;
	}
	
	public PhysicalPerson setGender(Gender gender) {
		this.gender = gender;
		return this;
	}
	
	
	
	public Person[] getEmployers() {
		return employers.toArray(new Person[0]);
	}

	public PhysicalPerson addEmployer(Person employer) {
		employers.add(employer);
		return this;
	}


	@Override
	public String toString() {
		return String.format("%s %s %s", name, middleName, Utils.join(surnames, " "));
	}


	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj == this) return true;
		if(obj instanceof PhysicalPerson) {
			PhysicalPerson other = (PhysicalPerson) obj;
			return super.equals(other) &&
					Utils.safeEqual(middleName, other.middleName) &&
					Arrays.equals(surnames.toArray(), other.surnames.toArray()) &&
					gender == other.gender &&
					Utils.safeEqual(born, other.born) &&
					Utils.safeEqual(died, other.died);
		}
		return false;
	}
	
	
	
}
