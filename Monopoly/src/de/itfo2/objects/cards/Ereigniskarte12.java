package de.itfo2.objects.cards;


import java.io.IOException;

import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte12 extends Karte{

    final String text = "Lass alle deine Häuser renovieren. Zahle an die Bank, für jedes Haus 500 DM, für jedes Hotel 2000 DM";

    public Ereigniskarte12() {

    }

    @Override
    public void effect() throws IOException {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum BestÃ¤tigen.
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
//        TODO Zahle an die Bank, für jedes Haus 500 DM, für jedes Hotel 2000 DM
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
