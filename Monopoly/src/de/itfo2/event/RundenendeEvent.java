package de.itfo2.event;

public class RundenendeEvent implements MonopolyEvent {
	
	private static final long serialVersionUID = 8833689971320995317L;
	private String spielername = null;

	public RundenendeEvent(String spielername) {
		this.spielername = spielername;
	}
	
	public String getSpielername() {
		return spielername;
	}

}
