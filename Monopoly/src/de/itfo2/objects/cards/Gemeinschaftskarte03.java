package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte03 extends Karte{

    final String text = "Du erhaelst aus Vorzugsaktien 7% Dividende -  500 Euro";

    public Gemeinschaftskarte03() {

    }

    @Override
    public void effect() {
        Verwalter.getInstance().getCurSpieler().addGeld(500);;
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);
    }
}
