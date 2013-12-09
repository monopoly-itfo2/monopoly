package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte15 extends Karte{

    final String text = "Gehe 3 Felder zurueck";

    public Ereigniskarte15() {

    }

    @Override
    public void effect() {
    	MonopolyGUI.getInstance().rueckeZurueck(Verwalter.getInstance().getCurSpieler().getPlatz()-3);
        Verwalter.getInstance().getCurSpieler().setPlatz(Verwalter.getInstance().getCurSpieler().getPlatz()-3);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

    }
}
