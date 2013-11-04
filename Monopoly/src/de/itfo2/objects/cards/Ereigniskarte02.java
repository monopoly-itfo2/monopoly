package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

import java.io.IOException;

public class Ereigniskarte02 extends Karte{

    final String text = "Du kommst aus dem Gef‰ngnis frei. Diese Karte kann verkauft werden";

    public Ereigniskarte02() {

    }

    @Override
    public void effect() throws IOException {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Best√§tigen.
       // TODO Gef‰ngnisfreikarte
        Verwalter.getInstance().getCurSpieler().setGefaengnisFrei(1);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
