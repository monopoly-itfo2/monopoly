package de.itfo2.fields;
import java.awt.Color;

import de.itfo2.objects.Spieler;
import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Strasse implements Grundstueck {
	
	//Hyothek = preis / 2 //methode fï¿½r amchen
	private String bezeichnung = null;
	private Spieler besitzer = null;
	private int preis = 0;
	private Color farbe = null;
	private int miete[] = new int[6];
    private int mietePointer = 0;
	private boolean alleFarben = false;
	private int hausKosten = 0;
    private boolean belastet;

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

    @Override
	public void handleFieldEffect() {

        Spieler curSpieler = null;
        MonopolyGUI gui = null;
        gui = MonopolyGUI.getInstance();
        curSpieler = Verwalter.getInstance().getCurSpieler();


        if(besitzer != null){
            if(besitzer != curSpieler){
                if(!isBelastet())
                {
                    //1. Fall - Spieler kann bezahlen
                    if(mietePointer==0 && alleFarben){
                        if(curSpieler.getKonto() >= miete[mietePointer]*2){
                            besitzer.addGeld(miete[mietePointer]*2);
                            curSpieler.addGeld(-miete[mietePointer]*2);
                            gui.addLogMessage(curSpieler.getName() + " hat blubb " + miete[mietePointer]*2 + "€ bezahlt.");
                        }
                        else{
                            //2. Fall - Spieler kann nicht bezahlen
                            Verwalter.getInstance().setHypothekenauswahl(true, true);
                            gui.addLogMessage("Bitte waehle ein Grundstueck fuer eine Hypothek aus!");
                        }
                    } else{
                        if(curSpieler.getKonto() >= miete[mietePointer]){
                            besitzer.addGeld(miete[mietePointer]);
                            curSpieler.addGeld(miete[mietePointer]);
                            gui.addLogMessage(curSpieler.getName() + " hat blubb " + miete[mietePointer]*2 + "€ bezahlt.");
                        }
                        else{
                            //2. Fall - Spieler kann nicht bezahlen
                            Verwalter.getInstance().setHypothekenauswahl(true, true);
                            gui.addLogMessage("Bitte waehle ein Grundstueck fuer eine Hypothek aus!");
                        }
                    }

                    //2. Fall - Spieler kann bezahlen
                }
            }
        }
    }
	
}
