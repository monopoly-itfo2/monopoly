package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte10 extends Karte{

    final String text = "Ruecke vor bis auf LOS";

    public Gemeinschaftskarte10() {

    }

    @Override
    public void effect() {
    	MonopolyGUI.getInstance().rueckeAuf(0);
        Verwalter.getInstance().getCurSpieler().setPlatz(0); 
        
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
