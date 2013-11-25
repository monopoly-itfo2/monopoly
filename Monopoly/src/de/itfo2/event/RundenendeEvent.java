package de.itfo2.event;

public class RundenendeEvent implements MonopolyEvent {
	
	private static final long serialVersionUID = 8833689971320995317L;
	private String spielername = null;

	public String getSpielername() {
		return spielername;
	}

	public void setSpielername(String spielername) {
		this.spielername = spielername;
	}
}
