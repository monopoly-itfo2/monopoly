package de.itfo2.event;

import de.itfo2.objects.Spieler;

public class SpieleranmeldungEvent implements MonopolyEvent {
	private Spieler player = null;

	public SpieleranmeldungEvent(Spieler spieler) {
		player = spieler;
	}
	
	public Spieler  getPlayer() {
		return player;
	}
}
