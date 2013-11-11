package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte04 extends Karte{

    final String text = "Gehe in das Gefaengnis, gehe direkt dorthin, gehe nicht ueber LOS. Ziehe nicht 4000 DM ein";

    public Ereigniskarte04() {

    }

    @Override
    public void effect() {
    	MonopolyGUI.getInstance().rueckeAuf(10);
        Verwalter.getInstance().getCurSpieler().setPlatz(10); 
        
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
