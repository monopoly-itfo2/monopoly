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
//        Verwalter.getInstance().getCurSpieler().addGeld(-3000);
//        TODO Zahle an die Bank, fuer jedes Haus 500 DM, fuer jedes Hotel 2000 DM
    	
    	//Warum wird diese Variable nicht genutzt ? Fabian
    	int anzahlHaeuser = Verwalter.getInstance().getAlleHaeuser(Verwalter.getInstance().getCurSpieler());
    	if(anzahlHaeuser > 5){
    		this.anzahlHaeuser = 5;
    		anzahlHotel = anzahlHaeuser - 5;
    	}else{
    		anzahlHaeuser = anzahlHaeuser;
    	}
    	System.out.println("Haeuser: "+anzahlHaeuser +" Hotels: "+anzahlHotel);
    	renovierungsKosten = ((500 * anzahlHaeuser)+ (2000 * anzahlHotel));
    	Verwalter.getInstance().getCurSpieler().addGeld(-renovierungsKosten);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

    }
}
