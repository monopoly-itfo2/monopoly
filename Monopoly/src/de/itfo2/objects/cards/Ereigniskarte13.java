package de.itfo2.objects.cards;


import java.io.IOException;

import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte13 extends Karte{

    final String text = "Du wirst zu Strassenausbesserungsarbeiten herangezogen. Zahle f�r deine H�user und Hotels DM 800 pro Haus und DM 2300 je Hotel";

    public Ereigniskarte13() {

    }

    @Override
    public void effect() throws IOException {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Bestätigen.
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
//        TODO Zahle an die Bank, f�r jedes Haus 800 DM, f�r jedes Hotel 2300 DM
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
