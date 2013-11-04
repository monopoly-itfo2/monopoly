package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte16 extends Karte{

    final String text = "Rücke vor bis zur Schlossallee";

    public Ereigniskarte16() {

    }

    @Override
    public void effect() throws IOException {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum BestÃ¤tigen.
        //TODO LOS 4000DM
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
        
        Verwalter.getInstance().getCurSpieler().setPlatz(39);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
