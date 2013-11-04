package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Gemeinschaftskarte10 extends Karte{

    final String text = "Rücke vor bis auf LOS";

    public Gemeinschaftskarte10() {

    }

    @Override
    public void effect() throws IOException {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum BestÃ¤tigen.
        Verwalter.getInstance().getCurSpieler().setPlatz(0);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
