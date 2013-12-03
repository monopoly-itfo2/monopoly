package de.itfo2.fields;

import de.itfo2.objects.Verwalter;

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
        Verwalter.getInstance().getCurSpieler().addGeld(4000);

    }
}
