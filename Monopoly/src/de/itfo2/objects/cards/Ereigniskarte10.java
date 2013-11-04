package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte10 extends Karte{

    final String text = "Betrunken im Dienst, zahle 400 DM Strafe";

    public Ereigniskarte10() {

    }

    @Override
    public void effect() throws IOException {
        Verwalter.getInstance().getCurSpieler().addGeld(-400);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
