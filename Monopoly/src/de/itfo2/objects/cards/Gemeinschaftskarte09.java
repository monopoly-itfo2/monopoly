package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte09 extends Karte{

    final String text = "Gehe zurueck nach der Badstrasse";

    public Gemeinschaftskarte09() {

    }

    @Override
    public void effect() {
    	int aktuellePosition = Verwalter.getInstance().getCurSpieler().getPlatz();
    	int divPosition = aktuellePosition -1;
    	Verwalter.getInstance().getCurSpieler().addPlatz(-divPosition);
        Verwalter.getInstance().getCurSpieler().setPlatz(1);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
