package de.itfo2.fields;
import java.awt.Color;
import java.io.IOException;

import de.itfo2.objects.Spieler;
import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;


public class Bahnhof implements Grundstueck
{
	String bezeichnung = null;
	Spieler besitzer = null;
	private int preis = 0;
    private int miete = 0;
    private boolean belastet;
    Color farbe = Color.black;

    public Bahnhof(String bezeichnung, int preis, int miete)
    {
        this.bezeichnung = bezeichnung;
        this.preis = preis;
    }

    @Override
    public void setBelastet(boolean belastet){
        this.belastet = belastet;
    }

    @Override
    public boolean isBelastet(){
        return belastet;
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
	public void handleFieldEffect() throws IOException {
        Spieler curSpieler = null;
        MonopolyGUI gui = null;
        int inBesitz = Verwalter.getInstance().getSpielfeld().getBahnhofBesitz(curSpieler);
        gui = MonopolyGUI.getInstance();
        curSpieler = Verwalter.getInstance().getCurSpieler();


        if(besitzer != null){
            if(!isBelastet())
            {
                if(besitzer != curSpieler){
                    //1. Fall - Spieler kann bezahlen
                    if(curSpieler.getKonto() >= miete * inBesitz){
                        besitzer.addGeld(miete * inBesitz);
                        curSpieler.addGeld(-miete * inBesitz);
                        gui.addLogMessage(curSpieler.getName() + " hat " + (miete*inBesitz) + "€ bezahlt.");
                    }
                    else{
                        //2. Fall - Spieler kann nicht bezahlen
                        Verwalter.getInstance().setHypothekenauswahl(true, true);
                        gui.addLogMessage("Bitte waehle ein Grundstueck fuer eine Hypothek aus!");
                    }
                }
            }
        }
	}
}
