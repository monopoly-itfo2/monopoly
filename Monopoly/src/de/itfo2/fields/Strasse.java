package de.itfo2.fields;
import de.itfo2.objects.Spieler;

import java.awt.Color;

public class Strasse implements Grundstueck {
	
	//Hyothek = preis / 2 //methode f�r amchen
	String bezeichnung = null;
	Spieler besitzer = null;
	int preis = 0;
	Color farbe = null;
	int miete[] = new int[6];
	boolean alleFarben = false;
	int hausKosten = 0;
	int position = 0;

	@Override
	public String getBezeichnung() {
		return bezeichnung;
	}

	@Override
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	@Override
	public Spieler getBesitzer() {
		return besitzer;
	}

	@Override
	public void setBesitzer(Spieler spieler) {
		this.besitzer = spieler;
	}

	@Override
	public int getPreis() {
		return preis;
	}

	@Override
	public void setPreis(int preis) {
		this.preis = preis;
	}
	
	public Color getFarbe() {
		return farbe;
	}
	
	public void setFarbe(Color farbe) {
		this.farbe = farbe;
	}

	public int[] getMiete() {
		return miete;
	}

	public void setMiete(int[] miete) {
		this.miete = miete;
	}

	public boolean isAlleFarben() {
		return alleFarben;
	}

	public void setAlleFarben(boolean alleFarben) {
		this.alleFarben = alleFarben;
	}

	public int getHausKosten() {
		return hausKosten;
	}

	public void setHausKosten(int hausKosten) {
		this.hausKosten = hausKosten;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	@Override
	public int getHypothek () {
		return preis/2;
	}
	@Override
	public void handleFieldEffect() {
	
	}
	
}
