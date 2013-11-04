package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Gemeinschaftskarte06 extends Karte{

    final String text = "Aus Lagerverk‰ufen erh‰lst du 100 DM";

    public Gemeinschaftskarte06() {

    }

    @Override
    public void effect() throws IOException {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Best√§tigen.
        Verwalter.getInstance().getCurSpieler().addGeld(100); 
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
