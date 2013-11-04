package de.itfo2.fields;

import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Steuerfeld implements Feld
{
	String bezeichnung;
    int zuZahlen = 0; //Einkommens- und Zusatzsteuer sind unterrschiedlich hoch, das komtm hier rein

    public Steuerfeld(String bezeichnung, int zuZahlen)
    {
        this.bezeichnung = bezeichnung;
        this.zuZahlen = zuZahlen;
    }

	@Override
	public String getBezeichnung() {
		return bezeichnung;
	}

	@Override
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
		
	}

    public int getZuZahlen() {
        return zuZahlen;
    }

    public void setZuZahlen(int zuZahlen) {
        this.zuZahlen = zuZahlen;
    }

	@Override
	public void handleFieldEffect() {
        Verwalter verwalter = null;
        try {
            verwalter = Verwalter.getInstance();
        } catch (IOException e) {
        }
        if(verwalter.getCurSpieler().getKonto() >= zuZahlen){
            verwalter.getCurSpieler().addGeld(-zuZahlen);
            verwalter.getSpielfeld().addFreiParkenGeld(zuZahlen);
            MonopolyGUI gui = null;
            try {
                gui = MonopolyGUI.getInstance();
            } catch (IOException e) {
            }
            gui.addLogMessage(verwalter.getCurSpieler().getName() + "zahlte " + zuZahlen + "€ Steuer");
        }else{
            //Hypothek?
        }
	}

}
