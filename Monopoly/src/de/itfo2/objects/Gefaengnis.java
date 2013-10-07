package de.itfo2.objects;

import java.io.IOException;

public class Gefaengnis implements Feld
{
	String bezeichnung;

    public Gefaengnis(String bezeichnung){
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

	@Override
	public void handleFieldEffect() {
		try{
			Verwalter.getInstance().getCurSpieler().setImGef√§ngnis(true);
			Verwalter.getInstance().getCurSpieler().setPlatz(10);
		}catch(IOException ex){
			System.out.println("FieldEffect Gef‰ngnis Probleme");
		}
		
	}

}
