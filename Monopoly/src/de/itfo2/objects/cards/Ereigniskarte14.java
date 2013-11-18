package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte14 extends Karte{

    final String text = "Ruecke vor bis zum Opernplatz, wenn du ueber LOS kommst, ziehe DM 4000 ein";

    public Ereigniskarte14() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().rueckeAuf(24);
    	Verwalter.getInstance().getCurSpieler().addPlatz(Verwalter.getInstance().calculateToGo(24));    	
        
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

    }
}
