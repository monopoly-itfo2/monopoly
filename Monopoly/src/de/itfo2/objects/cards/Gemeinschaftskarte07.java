package de.itfo2.objects.cards;


import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte07 extends Karte{

    final String text = "Du kommst aus dem Gef‰ngnis frei. Diese Karte kann verkauft werden";

    public Gemeinschaftskarte07() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Best√§tigen.
       // TODO Gef‰ngnisfreikarte
//        Verwalter.getInstance().getCurSpieler().setPlatz(11); 
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
