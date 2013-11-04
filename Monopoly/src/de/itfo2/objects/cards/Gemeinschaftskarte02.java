package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte02 extends Karte{

    final String text = "Du erbst 2000 DM";

    public Gemeinschaftskarte02() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Bestätigen.
        Verwalter.getInstance().getCurSpieler().addGeld(2000);; 
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
