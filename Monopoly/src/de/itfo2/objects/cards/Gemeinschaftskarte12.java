package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte12 extends Karte{

    final String text = "Zahle an das Krankenhaus 2000 DM";

    public Gemeinschaftskarte12() {

    }

    @Override
    public void effect() {
        Verwalter.getInstance().getCurSpieler().addGeld(-2000);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
