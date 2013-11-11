package de.itfo2.fields;

import java.io.IOException;

public interface Feld
{
	String getBezeichnung();
	void setBezeichnung(String bezeichnung);
	void handleFieldEffect() throws IOException;

}
