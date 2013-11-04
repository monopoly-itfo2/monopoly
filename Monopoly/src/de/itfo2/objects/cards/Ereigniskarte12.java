package de.itfo2.objects.cards;


import java.io.IOException;

import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte12 extends Karte{

    final String text = "Lass alle deine H�user renovieren. Zahle an die Bank, f�r jedes Haus 500 DM, f�r jedes Hotel 2000 DM";

    public Ereigniskarte12() {

    }

    @Override
    public void effect() throws IOException {
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
//        TODO Zahle an die Bank, f�r jedes Haus 500 DM, f�r jedes Hotel 2000 DM
    	int anzahlHaeuser = Verwalter.getInstance().getAlleHaeuser(Verwalter.getInstance().getCurSpieler());
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
