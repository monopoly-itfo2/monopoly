package de.itfo2.objects;

public class Würfel {
    private int wert;

    public int getWert() {
        würfeln();
        return wert;
    }

    public void würfeln() {
        wert = (int) (Math.random() * 7);
        if (wert == 0) {
            würfeln();
        }
    }
}
