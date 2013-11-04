package de.itfo2.ui;

import de.itfo2.objects.Spieler;

public interface MonopolyGUIInterface {
    void rueckeVor(int anzahl);
    void rueckeAuf(int platz);
    void geheInsGefaengnis(int spieler);
    void kaufeFeld();
    void baueHaus();
    void wuerfeln();
    void addSpieler(int pos, Spieler spieler);
}
