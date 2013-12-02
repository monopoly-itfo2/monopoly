package de.itfo2.event;

import de.itfo2.objects.Spieler;

public class LoginEvent implements MonopolyEvent {

	private static final long serialVersionUID = 8401246261306272972L;
	private Spieler player = null;

	public LoginEvent(Spieler spieler) {
		player = spieler;
	}
	
	public Spieler getPlayer() {
		return player;
	}
}
