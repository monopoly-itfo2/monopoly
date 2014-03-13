package de.itfo2.fields;
import java.awt.Color;

import de.itfo2.objects.Spieler;
import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;


public class Werk implements Grundstueck
{
	String bezeichnung = null;
	Spieler besitzer = null;
	private int preis = 0;
    private Color farbe = null;
    boolean belastet;

    public Werk(String bezeichnung, int preis){
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
	
	@Override
	public void handleFieldEffect() {
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
                    if(curSpieler.getKonto() >= 200 * Verwalter.getInstance().getLastWuerfelZahl()){
                        besitzer.addGeld(200 * Verwalter.getInstance().getLastWuerfelZahl());
                        curSpieler.addGeld(-200 * Verwalter.getInstance().getLastWuerfelZahl());
                        gui.addLogMessage(curSpieler.getName() + " hat blubb " + (Verwalter.getInstance().getLastWuerfelZahl()) + "€ bezahlt.");
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
