package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte06 extends Karte{

    final String text = "Mache einen Ausflug zu dem Westbahnhof, wenn du ueber LOS kommst, ziehe 4000 DM ein";

    public Ereigniskarte06() {

    }

    @Override
    public void effect() {
       // TODO LOS ziehe 4000 DM ein
    	MonopolyGUI.getInstance().rueckeAuf(15);
    	Verwalter.getInstance().getCurSpieler().addPlatz(Verwalter.getInstance().calculateToGo(15));
    	
    	System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

    }
}
