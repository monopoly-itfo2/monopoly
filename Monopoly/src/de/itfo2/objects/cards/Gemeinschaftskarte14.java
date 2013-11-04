package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte14 extends Karte{

    final String text = "Arztkosten 100 DM";

    public Gemeinschaftskarte14() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Bestätigen.
        //TODO LOS 4000DM
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
        Verwalter.getInstance().getCurSpieler().addGeld(-100);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
