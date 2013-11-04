package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte05 extends Karte{

    final String text = "Rücke vor bis zur Seestrasse. Wenn du über LOS kommst, ziehe 4000 DM ein";

    public Ereigniskarte05() {

    }

    @Override
    public void effect() throws IOException {
       // TODO LOS ziehe 4000 DM ein
    	Verwalter.getInstance().getCurSpieler().addPlatz(Verwalter.getInstance().calculateToGo(11));
    	MonopolyGUI.getInstance().rueckeAuf(11);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
