package model;

import java.io.Serializable;

public class Participation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Person participant;
	private LegalPerson participed;
	private long amount;
	
	public Person getParticipant() {
		return participant;
	}
	
	public Participation setParticipant(Person participant) {
		this.participant = participant;
		return this;
	}
	
	public LegalPerson getParticiped() {
		return participed;
	}
	
	public Participation setParticiped(LegalPerson participed) {
		this.participed = participed;
		return this;
	}
	
	public long getAmount() {
		return amount;
	}
	
	public Participation setAmount(long amount) {
		this.amount = amount;
		return this;
	}
}
