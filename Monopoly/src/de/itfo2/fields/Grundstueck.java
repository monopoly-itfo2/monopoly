package de.itfo2.fields;

import de.itfo2.objects.Spieler;

public interface Grundstueck extends Feld
{
	Spieler getBesitzer();
	void setBesitzer(Spieler spieler);
	int getPreis();
	void setPreis(int preis);
	int getHypothek();
}
