package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte05 extends Karte{

    final String text = "Du hast den 2. Platz in einem Schoenheitswettbewerb gewonnen. Ziehe 200 DM ein";

    public Gemeinschaftskarte05() {

    }

    @Override
    public void effect() {
        Verwalter.getInstance().getCurSpieler().addGeld(200); 
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
