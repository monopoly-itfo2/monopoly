package de.itfo2.objects;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import de.itfo2.ui.MonopolyGUI;



public class Verwalter {
    public int wert;
    public int pasch = 0;
    public boolean spiel = true;
    ArrayList<Spieler> spieler = new ArrayList<Spieler>();
    private static Verwalter instance = null;
    int spielerAmZug = -1;
    Spielfeld spielfeld;

    public Verwalter() throws IOException {
        init();
        spielerAmZug = 1;
        //play();

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

    public void play() {

        System.out.println("Start");

        //init();

        while(spiel == true){

            //Wuerfeln

            // int wuerfelZahl = wuerfeln();

            //Ziehen

            //spieler.get(spielerAmZug).addPlatz(wuerfelZahl);


            //Feld behandeln

            //spielfeld[spieler.get(spielerAmZug).getPlatz()];


            //MainPhase


            //Spieler-wechsel

            //wenn ein Pasch gewuerfelt wurde
            if(pasch != 0) {
                //gleicher.spieler
            } else {
                pasch = 0;
                //next.spieler
            }

//    		 if(gewonnen)
//          	 spiel = false;
        }
    }
    private void init() throws IOException {

        spielfeld = new Spielfeld(InitSpielfeld.getfelder());
        MonopolyGUI.getInstance().setSpielfeld(spielfeld);

        //Spieler spieler1 = new Spieler("Spieler 1", 10000, Color.getHSBColor(269f, 35f, 96f));
        Spieler spieler1 = new Spieler("Spieler 1", 10000, Color.getHSBColor(0.9f, 0.1f, 0.7f));
        Spieler spieler2 = new Spieler("Spieler 2", 10000, Color.getHSBColor(0.3f, 0.1f, 0.9f));
        spieler.add(spieler1);
        MonopolyGUI.getInstance().addSpieler(0, spieler1);
        spieler.add(spieler2);
        MonopolyGUI.getInstance().addSpieler(1, spieler2);
        MonopolyGUI.getInstance().updateFeld();
    }

    public Spieler getCurSpieler(){
        return spieler.get(spielerAmZug);
    }

}
