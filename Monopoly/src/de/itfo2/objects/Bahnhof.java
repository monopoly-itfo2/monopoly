package de.itfo2.objects;
import java.awt.Color;


public class Bahnhof implements Grundstueck
{
	String bezeichnung = null;
	Spieler besitzer = null;
	private int preis = 0;
    private int miete = 0;

    Color farbe = Color.black;

    public Bahnhof(String bezeichnung, int preis, int miete)
    {
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

    public int getMiete() {
        return miete;
    }

    public void setMiete(int miete) {
        this.miete = miete;
    }
	
	@Override
	public void handleFieldEffect() {
	
	}
}
