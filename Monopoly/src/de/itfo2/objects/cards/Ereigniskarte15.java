package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte15 extends Karte{

    final String text = "Gehe 3 Felder zur�ck";

    public Ereigniskarte15() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Bestätigen.
        //TODO LOS 4000DM
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
        
        Verwalter.getInstance().getCurSpieler().setPlatz(Verwalter.getInstance().getCurSpieler().getPlatz()-3);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
