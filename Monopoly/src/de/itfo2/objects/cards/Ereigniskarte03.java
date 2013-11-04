package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte03 extends Karte{

    final String text = "Rücke vor bis auf LOS";

    public Ereigniskarte03() {

    }

    @Override
    public void effect() throws IOException {
       // TODO Gefängnisfreikarte
        Verwalter.getInstance().getCurSpieler().setPlatz(0); 
        MonopolyGUI.getInstance().rueckeAuf(0);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
