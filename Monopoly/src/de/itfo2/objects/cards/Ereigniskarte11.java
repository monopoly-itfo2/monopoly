package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte11 extends Karte{

    final String text = "Zahle Schulgeld, 3000 DM";

    public Ereigniskarte11() {

    }

    @Override
    public void effect() throws IOException {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Bestätigen.
        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
