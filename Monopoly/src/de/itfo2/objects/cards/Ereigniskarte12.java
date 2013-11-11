package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte12 extends Karte{

    final String text = "Lass alle deine Haeuser renovieren. Zahle an die Bank, fuer jedes Haus 500 DM, fuer jedes Hotel 2000 DM";

    public Ereigniskarte12() {

    }

    @Override
    public void effect() {
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
//        TODO Zahle an die Bank, fuer jedes Haus 500 DM, fuer jedes Hotel 2000 DM
    	
    	//Warum wird diese Variable nicht genutzt ? Fabian
    	int anzahlHaeuser = Verwalter.getInstance().getAlleHaeuser(Verwalter.getInstance().getCurSpieler());
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
