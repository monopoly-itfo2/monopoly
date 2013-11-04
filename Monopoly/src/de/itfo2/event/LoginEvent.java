package de.itfo2.event;

import de.itfo2.objects.Spieler;

@SuppressWarnings("serial")
public class LoginEvent implements MonopolyEvent {
	private Spieler player = null;

	public LoginEvent(Spieler spieler) {
		player = spieler;
	}
	
	public Spieler  getPlayer() {
		return player;
	}
}
