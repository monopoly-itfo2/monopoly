package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte06 extends Karte{

    final String text = "Mache einen Ausflug zu dem Westbahnhof, wenn du über LOS kommst, ziehe 4000 DM ein";

    public Ereigniskarte06() {

    }

    @Override
    public void effect() throws IOException {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum BestÃ¤tigen.
       // TODO LOS ziehe 4000 DM ein
        Verwalter.getInstance().getCurSpieler().setPlatz(15); 
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
