package de.itfo2.objects.cards;

import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte13 extends Karte{

    final String text = "Du wirst zu Strassenausbesserungsarbeiten herangezogen. Zahle fuer deine Haeuser und Hotels DM 800 pro Haus und DM 2300 je Hotel";
    int anzahlHotel = 0;
    int anzahlHaeuser = 0;
    int renovierungsKosten = 0;


    public Ereigniskarte13() {

    }

    @Override
    public void effect() {
    	int[] anzahlGebäude = Verwalter.getInstance().getAlleHaeuser(Verwalter.getInstance().getCurSpieler());

    	System.out.println("Häuser: "+anzahlGebäude[0] +" Hotels: "+anzahlGebäude[1]);
    	renovierungsKosten = ((800 * anzahlGebäude[0])+ (2300 * anzahlGebäude[1]));
    	Verwalter.getInstance().getCurSpieler().addGeld(-renovierungsKosten);

        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

        
        

    }
}
