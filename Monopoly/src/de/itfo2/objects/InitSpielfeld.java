package de.itfo2.objects;

import de.itfo2.fields.*;
import de.itfo2.objects.cards.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class InitSpielfeld {
	
	public static ArrayList<Karte> getEreigniskarten(){
        ArrayList<Karte> ereigniskarten = new ArrayList<Karte>();
        ereigniskarten.add(new Ereigniskarte01());
        ereigniskarten.add(new Ereigniskarte02()); // Gefängnisfreikarte
        ereigniskarten.add(new Ereigniskarte03());
        ereigniskarten.add(new Ereigniskarte04());
        ereigniskarten.add(new Ereigniskarte05());
        ereigniskarten.add(new Ereigniskarte06());
        ereigniskarten.add(new Ereigniskarte07());
        ereigniskarten.add(new Ereigniskarte08());
        ereigniskarten.add(new Ereigniskarte09());
        ereigniskarten.add(new Ereigniskarte10());
        ereigniskarten.add(new Ereigniskarte11());
        ereigniskarten.add(new Ereigniskarte12());
        ereigniskarten.add(new Ereigniskarte13());
        ereigniskarten.add(new Ereigniskarte14());
        ereigniskarten.add(new Ereigniskarte15());
        ereigniskarten.add(new Ereigniskarte16());
        Collections.shuffle(ereigniskarten);
        return ereigniskarten;
	}
	public static ArrayList<Karte> getGemeischaftskarten(){
        ArrayList<Karte> gemeinschaftskarten = new ArrayList<Karte>();
        gemeinschaftskarten.add(new Gemeinschaftskarte01());
        gemeinschaftskarten.add(new Gemeinschaftskarte02());
        gemeinschaftskarten.add(new Gemeinschaftskarte03());
        gemeinschaftskarten.add(new Gemeinschaftskarte04());
        gemeinschaftskarten.add(new Gemeinschaftskarte05());
        gemeinschaftskarten.add(new Gemeinschaftskarte06());
        gemeinschaftskarten.add(new Gemeinschaftskarte07());  // Gefängnisfreikarte
        gemeinschaftskarten.add(new Gemeinschaftskarte08());
        gemeinschaftskarten.add(new Gemeinschaftskarte09());
        gemeinschaftskarten.add(new Gemeinschaftskarte10());
        gemeinschaftskarten.add(new Gemeinschaftskarte11());
        gemeinschaftskarten.add(new Gemeinschaftskarte12());
        gemeinschaftskarten.add(new Gemeinschaftskarte13());
        gemeinschaftskarten.add(new Gemeinschaftskarte14());
        gemeinschaftskarten.add(new Gemeinschaftskarte15());
        gemeinschaftskarten.add(new Gemeinschaftskarte16());
        Collections.shuffle(gemeinschaftskarten);
        return gemeinschaftskarten;
	}
	
	public static ArrayList<Feld> getfelder() {
		
		ArrayList<Feld> feldArray = new ArrayList<Feld>();

        Los feld0 = new Los("Los");
        feldArray.add(feld0);
		
		Strasse feld1 = new Strasse();
		feld1.setBezeichnung("Badstrasse");
		feld1.setPreis(1200);
		feld1.setHausKosten(1000);
		int[] miete1 = {40, 200, 600, 1800, 3200, 5000};
		feld1.setMiete(miete1);
		feld1.setFarbe(Color.CYAN);
		feldArray.add(feld1);

        Gemeinschaftsfeld feld2 = new Gemeinschaftsfeld("Gemeinschaftsfeld");
        feldArray.add(feld2);
		
		Strasse feld3 = new Strasse();
		feld3.setBezeichnung("Turmstrasse");
		feld3.setPreis(1200);
		feld3.setHausKosten(1000);
		int[] miete3 = {80, 400, 1200, 3600, 6400, 9000};
		feld3.setMiete(miete3);
		feld3.setFarbe(Color.CYAN);
		feldArray.add(feld3);

        Steuerfeld feld4 = new Steuerfeld("Einkommenssteuer", 2000);
        feldArray.add(feld4);

        Bahnhof feld5 = new Bahnhof("Suedbahnhof", 4000, 500);
        feldArray.add(feld5);
		
		Strasse feld6 = new Strasse();
		feld6.setBezeichnung("Chauseestrasse");
		feld6.setPreis(2000);
		feld6.setHausKosten(1000);
		int[] miete6 = {120, 600, 1800, 5400, 8000, 11000};
		feld6.setMiete(miete6);
		feld6.setFarbe(Color.MAGENTA);
		feldArray.add(feld6);

        Ereignisfeld feld7 = new Ereignisfeld("Ereignisfeld");
        feldArray.add(feld7);
		
		Strasse feld8 = new Strasse();
		feld8.setBezeichnung("Elisenstrasse");
		feld8.setPreis(2000);
		feld8.setHausKosten(1000);
		int[] miete8 = {120, 600, 1800, 5400, 8000, 11000};
		feld8.setMiete(miete8);
		feld8.setFarbe(Color.MAGENTA);
		feldArray.add(feld8);
		
		Strasse feld9 = new Strasse();
		feld9.setBezeichnung("Poststrasse");
		feld9.setPreis(2000);
		feld9.setHausKosten(1000);
		int[] miete9 = {120, 600, 1800, 5400, 8000, 11000};
		feld9.setMiete(miete9);
		feld9.setFarbe(Color.MAGENTA);
		feldArray.add(feld9);

        Gefaengnis feld10 = new Gefaengnis("Gefaengnis");
        feldArray.add(feld10);
		
		Strasse feld11 = new Strasse();
		feld11.setBezeichnung("Seestrasse");
		feld11.setPreis(2800);
		feld11.setHausKosten(2000);
		int[] miete11 = {200, 1000, 3000, 9000, 12500, 15000};
		feld11.setMiete(miete11);
		feld11.setFarbe(Color.PINK);
		feldArray.add(feld11);

        Werk feld12 = new Werk("Elektrizitaetswerk", 3000);
        feldArray.add(feld12);
		
		Strasse feld13 = new Strasse();
		feld13.setBezeichnung("Hafenstrasse");
		feld13.setPreis(2800);
		feld13.setHausKosten(2000);
		int[] miete13 = {200, 1000, 3000, 9000, 12500, 15000};
		feld13.setMiete(miete13);
		feld13.setFarbe(Color.PINK);
		feldArray.add(feld13);
		
		Strasse feld14 = new Strasse();
		feld14.setBezeichnung("Neue Strasse");
		feld14.setPreis(2800);
		feld14.setHausKosten(2000);
		int[] miete14 = {200, 1000, 3000, 9000, 12500, 15000};
		feld14.setMiete(miete14);
		feld14.setFarbe(Color.PINK);
		feldArray.add(feld14);

        Bahnhof feld15 = new Bahnhof("Westbahnhof", 4000, 500);
        feldArray.add(feld15);
		
		Strasse feld16 = new Strasse();
		feld16.setBezeichnung("Muechner Strasse");
		feld16.setPreis(3600);
		feld16.setHausKosten(2000);
		int[] miete16 = {280, 1400, 4000, 11000, 15000, 19000};
		feld16.setMiete(miete16);
		feld16.setFarbe(Color.ORANGE);
		feldArray.add(feld16);

        Gemeinschaftsfeld feld17 = new Gemeinschaftsfeld("Gemeinschaftsfeld");
        feldArray.add(feld17);
		
		Strasse feld18 = new Strasse();
		feld18.setBezeichnung("Wiener Strasse");
		feld18.setPreis(3600);
		feld18.setHausKosten(2000);
		int[] miete18 = {280, 1400, 4000, 11000, 15000, 19000};
		feld18.setMiete(miete18);
		feld18.setFarbe(Color.ORANGE);
		feldArray.add(feld18);
		
		Strasse feld19 = new Strasse();
		feld19.setBezeichnung("Berliner Strasse");
		feld19.setPreis(4000);
		feld19.setHausKosten(2000);
		int[] miete19 = {320, 1600, 4400, 12000, 16000, 20000};
		feld19.setMiete(miete19);
		feld19.setFarbe(Color.ORANGE);
		feldArray.add(feld19);

        Freiparken feld20 = new Freiparken("Frei Parken");
        feldArray.add(feld20);

        Strasse feld21 = new Strasse();
        feld21.setBezeichnung("Theaterstrasse");
        feld21.setPreis(4400);
        feld21.setHausKosten(3000);
        int[] miete21 = {360, 1800, 5000, 14000, 17500, 21000};
        feld21.setMiete(miete21);
        feld21.setFarbe(Color.RED);
        feldArray.add(feld21);

        Ereignisfeld feld22 = new Ereignisfeld("Ereignisfeld");
        feldArray.add(feld22);

        Strasse feld23 = new Strasse();
        feld23.setBezeichnung("Museumsstrasse");
        feld23.setPreis(4400);
        feld23.setHausKosten(3000);
        int[] miete23 = {360, 1800, 5000, 14000, 17500, 21000};
        feld23.setMiete(miete23);
        feld23.setFarbe(Color.RED);
        feldArray.add(feld23);

        Strasse feld24 = new Strasse();
        feld24.setBezeichnung("Opernplatz");
        feld24.setPreis(4800);
        feld24.setHausKosten(3000);
        int[] miete24 = {400, 2000, 6000, 15000, 18500, 22000};
        feld24.setMiete(miete24);
        feld24.setFarbe(Color.RED);
        feldArray.add(feld24);

        Bahnhof feld25 = new Bahnhof("Nordbahnhof", 4000, 500);
        feldArray.add(feld25);

        Strasse feld26 = new Strasse();
        feld26.setBezeichnung("Lessingstrasse");
        feld26.setPreis(5200);
        feld26.setHausKosten(3000);
        int[] miete26 = {440, 2200, 6600, 16000, 19500, 23000};
        feld26.setMiete(miete26);
        feld26.setFarbe(Color.YELLOW);
        feldArray.add(feld26);

        Strasse feld27 = new Strasse();
        feld27.setBezeichnung("Schillerstrasse");
        feld27.setPreis(5200);
        feld27.setHausKosten(3000);
        int[] miete27 = {440, 2200, 6600, 16000, 19500, 23000};
        feld27.setMiete(miete27);
        feld27.setFarbe(Color.YELLOW);
        feldArray.add(feld27);

        Werk feld28 = new Werk("Wasserwerk", 3000);
        feldArray.add(feld28);

        Strasse feld29 = new Strasse();
        feld29.setBezeichnung("Goethestrasse");
        feld29.setPreis(5600);
        feld29.setHausKosten(3000);
        int[] miete29 = {440, 2400, 7200, 17000, 20500, 24000};
        feld29.setMiete(miete29);
        feld29.setFarbe(Color.YELLOW);
        feldArray.add(feld29);

        GeheGefaengnis feld30 = new GeheGefaengnis("Gehe ins Gefaengnis");
        feldArray.add(feld30);

        Strasse feld31 = new Strasse();
        feld31.setBezeichnung("Rathausplatz");
        feld31.setPreis(6000);
        feld31.setHausKosten(4000);
        int[] miete31 = {520, 2600, 7800, 18000, 22000, 25500};
        feld31.setMiete(miete31);
        feld31.setFarbe(Color.GREEN);
        feldArray.add(feld31);

        Strasse feld32 = new Strasse();
        feld32.setBezeichnung("Hauptstrasse");
        feld32.setPreis(6000);
        feld32.setHausKosten(4000);
        int[] miete32 = {520, 2600, 7800, 18000, 22000, 25500};
        feld32.setMiete(miete32);
        feld32.setFarbe(Color.GREEN);
        feldArray.add(feld32);

        Gemeinschaftsfeld feld33 = new Gemeinschaftsfeld("Gemeinschaftsfeld");
        feldArray.add(feld33);

        Strasse feld34 = new Strasse();
        feld34.setBezeichnung("Bahnhofsstrasse");
        feld34.setPreis(6400);
        feld34.setHausKosten(4000);
        int[] miete34 = {560, 3000, 9000, 20000, 24000, 28000};
        feld34.setMiete(miete34);
        feld34.setFarbe(Color.GREEN);
        feldArray.add(feld34);

        Bahnhof feld35 = new Bahnhof("Hauptbahnhof", 4000, 500);
        feldArray.add(feld35);

        Ereignisfeld feld36 = new Ereignisfeld("Ereignisfeld");
        feldArray.add(feld36);

        Strasse feld37 = new Strasse();
        feld37.setBezeichnung("Parkstrasse");
        feld37.setPreis(7000);
        feld37.setHausKosten(4000);
        int[] miete37 = {700, 3500, 10000, 22000, 26000, 30000};
        feld37.setMiete(miete37);
        feld37.setFarbe(Color.BLUE);
        feldArray.add(feld37);

        Steuerfeld feld38 = new Steuerfeld("Zusatzsteuer", 4000);
        feldArray.add(feld38);
		
		Strasse feld39 = new Strasse();
		feld39.setBezeichnung("Schlossallee");
		feld39.setPreis(8000);
		feld39.setHausKosten(4000);
		int[] miete = {1000, 4000, 12000, 28000, 34000, 40000};
		feld39.setMiete(miete);
		feld39.setFarbe(Color.BLUE);
		feldArray.add(feld39);

		return feldArray;
	}
	
	
	
}
