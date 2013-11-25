package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte16 extends Karte{

    final String text = "Ruecke vor bis zur Schlossallee";

    public Ereigniskarte16() {

    }

    @Override
    public void effect() {
        //TODO LOS 4000DM
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
        MonopolyGUI.getInstance().rueckeAuf(39);
    	Verwalter.getInstance().getCurSpieler().addPlatz(Verwalter.getInstance().calculateToGo(39));
        
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

    }
}
