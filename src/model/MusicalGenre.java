package model;

import java.io.Serializable;

import utils.Utils;

public final class MusicalGenre implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private String name;

	public String getName() {
		return name;
	}
	
	public MusicalGenre setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj == this) return true;
		if(obj instanceof MusicalGenre) {
			MusicalGenre other = (MusicalGenre) obj;
			return Utils.safeEqual(name, other.name);
		}
		return false;
	}

	@Override
	public int hashCode() {
		if(name == null) return super.hashCode();
		return name.hashCode();
	}
}
