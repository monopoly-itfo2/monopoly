package de.itfo2.ui;

import java.io.IOException;

import de.itfo2.objects.Spieler;

public interface MonopolyGUIInterface {
    void rueckeVor(int anzahl) throws IOException;
    void rueckeAuf(int platz) throws IOException;
    void geheInsGefaengnis(Spieler spieler);
    void kaufeFeld();
    void baueHaus();
    void wuerfeln();
    void addSpieler(int pos, Spieler spieler) throws IOException;
}
