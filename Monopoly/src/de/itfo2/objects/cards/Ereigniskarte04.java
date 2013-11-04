package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte04 extends Karte{

    final String text = "Gehe in das Gefängnis, gehe direkt dorthin, gehe nicht über LOS. Ziehe nicht 4000 DM ein";

    public Ereigniskarte04() {

    }

    @Override
    public void effect() throws IOException {
        Verwalter.getInstance().getCurSpieler().setPlatz(10); 
        MonopolyGUI.getInstance().rueckeAuf(10);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
