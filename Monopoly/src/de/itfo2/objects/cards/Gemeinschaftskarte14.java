package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte14 extends Karte{

    final String text = "Arztkosten 100 Euro";

    public Gemeinschaftskarte14() {

    }

    @Override
    public void effect() {
        Verwalter.getInstance().getCurSpieler().addGeld(-100);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
