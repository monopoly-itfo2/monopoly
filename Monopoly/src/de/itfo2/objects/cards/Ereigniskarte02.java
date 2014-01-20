package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte02 extends Karte{
    final String text = "Du kommst aus dem Gefaengnis frei. Diese Karte kann verkauft werden";
    final String typ = "E";
    
    public Ereigniskarte02() {

    }
    
    @Override
    public String getText () {
    	return text;
    }
    @Override
    
    public String getTyp() {
    	return typ;
    }
    @Override
    public void effect() {
//        Verwalter.getInstance().getCurSpieler().addGefaengnisFrei(1);
        Verwalter.getInstance().getCurSpieler().addGefaengnisfreiKarten(this);
        Verwalter.getInstance().disableGef�ngnisFrei(0);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);
    }
}
