package de.itfo2.fields;
import java.awt.Color;

import de.itfo2.objects.Spieler;
import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Strasse implements Grundstueck {
	
	//Hyothek = preis / 2 //methode f�r amchen
	private String bezeichnung = null;
	private Spieler besitzer = null;
	private int preis = 0;
	private Color farbe = null;
	private int miete[] = new int[6];
    private int mietePointer = 0;
	private boolean alleFarben = false;
	private int hausKosten = 0;

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

    public void addMietePointer(){
        mietePointer++;
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
	
	@Override
	public int getHypothek () {
		return preis/2;
	}
	
	public int getMietePointer() {
        return mietePointer;
    }
	
    public int getHausAnzahl() {
        return mietePointer;
    }

    @Override
	public void handleFieldEffect() {

        Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
        MonopolyGUI gui = MonopolyGUI.getInstance();

        if(besitzer != null){
            if(besitzer != curSpieler){
                //1. Fall - Spieler kann bezahlen
                if(curSpieler.getKonto() >= miete[0]){
                    besitzer.addGeld(miete[0]);
                    curSpieler.addGeld(miete[0]);
                    gui.addLogMessage(curSpieler.getName() + " hat blubb " + preis + "€ bezahlt.");
                }
                else{
                    //2. Fall - Spieler kann nicht bezahlen
                }
            }
        }
    }
	
}
