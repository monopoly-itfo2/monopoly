package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte04 extends Karte{

    final String text = "Einkommenssteuer-Rueckzahlung. Ziehe 400 DM ein";

    public Gemeinschaftskarte04() {

    }

    @Override
    public void effect() {
        Verwalter.getInstance().getCurSpieler().addGeld(400); 
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
