package model;

import java.io.Serializable;
import java.util.ArrayList;

import utils.Utils;

public abstract class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected String artisticName;
	protected ArrayList<Participation> participations;

	protected Person() {
		participations = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public Person setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getArtisticName() {
		if(artisticName == null) {
			return toString();
		} else {
			return artisticName;
		}
	}
	
	public Person setArtisticName(String artisticName) {
		this.artisticName = artisticName;
		return this;
	}
	
	public Participation[] getParticipations() {
		return participations.toArray(new Participation[0]);
	}
	
	public Person addParticipation(LegalPerson participed, long amount) {
		Participation participation = findParticipation(participed);
		if(participation == null) {
			participation = new Participation()
				.setParticipant(this)
				.setParticiped(participed)
				.setAmount(amount);
			participations.add(participation);
		} else {
			participation.setAmount(participation.getAmount() + amount);
		}
		
		return this;
	}
	
	private Participation findParticipation(LegalPerson participed) {
		for(Participation participation : participations) {
			if(participation.getParticiped().equals(participed)) {
				return participation;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj == this) return true;
		if(obj instanceof Person) {
			Person other = (Person) obj;
			return Utils.safeEqual(name, other.name) &&
					Utils.safeEqual(artisticName, other.artisticName);
		}
		return false;
				
	}

	@Override
	public int hashCode() {
		if(name == null) return super.hashCode();
		return name.hashCode();
	}
	
}
