package de.itfo2.objects;

import java.io.IOException;

public class Los implements Feld
{
	String bezeichnung;

    public Los(String bezeichnung){
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
			Verwalter.getInstance().getCurSpieler().addMoney(4000);
		}catch(IOException ex){
			System.out.println("FieldEffect Los Probleme");
		}
		
	}
}
