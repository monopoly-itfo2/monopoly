package de.itfo2.objects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import de.itfo2.event.EventBus;
import de.itfo2.event.RundenendeEvent;
import de.itfo2.event.UpdateGUISperrenEvent;
import de.itfo2.event.UpdateGeldEvent;
import de.itfo2.event.UpdateSpielerlisteEvent;
import de.itfo2.event.WuerfelEvent;
import de.itfo2.event.listeners.RundenendeEventListener;
import de.itfo2.event.listeners.UpdateGUISperrenEventListener;
import de.itfo2.event.listeners.UpdateGeldEventListener;
import de.itfo2.event.listeners.UpdateSpielerlisteEventListener;
import de.itfo2.event.listeners.WuerfelEventListener;
import de.itfo2.fields.Ereignisfeld;
import de.itfo2.fields.Gemeinschaftsfeld;
import de.itfo2.fields.Grundstueck;
import de.itfo2.fields.Strasse;
import de.itfo2.network.Connector;
import de.itfo2.ui.MonopolyGUI;

public class Verwalter {
	private int wuerfelZahl;
	public int pasch = 0;
	public boolean spielAmLaufen = true;
	public List<Spieler> spielerListe = new ArrayList<Spieler>();
	private static Verwalter instance = null;
	boolean meinZug = false;
	Spielfeld spielfeld;
	MonopolyGUI gui = MonopolyGUI.getInstance();
	boolean gewuerfelt;
	boolean hypothekenauswahl = false;
	private Spieler meinSpieler;
	private Spieler spielerAmZug;

	public Verwalter() {
		init();
		initListeners();
		initGuiButtonFunctions();
	}

	public static Verwalter getInstance() {
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

		pasch = (ersterWert == zweiterWert) ? pasch + 1 : 0;

		wuerfelZahl = ersterWert + zweiterWert;

		gewuerfelt = true;
		gui.setRollDiceButtonEnabled(false);
		gui.addLogMessage(meinSpieler.getName() + " hat eine " + wuerfelZahl
				+ " gewuerfelt.");
		EventBus.getInstance().sinkClientEvent(
				new WuerfelEvent(meinSpieler.getName(), wuerfelZahl));
	}

	public Spieler getCurSpieler() {
		return spielerAmZug;
	}

	private void init() {
		spielfeld = new Spielfeld(InitSpielfeld.getfelder(),
				InitSpielfeld.getEreigniskarten(),
				InitSpielfeld.getEreigniskarten());
		gui.setSpielfeld(spielfeld);
	}

	private void initListeners() {
		EventBus bus = EventBus.getInstance();
		bus.addRundenendeEventListener(new RundenendeEventListener() {
			@Override
			public void onEvent(RundenendeEvent event) {
				System.out.println("rundenende von " + event.getSpielername()
						+ " angekommen bei : " + meinSpieler.getName());
				int spieleranzahl = spielerListe.size();
				int meineListenPosition = 0;
				int eventListenPosition = 0;
				String eventSpielername = event.getSpielername();
				for (int i = 0; i < spieleranzahl; i++) {
					Spieler s = spielerListe.get(i);

					System.out.println(s.getName() + " pos: " + i);

					if (meinSpieler.getName().equals(s.getName())) {
						meineListenPosition = i;
					}
					if (eventSpielername.equals(s.getName())) {
						eventListenPosition = i;
					}
				}
				System.out.println("meine pos: " + meineListenPosition);
				System.out.println("event pos: " + eventListenPosition);
				if ((eventListenPosition + 1) % spieleranzahl == meineListenPosition) {
					gui.addLogMessage("Ich bin am Zug!");
					spielerAmZug = meinSpieler;
					gui.entsperren(meinSpieler);
				} else {
					gui.addLogMessage(spielerAmZug.getName() + " ist am Zug!");
				}
			}
		});

		bus.addWuerfelEventListener(new WuerfelEventListener() {
			@Override
			public void onEvent(WuerfelEvent event) {
				String spielername = event.getSpielername();
				int wert = event.getWert();
				if (spielerAmZug.getName().equals(spielername)) {
					gui.rueckeVor(wert);

					spielerListe.get(spielerListe.indexOf(spielerAmZug))
							.addGUIPlatz(wert);

					gui.addLogMessage(spielername + " hat eine " + wert
							+ " gewuerfelt.");
				}
			}
		});

		bus.addUpdateSpielerlisteEventListener(new UpdateSpielerlisteEventListener() {
			@Override
			public void onEvent(UpdateSpielerlisteEvent event) {
				List<Spieler> liste = Connector.getInstance().getSpielerliste();
				System.out.println("UpdateSpielerListe Event");
				for (Spieler s : liste) {
					if (!s.getName().equals(meinSpieler.getName())) {
						System.out.println("Spieler gefunden: " + s.getName());
						int observerPosition = liste.contains(meinSpieler) ? liste
								.size() : liste.size() + 1;
						s.addObserver(gui.getStatusPanel(observerPosition));

						spielerListe.add(s);
						gui.addSpieler(observerPosition, s);
						gui.getStatusPanel(observerPosition).update(s, null);
					}
				}
			}
		});
		bus.addUpdateGeldEventListener(new UpdateGeldEventListener() {
			@Override
			public void onEvent(UpdateGeldEvent event) {
				if (spielerAmZug.getName().equals(event.getSpielername())) {
					spielerAmZug.addGUIGeld(event.getGeldVeraenderung());
				}
			}
		});
		bus.addUpdateGUISperrenEventListener(new UpdateGUISperrenEventListener() {

			@Override
			public void onEvent(UpdateGUISperrenEvent event) {
				for (Spieler s : spielerListe) {
					if (s.getName().equals(event.getName())) {
						// gui.sperren(s);
					}
				}
			}
		});
	}

