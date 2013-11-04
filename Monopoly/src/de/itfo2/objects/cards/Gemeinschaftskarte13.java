package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte13 extends Karte{

    final String text = "Zahle deine Versicherungssumme 1000 DM";

    public Gemeinschaftskarte13() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum BestÃ¤tigen.
        Verwalter.getInstance().getCurSpieler().addGeld(-1000);
//        TODO Zahle an die Bank, für jedes Haus 800 DM, für jedes Hotel 2300 DM
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
