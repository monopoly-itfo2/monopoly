package de.itfo2.objects;

public class W�rfel {
    private int wert;

    public int getWert() {
        w�rfeln();
        return wert;
    }

    public void w�rfeln() {
        wert = (int) (Math.random() * 7);
        if (wert == 0) {
            w�rfeln();
        }
    }
}
