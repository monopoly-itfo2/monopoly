package de.itfo2.objects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import de.itfo2.event.EventBus;
import de.itfo2.fields.*;
import de.itfo2.objects.cards.Karte;
import de.itfo2.ui.MonopolyGUI;
import de.itfo2.ui.SpielfeldOverlay;
import de.itfo2.ui.SpielfigurList;
import de.itfo2.ui.SpielstartFenster;
import de.itfo2.util.GuiFeldMouseListener;

import javax.swing.*;

public class Verwalter {
	private int wuerfelZahl;
	public int pasch = 0;
	public boolean spielAmLaufen = true;
	public ArrayList<Spieler> spielerliste = new ArrayList<Spieler>();
	private static Verwalter instance = null;
	int spielerAmZug = -1;
	Spielfeld spielfeld;
	MonopolyGUI gui = MonopolyGUI.getInstance();
	boolean gewuerfelt;
    boolean hypothekenauswahl = false;

    //	final EventBus bus = EventBus.getInstance();//temporary disabled

	public Verwalter() {
		play();
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
		Würfel wuerfel = new Würfel();
		ersterWert = wuerfel.getWert();
		zweiterWert = wuerfel.getWert();

		if (ersterWert == zweiterWert)
			pasch++;
		else
			pasch = 0;

		wuerfelZahl = ersterWert + zweiterWert;
		//wuerfelZahl = 17;
        gui.addLogMessage(getCurSpieler().getName() + " hat eine " + wuerfelZahl + " gewÃ¼rfelt. (" + ersterWert + " + " + zweiterWert + ")");
        gewuerfelt = true;
        gui.setRollDiceButtonEnabled(false);
	}

	public void play() {

		init();

        initGuiButtonFunctions();
	}

	private void init() {

		spielfeld = new Spielfeld(InitSpielfeld.getfelder(), InitSpielfeld.getEreigniskarten(), InitSpielfeld.getGemeischaftskarten());
		gui.setSpielfeld(spielfeld);

		// Spieler spieler1 = new Spieler("Spieler 1", 10000,
		// Color.getHSBColor(269f, 35f, 96f));

        /*//Spieler 1
		Spieler spieler1 = new Spieler("Spieler 1", 500000, Color.getHSBColor(
				0.9f, 0.1f, 0.7f), SpielfigurList.getInstance().getSpielfigurByName("kokowei"));
		gui.setFigurLabel(0, spieler1);

        spieler1.addObserver(gui.getStatusPanel(0));
		spielerliste.add(spieler1);
		gui.addSpieler(0, spieler1);
        gui.getStatusPanel(0).update(spieler1, null);

        //Spieler 2
        Spieler spieler2 = new Spieler("Spieler 2", 500000, Color.getHSBColor(
                0.3f, 0.1f, 0.9f), SpielfigurList.getInstance().getSpielfigurByName("arkani"));
        gui.setFigurLabel(1, spieler2);
        spieler2.addObserver(gui.getStatusPanel(1));
		spielerliste.add(spieler2);
		gui.addSpieler(1, spieler2);
        gui.getStatusPanel(1).update(spieler2, null);
		*/
	}
	
	public void addSpieler(ArrayList<Spieler> spielerdaten){
		int i = 0;
		Color[]colors = {Color.getHSBColor(0.9f, 0.1f, 0.7f), Color.getHSBColor(0.3f, 0.1f, 0.9f), Color.getHSBColor(0.5f, 0.5f, 0.5f), Color.getHSBColor(0.3f, 0.9f, 0.1f)}; 
		for(Spieler spieler : spielerdaten){
			Spieler tempSpieler = spieler;
			gui.setFigurLabel(i, tempSpieler);
			tempSpieler.setColor(colors[i]);
			tempSpieler.addObserver(gui.getStatusPanel(i));
		    spielerliste.add(tempSpieler);
		    gui.addSpieler(i,  tempSpieler);
		    gui.getStatusPanel(i).update(tempSpieler, null);
			i++;
		}
	}

	public Spieler getCurSpieler() {
		return spielerliste.get(spielerAmZug);
	}
    public Spieler getNextSpieler() {
        return spielerliste.get((spielerAmZug+1)%getSpieleranzahl());
    }

	public int getSpielerAmZug() {
		return spielerAmZug;
	}

