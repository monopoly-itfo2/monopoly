package de.itfo2.fields;

import java.io.IOException;

import de.itfo2.ui.MonopolyGUI;

public class Ereignisfeld implements Feld
{
	String bezeichnung;
    private static int pointer = 0;

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
	public void handleFieldEffect() throws IOException {
        MonopolyGUI.getInstance().setEreigniskartenButtonEnabled(true);
    }
}
