package de.itfo2.ui;

import java.io.IOException;

import de.itfo2.objects.Spieler;

public interface MonopolyGUIInterface {
    void rueckeVor(int spieler, int anzahl);
    void geheInsGefaengnis(int spieler);
    void baueHaus(GUIFeld feld);
    void kaufeFeld(int spieler, GUIFeld feld);
    void wuerfeln();
    void addSpieler(int pos, Spieler spieler) throws IOException;
}
