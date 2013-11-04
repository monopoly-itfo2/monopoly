package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte07 extends Karte{

    final String text = "Du hast in einem Kreutzworträtselwettbewerb gewonnen. Ziehe 2000 DM ein";

    public Ereigniskarte07() {

    }

    @Override
    public void effect() throws IOException {
        Verwalter.getInstance().getCurSpieler().addGeld(2000);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
