package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte08 extends Karte{

    final String text = "Miete und Anleihezinsen werden fällig. Zahle der Bank 3000 DM";

    public Ereigniskarte08() {

    }

    @Override
    public void effect() throws IOException {
        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
