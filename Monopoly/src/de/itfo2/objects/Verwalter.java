package de.itfo2.objects;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import de.itfo2.ui.MonopolyGUI;



public class Verwalter {
    public int wert;
    public int pasch = 0;
    public boolean spielAmLaufen = true;
    ArrayList<Spieler> spieler = new ArrayList<Spieler>();
    private static Verwalter instance = null;
    int spielerAmZug = -1;
    Spielfeld spielfeld;

    public Verwalter() throws IOException {
        spielerAmZug = 1;
        play();

//		System.out.println(spielfeld.ereignis.length);
//		for(int i = 1;i<=spielfeld.ereignis.length;i++){
//			System.out.println( i);
//		}
    }

    public static Verwalter getInstance() throws IOException {
        if(instance == null){
            instance = new Verwalter();
        }
        return instance;
    }

    public int wuerfeln() {
        int ersterWert;
        int zweiterWert;
        Wuerfel wuerfel = new Wuerfel();
        ersterWert = wuerfel.getWert();
        zweiterWert = wuerfel.getWert();
        
        if (ersterWert == zweiterWert)
            pasch++;

        wert = ersterWert + zweiterWert;
//		System.out.println("###### "+ersterWert+" / "+zweiterWert);
        return wert;
    }

    public void play() throws IOException {

        System.out.println("Start");

        init();

        while(spielAmLaufen){

            //Wuerfeln

        	int wuerfelZahl = wuerfeln();
        	
        	if(pasch==3){
        		//geheInsGefängnis
        		pasch=0;
        		
        	}else{
	        	
	            //Ziehen
	
	            spieler.get(spielerAmZug).addPlatz(wuerfelZahl);
	
	            //Feld behandeln
	
	            int actualPlayerPosition = spieler.get(spielerAmZug).getPlatz();
	            
	            spielfeld.getFeld(actualPlayerPosition).handleFieldEffect();
	            
	            //MainPhase
	
	
	            //Spieler-wechsel

	            //wenn ein Pasch gewuerfelt wurde
        	}
            if(pasch != 0) {
                //gleicher.spieler
            } else {
                if(spielerAmZug == spieler.size()){
                	spielerAmZug = 0;
                }else{
                	spielerAmZug++;
                }
              	 
            }

//    		 if(gewonnen)
//          	 spiel = false;
        }
    }
    private void init() throws IOException {

        spielfeld = new Spielfeld(InitSpielfeld.getfelder());
        MonopolyGUI.getInstance().setSpielfeld(spielfeld);

//        Spieler spieler1 = new Spieler("Spieler 1", 10000, Color.getHSBColor(269f, 35f, 96f));
        Spieler spieler1 = new Spieler("Spieler 1", 10000, Color.getHSBColor(0.9f, 0.1f, 0.7f));
        Spieler spieler2 = new Spieler("Spieler 2", 10000, Color.getHSBColor(0.3f, 0.1f, 0.9f));
        spieler.add(spieler1);
        MonopolyGUI.getInstance().addSpieler(0, spieler1);
        spieler.add(spieler2);
        MonopolyGUI.getInstance().addSpieler(1, spieler2);
//        MonopolyGUI.getInstance().updateFeld();
    }

    public Spieler getCurSpieler(){
        return spieler.get(spielerAmZug);
    }

	public int getSpielerAmZug(){
        return spielerAmZug;
    }
}
