package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte13 extends Karte{

    final String text = "Zahle deine Versicherungssumme 1000 Euro";

    public Gemeinschaftskarte13() {

    }

    @Override
    public void effect() {
        Verwalter.getInstance().getCurSpieler().addGeld(-1000);
//        TODO Zahle an die Bank, fuer jedes Haus 800 DM, fuer jedes Hotel 2300 DM
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

    }
}
