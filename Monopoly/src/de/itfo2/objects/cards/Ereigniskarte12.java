package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte12 extends Karte{


	final String text = "Lass alle deine Haeuser renovieren. Zahle an die Bank, fuer jedes Haus 500 DM, fuer jedes Hotel 2000 DM";
    int anzahlHotel = 0;
    int anzahlHaeuser = 0;
    int renovierungsKosten = 0;
    
    public Ereigniskarte12() {

    }

    @Override
    public void effect() {
    	
    	int[] anzahlGebäude = Verwalter.getInstance().getAlleHaeuser(Verwalter.getInstance().getCurSpieler());

    	System.out.println("Häuser: "+anzahlGebäude[0] +" Hotels: "+anzahlGebäude[1]);
    	renovierungsKosten = ((500 * anzahlGebäude[0])+ (2000 * anzahlGebäude[1]));
    	Verwalter.getInstance().getCurSpieler().addGeld(-renovierungsKosten);
    	
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

    }
}
