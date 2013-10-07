package de.itfo2.event;



/**
 * 
 * @author Marco Ernst
 *
 */
public class WuerfelEvent implements MonopolyEvent {

	private int value = 0;
	private String player = null;

	public WuerfelEvent(String spieler, int wert) {
		player = spieler;
		value = wert;
	}
	
	public String getPlayer() {
		return player;
	}

	public int getValue() {
		return value;
	}
	
}