	public void initGuiButtonFunctions() {

		/*****************************************************
		 * Regelt, was beim wuerfeln passiert *
		 *****************************************************/
		gui.setRollDiceButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wuerfeln();
				// TODO curSPieler = spieler.get(spielerAmZug);
				// hier die Rune rein
				// System.out.println("spieler an der Reihe: "+spielerAmZug);
				if (pasch == 3) {// TODO spieler.get(spielerAmZug) ersetzen durh
									// curSpieler am Anfang jeder "Schleife"
					// geheInsGefaengnis
					meinSpieler.setImGefaengnis(true);
					gui.geheInsGefaengnis(meinSpieler);
					pasch = 0;

				} else {

					// Ziehen
					gui.rueckeVor(wuerfelZahl);
					meinSpieler.addPlatz(wuerfelZahl);

					// Feld behandeln

					int actualPlayerPosition = meinSpieler.getPlatz();

					try {
						spielfeld.getFeld(actualPlayerPosition)
								.handleFieldEffect();

					} catch (IOException e1) {
					}

					// MainPhase

					// Spieler-wechsel

					// wenn ein Pasch gewuerfelt wurde
				}
				if (spielfeld.getFeld(spielerAmZug.getPlatz()) instanceof Ereignisfeld
						|| spielfeld.getFeld(spielerAmZug.getPlatz()) instanceof Gemeinschaftsfeld) {
					gui.setNextButtonEnabled(false);
					gui.setRollDiceButtonEnabled(false);
				} else {
					if (pasch != 0) {
						gui.setNextButtonEnabled(false);
						gui.setRollDiceButtonEnabled(true);
					} else {
						gui.setNextButtonEnabled(true);
						gui.setRollDiceButtonEnabled(false);
					}
				}

			}
		});
		gui.setRollDiceButtonEnabled(false);

		/*****************************************************
		 * Startet das Spiel
		 *****************************************************/
		gui.setStartButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.addLogMessage("Spiel gestartet!");
				Collections.sort(spielerListe);
				System.out.println(spielerListe.get(0).getName());
				System.out.println(spielerListe.get(1).getName());
				spielerAmZug = spielerListe.get(0);
				if (meinZug = spielerAmZug.getName().equals(
						meinSpieler.getName())) {
					gui.setRollDiceButtonEnabled(true);
				}
				gui.setStartButtonEnabled(false);
				gui.addLogMessage(spielerListe.get(0).getName() + " startet.");
			}
		});

		/*****************************************************
		 * Naechster Spieler an der Reihe
		 *****************************************************/
		gui.setNextButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.sperren(meinSpieler);
				meinZug = !meinZug;
				gui.updateHypothekButtons();
				spielerAmZug = getNaechterSpieler();
				gui.addLogMessage(spielerAmZug.getName() + " ist jetzt am Zug.");
				EventBus.getInstance().sinkClientEvent(
						new RundenendeEvent(meinSpieler.getName()));
			}

		});
		gui.setNextButtonEnabled(false);

		/*****************************************************
		 * Login f�r Multiplayer
		 *****************************************************/
		gui.setLoginButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.addLogMessage("Login...");
				Connector.getInstance().login(meinSpieler);
				spielerListe = Connector.getInstance().getSpielerliste();
				meinSpieler.addObserver(gui.getStatusPanel(0));
				gui.addSpieler(0, meinSpieler);
				gui.getStatusPanel(0).update(meinSpieler, null);
				gui.setTitle("Monopoly - " + meinSpieler.getName());
			}
		});

		gui.setEreigniskartenButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getSpielfeld().getEreigniskarten()
						.get(spielfeld.getEreigniskartenPointer()).effect();
				spielfeld.setEreigniskartenPointer((spielfeld
						.getEreigniskartenPointer() + 1)
						% getSpielfeld().getEreigniskarten().size());
				gui.setEreigniskartenButtonEnabled(false);

				if (pasch != 0)
					gui.setRollDiceButtonEnabled(true);
				else
					gui.setNextButtonEnabled(true);

			}
		});
		gui.setEreigniskartenButtonEnabled(false);

		gui.setGemeinschaftskartenButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getSpielfeld().getGemeinschaftskarten()
						.get(spielfeld.getGemeinschaftskartenPointer())
						.effect();
				spielfeld.setGemeinschaftskartenPointer((spielfeld
						.getGemeinschaftskartenPointer() + 1)
						% getSpielfeld().getGemeinschaftskarten().size());
				gui.setGemeinschaftskartenButtonEnabled(false);

				if (pasch != 0)
					gui.setRollDiceButtonEnabled(true);
				else
					gui.setNextButtonEnabled(true);
			}
		});
		gui.setGemeinschaftskartenButtonEnabled(false);

		/*****************************************************
		 * Grundstück kaufen
		 *****************************************************/
		gui.setBuyButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Grundstueck gr = (Grundstueck) spielfeld.getFeld(spielerAmZug
						.getPlatz());
				if (spielerAmZug.getKonto() >= gr.getPreis()) {
					spielerAmZug.addGeld(-gr.getPreis());
					gr.setBesitzer(spielerAmZug);
					gui.addLogMessage(spielerAmZug.getName() + " kaufte "
							+ "\"" + gr.getBezeichnung() + "\"");
					gui.kaufeFeld();
					checkAlleFarben();
				} else {
					if (gui.createPopupChoiceDialog("Nicht genug Geld zum kaufen. Jetzt Hypothek aufnehmen?") == JOptionPane.YES_OPTION)
						;
					setHypothekenauswahl(true, false);
				}
			}
		});

		/*****************************************************
		 * Haus auf aktueller Straße kaufen
		 *****************************************************/
		gui.setBuildHouseButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Strasse st = (Strasse) spielfeld.getFeld(spielerAmZug
						.getPlatz());
				if (spielerAmZug.getKonto() >= st.getHausKosten()) {
					spielerAmZug.addGeld(-st.getHausKosten());
					st.addMietePointer();
					gui.addLogMessage(spielerAmZug.getName()
							+ " kaufte ein Haus auf " + st.getBezeichnung());
					gui.baueHaus();
				} else {
					gui.createPopupDialog("Geh halt arbeiten!");
				}
			}
		});

		/*****************************************************
		 * Hypothek zurückzahlen
		 *****************************************************/
		gui.setHypothekButtonActionListener();

		/*****************************************************
		 * Hypothekenauswahl, falls man nicht bezahlen kann
		 *****************************************************/
		gui.setGrundstueckMouseListener();
	}

	protected Spieler getNaechterSpieler() {
		int i = spielerListe.indexOf(meinSpieler);
		return spielerListe.get((i + 1) % spielerListe.size());
	}

	public int getSpieleranzahl() {
		return spielerListe.size();
	}

	public Spielfeld getSpielfeld() {
		return spielfeld;
	}

	public int getLastWuerfelZahl() {
		return wuerfelZahl;
	}

	public boolean isHypothekenauswahl() {
		return hypothekenauswahl;
	}

	public void setHypothekenauswahl(boolean hypothekenauswahl, boolean critical) {
		this.hypothekenauswahl = hypothekenauswahl;
		if (hypothekenauswahl) {
			checkIfHypothekVerfuegbar(critical);
		}
	}

	public int calculateToGo(int feldPosition) {
		int aktuellePosition = spielerAmZug.getPlatz();
		if (aktuellePosition > feldPosition) {
			return ((39 - aktuellePosition) + feldPosition);
		} else {
			return (feldPosition - aktuellePosition);
		}
	}

	public int getAlleHaeuser(Spieler aktuellerSpieler) {
		int anzahlHauser = 0;
		for (int i = 0; i < 40; i++) {
			Strasse strasse = (Strasse) spielfeld.getFeld(i);
			if (strasse != null) {
				Spieler besitzer = strasse.getBesitzer();
				if (aktuellerSpieler.equals(aktuellerSpieler)) {
					anzahlHauser += strasse.getMietePointer();
				}
			}
		}
		return anzahlHauser;
	}

	public void checkAlleFarben() {
		Color farbe = null;
		boolean alleFarben = true;
		for (int i = 0; i < 40; i++) {
			if (spielfeld.getFeld(i) instanceof Strasse) {
				Strasse st = (Strasse) spielfeld.getFeld(i);
				if (farbe != null) {
					if (!farbe.equals(st.getFarbe())) {
						if (alleFarben) {
							for (int j = 0; j >= 40; j++) {
								Strasse stTemp = (Strasse) spielfeld.getFeld(i);
								if (stTemp.getFarbe().equals(farbe))
									stTemp.setAlleFarben(true);
							}
						}
						alleFarben = true;
					} else {
						if (((Strasse) spielfeld.getFeld(i)).getBesitzer() == null) {
							alleFarben = false;
						}
						if (((Strasse) spielfeld.getFeld(i)).getBesitzer() != null) {
							if (!((Strasse) spielfeld.getFeld(i)).getBesitzer()
									.equals(spielerAmZug)) {
								alleFarben = false;
							}
						}
					}
				}
				farbe = st.getFarbe();
			}
		}
	}

	public void checkIfHypothekVerfuegbar(boolean critical) {
		boolean verfuegbar = false;
		String nochVerfuegbar = "Noch verfügbar: ";
		for (int i = 0; i < 40; i++) {
			if (spielfeld.getFeld(i) instanceof Grundstueck) {
				Grundstueck gr = (Grundstueck) spielfeld.getFeld(i);
				if (gr.getBesitzer() != null) {
					if (gr.getBesitzer().equals(spielerAmZug)) {
						if (!gr.isBelastet()) {
							verfuegbar = true;
							nochVerfuegbar += gr.getBezeichnung() + ", ";
						}
					}
				}
			}
		}
		if (verfuegbar) {
			nochVerfuegbar = nochVerfuegbar.substring(0,
					nochVerfuegbar.length() - 2);
			gui.addLogMessage(nochVerfuegbar);
		} else {
			if (critical) {
				gui.addLogMessage(spielerAmZug.getName()
						+ " Du hast keine Grundstücke für Hypotheken.");
				gui.addLogMessage(spielerAmZug.getName()
						+ " kann seine Schulden nicht mehr bezahlen.");
				gui.addLogMessage(spielerAmZug.getName()
						+ " scheidet aus dem Spiel aus. (noch zu implementieren)");
			} else {
				gui.addLogMessage(spielerAmZug.getName()
						+ " Du hast keine Grundstücke für Hypotheken.");
				setHypothekenauswahl(false, false);
			}

		}
	}

	public void login(String name) {
		meinSpieler = new Spieler(name, 5000);
	}

	public void connectionEstablished() {
		gui.addLogMessage("Connected");
		gui.createLoginDialog();
	}

	public Spieler getMeinSpieler() {
		return meinSpieler;
	}
}
