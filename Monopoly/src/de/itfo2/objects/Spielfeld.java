package de.itfo2.objects;

import de.itfo2.fields.Bahnhof;
import de.itfo2.fields.Feld;
import de.itfo2.fields.Freiparken;
import de.itfo2.objects.cards.Karte;

import java.util.ArrayList;

public class Spielfeld {

    public ArrayList<Karte> gemeinschaftskarten;
    private int gemeinschaftskartenPointer;
    public ArrayList<Karte> ereigniskarten;
    private int ereigniskartenPointer;
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
    
    public void removeGemeinschaftsskarte(int pointer) {
    	gemeinschaftskarten.remove(pointer);
    }

    public ArrayList<Karte> getEreigniskarten() {
        return ereigniskarten;
    }
    
    public void removeEreigniskarte(int pointer) {
    	ereigniskarten.remove(pointer);
    }

    public void printAllEreigniskarten() {
    	for(Karte karte: ereigniskarten) {
    		
    		System.out.println("Print Ereigniskarten "+karte.getText());
    	}
    }

    public void addFreiParkenGeld(int betrag){
        ((Freiparken)(felder.get(20))).addMoney(betrag);
    }

    public int getBahnhofBesitz(Spieler spieler){
        int inBesitz = 0;
        if(((Bahnhof)felder.get(5)).getBesitzer() != null)
            if(((Bahnhof)felder.get(5)).getBesitzer().equals(spieler))
                inBesitz++;
        if(((Bahnhof)felder.get(15)).getBesitzer() != null)
            if(((Bahnhof)felder.get(15)).getBesitzer().equals(spieler))
                inBesitz++;
        if(((Bahnhof)felder.get(25)).getBesitzer() != null)
            if(((Bahnhof)felder.get(25)).getBesitzer().equals(spieler))
                inBesitz++;
        if(((Bahnhof)felder.get(35)).getBesitzer() != null)
            if(((Bahnhof)felder.get(35)).getBesitzer().equals(spieler))
                inBesitz++;
        return inBesitz;
    }

    public int getGemeinschaftskartenPointer() {
        return gemeinschaftskartenPointer;
    }

    public void setGemeinschaftskartenPointer(int gemeinschaftskartenPointer) {
        this.gemeinschaftskartenPointer = gemeinschaftskartenPointer;
    }

    public int getEreigniskartenPointer() {
        return ereigniskartenPointer;
    }

    public void setEreigniskartenPointer(int ereigniskartenPointer) {
        this.ereigniskartenPointer = ereigniskartenPointer;
    }

}

