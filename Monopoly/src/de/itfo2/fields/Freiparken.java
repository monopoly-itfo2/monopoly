package de.itfo2.fields;

import java.util.Observable;

import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Freiparken extends Observable implements Feld{

	String bezeichnung;
    private int geldImPott;

    public Freiparken(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }

	@Override
	public String getBezeichnung() {
		return bezeichnung;
	}

	@Override
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

    public int getSumme() {
        return geldImPott;
    }

    public void setSumme(int summe) {
        this.geldImPott = summe;
    }

    public void addMoney(int money){
        this.geldImPott += money;
        notifyObservers();
    }

	@Override
	public void handleFieldEffect() {
		MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() + " erhält " + geldImPott + "€");
		Verwalter.getInstance().getCurSpieler().addGeld(geldImPott);
		geldImPott = 0;
		notifyObservers();
	}
}
