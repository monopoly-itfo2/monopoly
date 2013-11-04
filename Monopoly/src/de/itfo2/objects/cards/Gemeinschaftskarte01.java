package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte01 extends Karte{

    final String text = "Die Jahresrente wird f‰llig. Zihe 2000 DM ein";

    public Gemeinschaftskarte01() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Best√§tigen.
        Verwalter.getInstance().getCurSpieler().addGeld(-2000);
//        MonopolyGUI.getInstance().);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
