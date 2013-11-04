package de.itfo2.objects.cards;


import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte15 extends Karte{

    final String text = "Es ist dein Geburtstag, ziehe von jedem Spieler 200 DM ein";

    public Gemeinschaftskarte15() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Bestätigen.
        //TODO LOS 4000DM
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
//		TODO       ziehe von jedem Spieler 200 DM ein  
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
