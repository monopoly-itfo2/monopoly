package de.itfo2.objects;

import de.itfo2.fields.Feld;

import java.util.ArrayList;

public class Spielfeld {

    public Karte[] gemeinschaftskarten;
    public Karte[] ereigniskarten;
    public ArrayList<Feld> felder;
    Spielfeld(ArrayList<Feld> felder){
        this.felder = felder;
    }

    public Feld getFeld(int pos){
        return felder.get(pos);
    }
}

