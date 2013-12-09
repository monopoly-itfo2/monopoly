package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte02 extends Karte{

    final String text = "Du kommst aus dem Gefaengnis frei. Diese Karte kann verkauft werden";

    public Ereigniskarte02() {

    }
    
    @Override
    public String getText () {
    	return text;
    }
    
    @Override
    public void effect() {
        Verwalter.getInstance().getCurSpieler().addGefaengnisFrei(1);
        Verwalter.getInstance().disableGefängnisFrei(0);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);
    }
}
