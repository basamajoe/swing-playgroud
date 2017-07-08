package model;

import java.util.Date;

import utils.Utils;

public class LegalPerson extends Person {

	private static final long serialVersionUID = 1L;
	
	private Date founded;
	
	public LegalPerson() {
		super();
	}
	
	public LegalPerson setName(String name) {
		return (LegalPerson) super.setName(name);
	}
	
	public LegalPerson setArtisticName(String artisticName) {
		return (LegalPerson) super.setArtisticName(artisticName);
	}
	
	public Date getFounded() {
		return (Date) founded.clone();
	}
	
	public LegalPerson setFounded(Date founded) {
		this.founded = founded;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj == this) return true;
		if(obj instanceof LegalPerson) {
			LegalPerson other = (LegalPerson) obj;
			return super.equals(other) &&
					Utils.safeEqual(founded, other.founded);
		}
		return false;
	}
	
	
}
