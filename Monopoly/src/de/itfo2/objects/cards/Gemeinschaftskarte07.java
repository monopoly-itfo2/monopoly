package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte07 extends Karte{

    final String text = "Du kommst aus dem Gefaengnis frei. Diese Karte kann verkauft werden";
    final String typ = "G";
    public Gemeinschaftskarte07() {

    }

    @Override
    public String getTyp() {
    	return typ;
    }
    
    @Override
    public void effect() {
//        Verwalter.getInstance().getCurSpieler().addGefaengnisFrei(1);
    	Verwalter.getInstance().getCurSpieler().addGefaengnisfreiKarten(this);
        Verwalter.getInstance().disableGefängnisFrei(2);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

    }
}
