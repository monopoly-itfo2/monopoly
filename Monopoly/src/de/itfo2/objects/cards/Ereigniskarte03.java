package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte03 extends Karte{

    final String text = "Ruecke vor bis auf LOS";

    public Ereigniskarte03() {

    }

    @Override
    public void effect() {
       // TODO Gefaengnisfreikarte
        Verwalter.getInstance().getCurSpieler().setPlatz(0); 
        MonopolyGUI.getInstance().rueckeAuf(0);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
