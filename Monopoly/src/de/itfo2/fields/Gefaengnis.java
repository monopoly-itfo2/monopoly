package de.itfo2.fields;

import de.itfo2.objects.Verwalter;

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
        //nichts
    }

}
