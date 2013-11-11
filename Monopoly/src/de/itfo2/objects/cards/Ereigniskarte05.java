package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte05 extends Karte{

    final String text = "Ruecke vor bis zur Seestrasse. Wenn du ueber LOS kommst, ziehe 4000 DM ein";

    public Ereigniskarte05() {

    }

    @Override
    public void effect() {
       // TODO LOS ziehe 4000 DM ein
    	MonopolyGUI.getInstance().rueckeAuf(11);
    	Verwalter.getInstance().getCurSpieler().addPlatz(Verwalter.getInstance().calculateToGo(11));
    	
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
