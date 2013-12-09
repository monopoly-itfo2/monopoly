package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte09 extends Karte{

    final String text = "Gehe zurueck zur Badstrasse";

    public Gemeinschaftskarte09() {

    }

    @Override
    public void effect() {
    	MonopolyGUI.getInstance().rueckeZurueck(1);
        Verwalter.getInstance().getCurSpieler().setPlatz(1);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

    }
}
