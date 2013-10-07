package de.itfo2.ui;

import java.io.IOException;

import de.itfo2.objects.Spieler;
import de.itfo2.objects.Spielfeld;

public class MonopolyGUI implements MonopolyGUIInterface {

    private static MonopolyGUI instance = null;
    GUISpielfeld spielfeld;

    public MonopolyGUI() throws IOException {

    }

    public void setSpielfeld(Spielfeld spielfeld) throws IOException {
        this.spielfeld = new GUISpielfeld(spielfeld);
    }

    public static MonopolyGUI getInstance() throws IOException {
        if(instance == null){
            instance = new MonopolyGUI();
        }
        return instance;
    }

    @Override
    public void rueckeVor(int spieler, int anzahl) {

    }

    @Override
    public void geheInsGefaengnis(int spieler) {

    }

    @Override
    public void baueHaus(GUIFeld feld) {

    }

    @Override
    public void kaufeFeld(int spieler, GUIFeld feld) {

    }

    @Override
    public void wuerfeln() {

    }

    @Override
    public void addSpieler(int pos, Spieler spieler) throws IOException {
        spielfeld.addSpieler(pos, spieler);
    }

    public void updateFeld(){
        spielfeld.updateFeld();
    }
}
