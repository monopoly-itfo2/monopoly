package de.itfo2.fields;
import de.itfo2.objects.Spieler;

import java.awt.Color;


public class Werk implements Grundstueck
{
	String bezeichnung = null;
	Spieler besitzer = null;
	private int preis = 0;
    private Color farbe = null;

    public Werk(String bezeichnung, int preis){
        this.bezeichnung = bezeichnung;
        this.preis = preis;
    }

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
	
	@Override
	public int getHypothek() {
		return preis/2;
	}
	
	@Override
	public void handleFieldEffect() {
	
	}
}
