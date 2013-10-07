package de.itfo2.objects;

public class Wuerfel {
    private int wert;

    public int getWert() {
        wuerfeln();
        return wert;
    }

    public void wuerfeln() {
        wert = (int) (Math.random() * 7);
        if (wert == 0) {
            wuerfeln();
        }
    }
}
