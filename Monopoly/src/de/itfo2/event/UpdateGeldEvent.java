package de.itfo2.event;

public class UpdateGeldEvent implements MonopolyEvent {

	private static final long	serialVersionUID	= -324278601359935103L;
	
	private String spielername;
	private int geldVeraenderung;
	
	public UpdateGeldEvent(String spielername, int geldVeraenderung) {
		this.spielername = spielername;
		this.geldVeraenderung = geldVeraenderung;
	}

	public String getSpielername() {
		return spielername;
	}

	public void setSpielername(String spielername) {
		this.spielername = spielername;
	}

	public int getGeldVeraenderung() {
		return geldVeraenderung;
	}

	public void setGeldVeraenderung(int geldVeraenderung) {
		this.geldVeraenderung = geldVeraenderung;
	}

}
