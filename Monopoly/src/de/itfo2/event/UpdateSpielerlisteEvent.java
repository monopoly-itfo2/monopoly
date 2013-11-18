package de.itfo2.event;

import java.util.List;

import de.itfo2.objects.Spieler;

public class UpdateSpielerlisteEvent implements MonopolyEvent {
	private static final long serialVersionUID = -3308398555655286593L;
	private List<Spieler> spielerListe;

	public UpdateSpielerlisteEvent(List<Spieler> spielerListe) {
		this.spielerListe = spielerListe;
	}

	public List<Spieler> getSpielerListe() {
		return spielerListe;
	}
}
