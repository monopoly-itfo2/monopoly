package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte03 extends Karte{

    final String text = "Du erh‰lst aus Vorzugsaktien 7% Dividende -  500 DM";

    public Gemeinschaftskarte03() {

    }

    @Override
    public void effect() {
//        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Best√§tigen.
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
        Verwalter.getInstance().getCurSpieler().addGeld(500);;
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
