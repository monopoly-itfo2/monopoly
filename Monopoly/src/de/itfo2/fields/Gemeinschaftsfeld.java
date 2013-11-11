package de.itfo2.fields;

import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Gemeinschaftsfeld implements Feld
{
	String bezeichnung;

    public Gemeinschaftsfeld(String bezeichnung)
    {
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
        MonopolyGUI.getInstance().setGemeinschaftskartenButtonEnabled(true);
	}
}
