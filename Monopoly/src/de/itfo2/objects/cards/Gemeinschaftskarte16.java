package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Gemeinschaftskarte16 extends Karte{

    final String text = "Bankirrtum zu deinen Gunsten, ziehe 4000 DM ein";

    public Gemeinschaftskarte16() {

    }

    @Override
    public void effect() throws IOException {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Bestätigen.
       Verwalter.getInstance().getCurSpieler().addGeld(4000);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
