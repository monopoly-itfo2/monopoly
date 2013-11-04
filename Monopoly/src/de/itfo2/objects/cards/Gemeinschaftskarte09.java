package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Gemeinschaftskarte09 extends Karte{

    final String text = "Gehe zurück nach der Badstrasse";

    public Gemeinschaftskarte09() {

    }

    @Override
    public void effect() throws IOException {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum BestÃ¤tigen.
        Verwalter.getInstance().getCurSpieler().setPlatz(1);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
