package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte14 extends Karte{

    final String text = "Ruecke vor bis zum Opernplatz, wenn du ueber LOS kommst, ziehe DM 4000 ein";

    public Ereigniskarte14() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Bestätigen.
        //TODO LOS 4000DM
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
        Verwalter.getInstance().getCurSpieler().setPlatz(24);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
