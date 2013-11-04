package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte01 extends Karte{

    final String text = "Die Bank zahlt dir eine Dividende von 1000 DM";

    public Ereigniskarte01() {

    }

    @Override
    public void effect() throws IOException {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Bestätigen.
        Verwalter.getInstance().getCurSpieler().addGeld(1000);
//        MonopolyGUI.getInstance().);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
