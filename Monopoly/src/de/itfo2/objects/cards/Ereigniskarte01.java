package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte01 extends Karte{

    final String text = "Die Bank zahlt dir eine Dividende von 1000 Euro";

    public Ereigniskarte01() {

    }
    
    @Override
    public String getText () {
    	return text;
    }
    
    @Override
    public void effect() {
        Verwalter.getInstance().getCurSpieler().addGeld(1000);
//        MonopolyGUI.getInstance().);
        Verwalter.getInstance().testGefängnisFrei();
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);
    }
}
