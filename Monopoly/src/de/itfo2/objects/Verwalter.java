package de.itfo2.objects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import de.itfo2.event.EventBus;
import de.itfo2.fields.Feld;
import de.itfo2.fields.Grundstueck;
import de.itfo2.fields.Strasse;
import de.itfo2.ui.MonopolyGUI;

public class Verwalter {
	public int wuerfelZahl;
	public int pasch = 0;
	public boolean spielAmLaufen = true;
	ArrayList<Spieler> spieler = new ArrayList<Spieler>();
	private static Verwalter instance = null;
	int spielerAmZug = -1;
	Spielfeld spielfeld;
	MonopolyGUI gui = MonopolyGUI.getInstance();
	boolean gewuerfelt;
//	final EventBus bus = EventBus.getInstance();//temporary disabled

	public Verwalter() throws IOException {
		play();

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
		else
			pasch = 0;

		wuerfelZahl = ersterWert + zweiterWert;
        //wuerfelZahl = 1;
		 System.out.println("###### "+ersterWert+" / "+zweiterWert);
        gewuerfelt = true;
	}

	public void play() throws IOException {

		init();

        initGuiButtonFunctions();
	}

	private void init() throws IOException {

		spielfeld = new Spielfeld(InitSpielfeld.getfelder(), InitSpielfeld.getEreigniskarten(), InitSpielfeld.getEreigniskarten());
		gui.setSpielfeld(spielfeld);

		// Spieler spieler1 = new Spieler("Spieler 1", 10000,
		// Color.getHSBColor(269f, 35f, 96f));

        //Spieler 1
		Spieler spieler1 = new Spieler("Spieler 1", 10000, Color.getHSBColor(
				0.9f, 0.1f, 0.7f));

        spieler1.addObserver(gui.getStatusPanel(0));
		spieler.add(spieler1);
		gui.addSpieler(0, spieler1);
        gui.getStatusPanel(0).update(spieler1, null);

        //Spieler 2
        Spieler spieler2 = new Spieler("Spieler 2", 10000, Color.getHSBColor(
                0.3f, 0.1f, 0.9f));
        spieler2.addObserver(gui.getStatusPanel(1));
		spieler.add(spieler2);
		gui.addSpieler(1, spieler2);
        gui.getStatusPanel(1).update(spieler2, null);

	}

	public Spieler getCurSpieler() {
		return spieler.get(spielerAmZug);
	}
    public Spieler getNextSpieler() {
        return spieler.get((spielerAmZug+1)%getSpieleranzahl());
    }

	public int getSpielerAmZug() {
		return spielerAmZug;
	}

	public void initGuiButtonFunctions() {

        /*****************************************************
        Regelt, was beim würfeln passiert
        *****************************************************/
		gui.setRollDiceButtonActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                wuerfeln();
                //TODO curSPieler = spieler.get(spielerAmZug);
                // hier die Rune rein
                System.out.println("Sopieler an der Reihe: "+spielerAmZug);
                if (pasch == 3) {//TODO spieler.get(spielerAmZug) ersetzen durh curSpieler am Anfang jeder "Schleife"
                    // geheInsGefaengnis
                    spieler.get(spielerAmZug).setImGefaengnis(true);
                    gui.geheInsGefaengnis(spielerAmZug);
                    pasch = 0;

                } else {

                    // Ziehen
                    try {
                        gui.rueckeVor(wuerfelZahl);
                    } catch (IOException e1) {
                    }
                    spieler.get(spielerAmZug).addPlatz(wuerfelZahl);

                    System.out.println("Wrfel ergebnis: "+wuerfelZahl);

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
                } else {// TODO: nextPlayerButtonEventListener und oben noh startTheHoleFukingGameEventListener
                    gui.setNextButtonEnabled(true);
                    gui.setRollDiceButtonEnabled(false);
                }
            }
        });
        gui.setRollDiceButtonEnabled(false);

        /*****************************************************
         Startet das Spiel
         *****************************************************/
        gui.setStartButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.addLogMessage("Spiel gestartet!");
                spielerAmZug = 0;
                gui.setRollDiceButtonEnabled(true);
            }
        });

        /*****************************************************
         Nächster Spieler an der Reihe
         *****************************************************/
        gui.setNextButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gui.naechsterSpieler();
                } catch (IOException e1) {
                }
                spielerAmZug = (spielerAmZug+1)%getSpieleranzahl();
                gui.setRollDiceButtonEnabled(true);
            }
        });
        gui.setNextButtonEnabled(false);
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

        /*****************************************************
         Grundstück kaufen
         *****************************************************/
		gui.setBuyButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                Grundstueck gr = (Grundstueck) spielfeld.getFeld(getCurSpieler().getPlatz());
                if(getCurSpieler().getKonto() >= gr.getPreis()){
                    getCurSpieler().addGeld(-gr.getPreis());
                    gr.setBesitzer(getCurSpieler());
                    gui.addLogMessage(getCurSpieler().getName() + " kaufte " + "\"" + gr.getBezeichnung() + "\"");
                    gui.kaufeFeld();
                }else{
                    gui.createPopupDialog("Geh halt arbeiten!");
                }
			}
		});

        /*****************************************************
         Haus auf aktueller Straße kaufen
         *****************************************************/
		gui.setBuildHouseButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                Strasse st = (Strasse)spielfeld.getFeld(getCurSpieler().getPlatz());
                if(getCurSpieler().getKonto() >= st.getHausKosten()){
                    getCurSpieler().addGeld(-st.getHausKosten());
                    st.addMietePointer();
                    gui.addLogMessage(getCurSpieler().getName() +" kaufte ein Haus auf " + st.getBezeichnung());
                    gui.baueHaus();
                }else{
                    gui.createPopupDialog("Geh halt arbeiten!");
                }
			}
		});
	}

    public int getSpieleranzahl(){
        return spieler.size();
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }
}