	public void initGuiButtonFunctions() {

        /*****************************************************
        Regelt, was beim wÃ¼rfeln passiert
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
                    spielerliste.get(spielerAmZug).setImGefaengnis(true);
                    gui.rueckeAuf(10);
                    getCurSpieler().setPlatz(10);
                    getCurSpieler().setImGefaengnis(true);
                    pasch = 0;

                } else {

                    // Ziehen
                    gui.rueckeVor(wuerfelZahl);
                    spielerliste.get(spielerAmZug).addPlatz(wuerfelZahl);
                    //spieler.get(spielerAmZug).setPlatz(7);
//                    spieler.get(spielerAmZug).setPlatz(wuerfelZahl);

                    // Feld behandeln

                    int actualPlayerPosition = spielerliste.get(spielerAmZug)
                            .getPlatz();

//                    try {
//                        spielfeld.getFeld(actualPlayerPosition).handleFieldEffect();
//
//                    } catch (IOException e1) {
//                    }

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
            	SpielstartFenster sf = new SpielstartFenster();
                gui.addLogMessage("Spiel gestartet!");
                spielerAmZug = 0;
                gui.setRollDiceButtonEnabled(true);
                gui.setStartButtonEnabled(false);
            }
        });

        /*****************************************************
         NÃ¤chster Spieler an der Reihe
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
				//An dieser Stelle näöhste Woche weitermachen, wir wollen die Ereigniskarten in Reinfolge bringen ( für ALLE SPieler) und die Gefängisfreikarten entfernen.
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
                spielfeld.getGemeinschaftskarten().get(spielfeld.getGemeinschaftskartenPointer()).effect();
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
                    ((JButton)e.getSource()).setVisible(false);
                    ((JButton)e.getSource()).setEnabled(false);
                }else{
                    if(gui.createPopupChoiceDialog("Nicht genug Geld zum kaufen. Jetzt Hypothek aufnehmen?") == JOptionPane.YES_OPTION);
                        setHypothekenauswahl(true, false);
                }
			}
		});

        /*****************************************************
         Haus auf aktueller StraÃŸe kaufen
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
         Hypothek zurÃ¼ckzahlen
         *****************************************************/
        gui.setHypothekButtonActionListener();

        /*****************************************************
         Hypothekenauswahl, falls man nicht bezahlen kann
         *****************************************************/
        gui.setGrundstueckMouseListener();
	}

    public int getSpieleranzahl(){
        return spielerliste.size();
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

    public int[] getAlleHaeuser(Spieler aktuellerSpieler) {
        int[] gebäude = new int[2];//0 sind häuser, 1 sind hotels
        gebäude[0] = 0;
        gebäude[1] = 0;
        for(int i = 0; i < 40; i++){
        	if(spielfeld.getFeld(i) instanceof Strasse)
        	{
        		Strasse strasse = (Strasse)spielfeld.getFeld(i);
                if(strasse != null){
                    if(aktuellerSpieler.equals(aktuellerSpieler)){
                    	if(strasse.getMietePointer()<5){
                    		gebäude[0] += strasse.getMietePointer();
                    	}else{
                    		gebäude[1]++;
                    	}
                    }
                }
        	}  
        }
        return gebäude;
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
        String nochVerfuegbar = "Noch verfÃ¼gbar: ";
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
                gui.addLogMessage(getCurSpieler().getName() + " Du hast keine GrundstÃ¼cke fÃ¼r Hypotheken." );
                gui.addLogMessage(getCurSpieler().getName() + " kann seine Schulden nicht mehr bezahlen." );
                gui.addLogMessage(getCurSpieler().getName() + " scheidet aus dem Spiel aus. (noch zu implementieren)");
            }
            else{
                gui.addLogMessage(getCurSpieler().getName() + " Du hast keine GrundstÃ¼cke fÃ¼r Hypotheken." );
                setHypothekenauswahl(false, false);
            }

        }
    }
    
    public void disableGefängnisFrei(int typ) {

    	
//    	System.out.println("Typ "+typ);
//    	spielfeld.ereigniskarten.remove(0);
    	// Löscht erst beim zweiten mal
    	spielfeld.printAllEreigniskarten();
    	
    	
    	switch(typ) {//Das Problem ist, dass die Variablen aus dem Falschem Array gelöscht werden
	    	case 0:
//	    			if(spielfeld.ereigniskarten.isEmpty() == false){
	    				spielfeld.removeEreigniskarte(spielfeld.getEreigniskartenPointer());
//	    			}
	    			System.out.println(spielfeld.getEreigniskartenPointer());
	    			break;
	    	case 1: spielfeld.gemeinschaftskarten.remove(spielfeld.getGemeinschaftskartenPointer());
	    			break;
	    	default:System.out.println("nur eins oder zwei, du depp!");
    	}
    	
    	
    	
    }
    public void testGefängnisFrei ()
	{
    	for(Karte karte:spielfeld.ereigniskarten){
    		System.out.println("###############");
    		System.out.println(karte.getText());
    	}
    	
	}
}
