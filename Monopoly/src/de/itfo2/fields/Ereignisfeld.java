package de.itfo2.fields;

import de.itfo2.objects.Verwalter;

import java.io.IOException;

public class Ereignisfeld implements Feld
{
	String bezeichnung;
    static int pointer = 0;

    public Ereignisfeld(String bezeichnung){
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
        try {
            Verwalter.getInstance().getSpielfeld().getEreigniskarten().get(pointer).effect();
            pointer = (pointer+1)% Verwalter.getInstance().getSpielfeld().getEreigniskarten().size();
        } catch (IOException e) {
        }
    }
}
