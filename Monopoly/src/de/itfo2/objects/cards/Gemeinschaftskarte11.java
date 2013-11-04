package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte11 extends Karte{
	private boolean t;
    final String text = "Zahle eine Strafe von 200 DM oder nimm eine Ereigniskarte";

    public Gemeinschaftskarte11() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum BestÃ¤tigen.
        if(t == true){
        	Verwalter.getInstance().getCurSpieler().addGeld(-200);
        }else{
        	//TODO Ereigniskarte auslösen
        }
        
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
