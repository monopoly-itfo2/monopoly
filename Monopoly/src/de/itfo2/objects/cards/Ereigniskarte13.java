package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Ereigniskarte13 extends Karte{

    int anzahlHotel = 0;
    int anzahlHaeuser = 0;
    int renovierungsKosten = 0;
    final String text = "Du wirst zu Strassenausbesserungsarbeiten herangezogen. Zahle fuer deine Haeuser und Hotels DM 800 pro Haus und DM 2300 je Hotel";


    public Ereigniskarte13() {

    }

    @Override
    public void effect() {
    	int anzahlHaeuser = Verwalter.getInstance().getAlleHaeuser(Verwalter.getInstance().getCurSpieler());
    	if(anzahlHaeuser > 5){
    		this.anzahlHaeuser = 5;
    		anzahlHotel = anzahlHaeuser - 5;
    	}else{
    		anzahlHaeuser = anzahlHaeuser;
    	}
    	System.out.println("Häuser: "+anzahlHaeuser +" Hotels: "+anzahlHotel);
    	renovierungsKosten = ((800 * anzahlHaeuser)+ (2300 * anzahlHotel));
    	Verwalter.getInstance().getCurSpieler().addGeld(-renovierungsKosten);

        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

        
        

    }
}
