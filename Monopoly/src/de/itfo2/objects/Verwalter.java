package de.itfo2.objects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

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
	ArrayList<Spieler> spieler = new ArrayList<Spieler>();
	private static Verwalter instance = null;
	int spielerAmZug = -1;
	Spielfeld spielfeld;
	MonopolyGUI gui = MonopolyGUI.getInstance();
	boolean gewuerfelt;
    boolean hypothekenauswahl = false;

    //	final EventBus bus = EventBus.getInstance();//temporary disabled

	public Verwalter() {
		init();

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

		if (ersterWert == zweiterWert)
			pasch++;
		else
			pasch = 0;

		wuerfelZahl = ersterWert + zweiterWert;
        //wuerfelZahl = 2;
        gui.addLogMessage(getCurSpieler().getName() + " hat eine " + wuerfelZahl + " gewürfelt. (" + ersterWert + " + " + zweiterWert + ")");
        gewuerfelt = true;
        gui.setRollDiceButtonEnabled(false);
	}

	private void init() {

		spielfeld = new Spielfeld(InitSpielfeld.getfelder(), InitSpielfeld.getEreigniskarten(), InitSpielfeld.getEreigniskarten());
		gui.setSpielfeld(spielfeld);
		
		gui.addLogMessage("Login...");
		Connector.getInstance().ensureConnected();
		Random rnd = new Random();
		String name = "no hostname";
		try {
			name = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Spieler spieler1 = new Spieler(name, 5000, new Color(rnd.nextInt()));
		Connector.getInstance().login(spieler1);
		
        spieler1.addObserver(gui.getStatusPanel(0));
		spieler.add(spieler1);
		gui.addSpieler(0, spieler1);
        gui.getStatusPanel(0).update(spieler1, null);
        
        List<Spieler> liste = Connector.getInstance().getSpielerliste();
        System.out.println(liste.size() + " Spieler eingeloggt");
        while (Connector.getInstance().getSpielerliste().size() == liste.size()){
		}
        System.out.println("Spielerliste aktualisiert");
        liste = Connector.getInstance().getSpielerliste();
        for (Spieler s : liste) {
			if(!s.equals(spieler1)){
				System.out.println("Spieler gefunden: " + s.getName());
				s.addObserver(gui.getStatusPanel(1));
				spieler.add(s);
				gui.addSpieler(1, s);
				gui.getStatusPanel(1).update(s, null);
			}
		}
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
                //System.out.println("Sopieler an der Reihe: "+spielerAmZug);
                if (pasch == 3) {//TODO spieler.get(spielerAmZug) ersetzen durh curSpieler am Anfang jeder "Schleife"
                    // geheInsGefaengnis
                    spieler.get(spielerAmZug).setImGefaengnis(true);
                    gui.geheInsGefaengnis(spielerAmZug);
                    pasch = 0;

                } else {

                    // Ziehen
                    gui.rueckeVor(wuerfelZahl);
                    spieler.get(spielerAmZug).addPlatz(wuerfelZahl);

                    // Feld behandeln

                    int actualPlayerPosition = spieler.get(spielerAmZug)
                            .getPlatz();

                    try {
                        spielfeld.getFeld(actualPlayerPosition).handleFieldEffect();

                    } catch (IOException e1) {
                    }

                    // MainPhase

                    // Spieler-wechsel

                    // wenn ein Pasch gewuerfelt wurde
                }
                if(spielfeld.getFeld(getCurSpieler().getPlatz()) instanceof Ereignisfeld || spielfeld.getFeld(getCurSpieler().getPlatz()) instanceof Gemeinschaftsfeld){
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
         Startet das Spiel
         *****************************************************/
        gui.setStartButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.addLogMessage("Spiel gestartet!");
                spielerAmZug = 0;
                gui.setRollDiceButtonEnabled(true);
                gui.setStartButtonEnabled(false);
            }
        });

        /*****************************************************
         Nächster Spieler an der Reihe
         *****************************************************/
        gui.setNextButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.naechsterSpieler();
                spielerAmZug = (spielerAmZug+1)%getSpieleranzahl();
                gui.updateHypothekButtons();
                gui.setRollDiceButtonEnabled(true);
                gui.addLogMessage(getCurSpieler().getName() + " ist jetzt am Zug.");
                if(pasch != 0)
                    gui.setNextButtonEnabled(true);
                else
                    gui.setRollDiceButtonEnabled(true);
            }
        });
        gui.setNextButtonEnabled(false);
		gui.setEreigniskartenButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                getSpielfeld().getEreigniskarten().get(spielfeld.getEreigniskartenPointer()).effect();
                spielfeld.setEreigniskartenPointer((spielfeld.getEreigniskartenPointer()+1)%getSpielfeld().getEreigniskarten().size());
                gui.setEreigniskartenButtonEnabled(false);

                if(pasch != 0)
                    gui.setRollDiceButtonEnabled(true);
                else
                    gui.setNextButtonEnabled(true);

            }
		});
        gui.setEreigniskartenButtonEnabled(false);

		gui.setGemeinschaftskartenButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                getSpielfeld().getGemeinschaftskarten().get(spielfeld.getGemeinschaftskartenPointer()).effect();
                spielfeld.setGemeinschaftskartenPointer((spielfeld.getGemeinschaftskartenPointer()+1)%getSpielfeld().getGemeinschaftskarten().size());
                gui.setGemeinschaftskartenButtonEnabled(false);

                if(pasch != 0)
                    gui.setRollDiceButtonEnabled(true);
                else
                    gui.setNextButtonEnabled(true);
            }
		});
        gui.setGemeinschaftskartenButtonEnabled(false);

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
                    checkAlleFarben();
                }else{
                    if(gui.createPopupChoiceDialog("Nicht genug Geld zum kaufen. Jetzt Hypothek aufnehmen?") == JOptionPane.YES_OPTION);
                        setHypothekenauswahl(true, false);
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

        /*****************************************************
         Hypothek zurückzahlen
         *****************************************************/
        gui.setHypothekButtonActionListener();

        /*****************************************************
         Hypothekenauswahl, falls man nicht bezahlen kann
         *****************************************************/
        gui.setGrundstueckMouseListener();
	}

    public int getSpieleranzahl(){
        return spieler.size();
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
        if(hypothekenauswahl){
            checkIfHypothekVerfuegbar(critical);
        }
    }

    public int calculateToGo(int feldPosition){
        int aktuellePosition = getCurSpieler().getPlatz();
        if(aktuellePosition > feldPosition){
            return((39-aktuellePosition) + feldPosition);
        } else {
            return(feldPosition-aktuellePosition);
        }
    }

    public int getAlleHaeuser(Spieler aktuellerSpieler) {
        int anzahlHauser = 0;
        for(int i = 0; i < 40; i++){
            Strasse strasse = (Strasse)spielfeld.getFeld(i);
            if(strasse != null){
                Spieler besitzer = strasse.getBesitzer();
                if(aktuellerSpieler.equals(aktuellerSpieler)){
                    anzahlHauser += strasse.getMietePointer();
                }
            }
        }
        return anzahlHauser;
    }

    public void checkAlleFarben(){
        Color farbe = null;
        boolean alleFarben = true;
        for(int i=0;i<40;i++){
            if(spielfeld.getFeld(i) instanceof Strasse){
                Strasse st = (Strasse) spielfeld.getFeld(i);
                if(farbe != null){
                    if(!farbe.equals(st.getFarbe())){
                        if(alleFarben){
                            for(int j=0;j>=40;j++){
                                Strasse stTemp = (Strasse) spielfeld.getFeld(i);
                                if(stTemp.getFarbe().equals(farbe))
                                    stTemp.setAlleFarben(true);
                            }
                        }
                        alleFarben = true;
                    }else{
                        if(((Strasse) spielfeld.getFeld(i)).getBesitzer()==null){
                            alleFarben = false;
                        }
                        if(((Strasse) spielfeld.getFeld(i)).getBesitzer()!=null){
                            if(!((Strasse) spielfeld.getFeld(i)).getBesitzer().equals(getCurSpieler())){
                                alleFarben = false;
                            }
                        }
                    }
                }
                farbe = st.getFarbe();
            }
        }
    }

    public void checkIfHypothekVerfuegbar(boolean critical){
        boolean verfuegbar = false;
        String nochVerfuegbar = "Noch verfügbar: ";
        for(int i=0;i<40;i++){
            if(spielfeld.getFeld(i) instanceof Grundstueck){
                Grundstueck gr = (Grundstueck) spielfeld.getFeld(i);
                if(gr.getBesitzer() != null){
                    if(gr.getBesitzer().equals(getCurSpieler())){
                        if(!gr.isBelastet()){
                            verfuegbar = true;
                            nochVerfuegbar += gr.getBezeichnung() + ", ";
                        }
                    }
                }
            }
        }
        if(verfuegbar){
            nochVerfuegbar = nochVerfuegbar.substring(0, nochVerfuegbar.length()-2);
            gui.addLogMessage(nochVerfuegbar);
        }
        else{
            if(critical){
                gui.addLogMessage(getCurSpieler().getName() + " Du hast keine Grundstücke für Hypotheken." );
                gui.addLogMessage(getCurSpieler().getName() + " kann seine Schulden nicht mehr bezahlen." );
                gui.addLogMessage(getCurSpieler().getName() + " scheidet aus dem Spiel aus. (noch zu implementieren)");
            }
            else{
                gui.addLogMessage(getCurSpieler().getName() + " Du hast keine Grundstücke für Hypotheken." );
                setHypothekenauswahl(false, false);
            }

        }
    }
}
