package model;

import java.io.Serializable;

public enum Gender implements Serializable {
	MALE("male"),
	FEMALE("female"),
	NONE("none");
	
	private String gender;
	
	private Gender (String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return gender;
	}
	
}
