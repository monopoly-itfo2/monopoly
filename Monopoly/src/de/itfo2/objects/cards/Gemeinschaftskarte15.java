package de.itfo2.objects.cards;


import java.util.ArrayList;

import de.itfo2.network.Connector;
import de.itfo2.objects.Spieler;
import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte15 extends Karte{

    final String text = "Es ist dein Geburtstag, ziehe von jedem Spieler 200 Euro ein";

    public Gemeinschaftskarte15() {

    }

    @Override
    public void effect() {
//		TODO       ziehe von jedem Spieler 200 DM ein  
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        /*
         * 1. Jedem Spieler, auﬂer Aktuellem Spieler, werden dm 200 abgezogen, dabei i hohz‰hlen
         * 2. Aktuellem SPieler Geld geben, das da w‰re: 200 * i
         */
        
       ArrayList<Spieler> alleSpieler = Verwalter.getInstance().spielerliste;
       Spieler aktuellerSpieler = Verwalter.getInstance().getCurSpieler();
       int i = 0;
       for(Spieler spielerMussZahlen: alleSpieler){
    	   if(!aktuellerSpieler.equals(spielerMussZahlen)){    		   
    		   spielerMussZahlen.addGeld(-200);
    		   MonopolyGUI.getInstance().addLogMessage(spielerMussZahlen.getName()+" muss 200 Euro an "+aktuellerSpieler+" zahlen");
    		   i++;
    	   }    	   
       }       
       aktuellerSpieler.addGeld(200*i);
       
       MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

       
    }
}
