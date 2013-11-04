package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte09 extends Karte{

    final String text = "Strafe f�r zu schnelles fahren, 300 DM";

    public Ereigniskarte09() {

    }

    @Override
    public void effect() throws IOException {
        Verwalter.getInstance().getCurSpieler().addGeld(-300);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
