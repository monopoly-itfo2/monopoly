package de.itfo2.objects;

import de.itfo2.fields.Feld;
import de.itfo2.fields.Freiparken;
import de.itfo2.objects.cards.Karte;

import java.util.ArrayList;

public class Spielfeld {

    public ArrayList<Karte> gemeinschaftskarten;
    public ArrayList<Karte> ereigniskarten;
    public ArrayList<Feld> felder;
    Spielfeld(ArrayList<Feld> felder, ArrayList<Karte> ereigniskarten, ArrayList<Karte> gemeinschaftskarten){
        this.felder = felder;
        this.ereigniskarten = ereigniskarten;
        this.gemeinschaftskarten = gemeinschaftskarten;
    }

    public Feld getFeld(int pos){
        return felder.get(pos);
    }

    public ArrayList<Karte> getGemeinschaftskarten() {
        return gemeinschaftskarten;
    }

    public ArrayList<Karte> getEreigniskarten() {
        return ereigniskarten;
    }

    public void addFreiParkenGeld(int betrag){
        ((Freiparken)(felder.get(20))).addMoney(betrag);
    }
}

