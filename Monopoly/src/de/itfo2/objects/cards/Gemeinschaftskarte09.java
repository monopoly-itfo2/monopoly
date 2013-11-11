package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte09 extends Karte{

    final String text = "Gehe zurueck nach der Badstrasse";

    public Gemeinschaftskarte09() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Bestätigen.
        Verwalter.getInstance().getCurSpieler().setPlatz(1);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
