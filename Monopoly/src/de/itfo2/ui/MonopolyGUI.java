package de.itfo2.ui;

import de.itfo2.objects.InitSpielfeld;
import de.itfo2.objects.Spieler;
import de.itfo2.objects.Spielfeld;
import de.itfo2.objects.Verwalter;

import java.io.IOException;

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
    public void rueckeVor(int spieler, int anzahl) throws IOException {
        Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
        spielfeld.setSpielerVisible(curSpieler.getPlatz(), Verwalter.getInstance().getSpielerAmZug(), false);
        spielfeld.setSpielerVisible(curSpieler.getPlatz()+anzahl, Verwalter.getInstance().getSpielerAmZug(), true);
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
