package de.itfo2.objects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import de.itfo2.event.EventBus;
import de.itfo2.ui.MonopolyGUI;

public class Verwalter {
	public int wuerfelZahl;
	public int pasch = 0;
	public boolean spielAmLaufen = true;
	ArrayList<Spieler> spieler = new ArrayList<Spieler>();
	private static Verwalter instance = null;
	int spielerAmZug;
	Spielfeld spielfeld;
	MonopolyGUI gui = MonopolyGUI.getInstance();
	boolean gewuerfelt;
//	final EventBus bus = EventBus.getInstance();//temporary disabled

	public Verwalter() throws IOException {
		spielerAmZug = 0;
		play();

		// System.out.println(spielfeld.ereignis.length);
		// for(int i = 1;i<=spielfeld.ereignis.length;i++){
		// System.out.println( i);
		// }
	}

	public static Verwalter getInstance() throws IOException {
		if (instance == null) {
			instance = new Verwalter();
		}
		return instance;
	}

	public void wuerfeln() {
		int ersterWert;
		int zweiterWert;
		Wuerfel wuerfel = new Wuerfel();
		ersterWert = wuerfel.getWert();
		zweiterWert = wuerfel.getWert();

		if (ersterWert == zweiterWert)
			pasch++;

		wuerfelZahl = ersterWert + zweiterWert;
		// System.out.println("###### "+ersterWert+" / "+zweiterWert);
	}

	public void play() throws IOException {

		System.out.println("Start");

		init();

		gui.setRollDiceButtonActionListener(new ActionListener() {// dummi, muss ersetzt werden durch einen neuen Button "Runde fertig"

			
			@Override
			public void actionPerformed(ActionEvent e) {
				wuerfeln();
				// hier die Rune rein
				System.out.println("Sopieler an der Reihe: "+spielerAmZug);
				if (pasch == 3) {
					// geheInsGefÃ¤ngnis
					pasch = 0;

				} else {

					// Ziehen

					spieler.get(spielerAmZug).addPlatz(wuerfelZahl);

					System.out.println("Würfel ergebnis: "+wuerfelZahl);

					try {
						MonopolyGUI.getInstance().rueckeVor(0, wuerfelZahl);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// Feld behandeln

					int actualPlayerPosition = spieler.get(spielerAmZug)
							.getPlatz();

					spielfeld.getFeld(actualPlayerPosition).handleFieldEffect();

					// MainPhase

					// Spieler-wechsel

					// wenn ein Pasch gewuerfelt wurde
				}
				if (pasch != 0) {
					// gleicher.spieler
				} else {
					if (spielerAmZug == (spieler.size()-1)) {
						spielerAmZug = 0;
					} else {
						spielerAmZug++;
					}
				}
			}
		});
	}

	private void init() throws IOException {

		spielfeld = new Spielfeld(InitSpielfeld.getfelder());
		gui.setSpielfeld(spielfeld);

		// Spieler spieler1 = new Spieler("Spieler 1", 10000,
		// Color.getHSBColor(269f, 35f, 96f));
		Spieler spieler1 = new Spieler("Spieler 1", 10000, Color.getHSBColor(
				0.9f, 0.1f, 0.7f));
		Spieler spieler2 = new Spieler("Spieler 2", 10000, Color.getHSBColor(
				0.3f, 0.1f, 0.9f));
		spieler.add(spieler1);
		gui.addSpieler(0, spieler1);
		spieler.add(spieler2);
		gui.addSpieler(1, spieler2);
		// MonopolyGUI.getInstance().updateFeld();

	}

	public Spieler getCurSpieler() {
		return spieler.get(spielerAmZug);
	}

	public int getSpielerAmZug() {
		return spielerAmZug;
	}

	public void initGuiButtonFunctions() {
		gui.setRollDiceButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		gui.setEreigniskartenButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		gui.setGemeinschaftskartenButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		gui.setBuyButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		gui.setBuildHouseButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}
}
