package de.itfo2.fields;

import de.itfo2.objects.Verwalter;

import java.io.IOException;

public class Freiparken implements Feld{

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
    }

	@Override
	public void handleFieldEffect() {
		try{
			Verwalter.getInstance().getCurSpieler().addGeld(geldImPott);
		}catch(IOException ex){
			System.out.println("FieldEffect Freiparken Probleme");
		}
	}
}
