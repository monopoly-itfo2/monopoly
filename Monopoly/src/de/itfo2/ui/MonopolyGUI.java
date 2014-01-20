package de.itfo2.ui;

import java.awt.event.ActionListener;

import de.itfo2.fields.Grundstueck;
import de.itfo2.objects.Spieler;
import de.itfo2.objects.Spielfeld;
import de.itfo2.objects.Verwalter;
import de.itfo2.util.DialogCreator;
import de.itfo2.util.GuiFeldMouseListener;
import de.itfo2.util.HypothekenListener;

public class MonopolyGUI implements MonopolyGUIInterface {

    private static MonopolyGUI instance = null;
    GUISpielfeld spielfeld;

    public MonopolyGUI(){

    }

    public void setSpielfeld(Spielfeld spielfeld){
        this.spielfeld = new GUISpielfeld(spielfeld);
    }

    public static MonopolyGUI getInstance(){
        if(instance == null){
            instance = new MonopolyGUI();
        }
        return instance;
    }

    @Override
    public void rueckeVor(int anzahl){
        //spielfeld.setSpielerVisible(curSpieler.getPlatz(), Verwalter.getInstance().getSpielerAmZug(), false);
        //spielfeld.setSpielerVisible(curSpieler.getPlatz()+anzahl, Verwalter.getInstance().getSpielerAmZug(), true);
        setRollDiceButtonEnabled(false);
        setNextButtonEnabled(false);
    	moveFigur(anzahl);
    }

	public void updateBuyVisibility() {
		Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
		disableBuyButton(curSpieler);
        if(spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel()!=null){
            Grundstueck gr = (Grundstueck)spielfeld.getFeld(curSpieler.getPlatz()).getFeld();
            if(gr.getBesitzer()==null){
                spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setVisible(true);
                spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setEnabled(true);
            }
        }
	}

    @Override
    public void rueckeAuf(int platz){
        //spielfeld.setSpielerVisible(curSpieler.getPlatz(), Verwalter.getInstance().getSpielerAmZug(), false);
        //spielfeld.setSpielerVisible(platz, Verwalter.getInstance().getSpielerAmZug(), true);
    	setRollDiceButtonEnabled(false);
    	setNextButtonEnabled(false);
        moveFigur(Verwalter.getInstance().calculateToGo(platz));
    }
    
    public void rueckeZurueck(int platz){
//        Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
        //spielfeld.setSpielerVisible(curSpieler.getPlatz(), Verwalter.getInstance().getSpielerAmZug(), false);
        //spielfeld.setSpielerVisible(platz, Verwalter.getInstance().getSpielerAmZug(), true);
    	setRollDiceButtonEnabled(false);
    	setNextButtonEnabled(false);
        moveFigur(Verwalter.getInstance().calculateToGo(platz)-39);
//        if(spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel()!=null){
//            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setVisible(false);
//            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setEnabled(false);
//        }
//        if(spielfeld.getFeld(platz).getMenuPanel()!=null){
//            Grundstueck gr = (Grundstueck)spielfeld.getFeld(platz).getFeld();
//            if(gr.getBesitzer()==null){
//                spielfeld.getFeld(platz).getMenuPanel().getbBuy().setVisible(true);
//                spielfeld.getFeld(platz).getMenuPanel().getbBuy().setEnabled(true);
//            }
//        }
    }

    public void naechsterSpieler(){
        disableBuyButton(Verwalter.getInstance().getCurSpieler());
        setNextButtonEnabled(false);
    }

	public void disableBuyButton(Spieler curSpieler) {
        if(spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel()!=null){
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setVisible(false);
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setEnabled(false);
        }
	}

    public void updateHypothekButtons(){
        for(int i=0;i<spielfeld.getFelder().size();i++){
            if(spielfeld.getFelder().get(i).getMenuPanel()!=null) {
                Grundstueck gr = (Grundstueck) spielfeld.getFelder().get(i).getFeld();
                if(gr.getBesitzer() != null){
                    if(gr.getBesitzer().equals(Verwalter.getInstance().getCurSpieler()))
                    {
                        if(gr.isBelastet()){
                            spielfeld.getFelder().get(i).getMenuPanel().getbHypothek().setVisible(true);
                            spielfeld.getFelder().get(i).getMenuPanel().getbHypothek().setEnabled(true);
                        }
                        else{
                            spielfeld.getFelder().get(i).getMenuPanel().getbHypothek().setVisible(false);
                            spielfeld.getFelder().get(i).getMenuPanel().getbHypothek().setEnabled(false);
                        }
                    }else{
                        spielfeld.getFelder().get(i).getMenuPanel().getbHypothek().setVisible(false);
                        spielfeld.getFelder().get(i).getMenuPanel().getbHypothek().setEnabled(false);
                    }
                }
                else{
                    spielfeld.getFelder().get(i).getMenuPanel().getbHypothek().setVisible(false);
                    spielfeld.getFelder().get(i).getMenuPanel().getbHypothek().setEnabled(false);
                }
            }
        }
    }

    @Override
    public void geheInsGefaengnis(int spieler) {

    }

    @Override
    public void wuerfeln() {

    }

    @Override
    public void addSpieler(int pos, Spieler spieler){
        spielfeld.addSpieler(pos, spieler);
    }

    public void updateFeld(){
        spielfeld.updateFeld();
    }

    public void setRollDiceButtonActionListener(ActionListener listener){
        spielfeld.getMiddlePanel().getRollDiceButton().addActionListener(listener);
    }
    public void setRollDiceButtonEnabled(boolean enabled){
        spielfeld.getMiddlePanel().getRollDiceButton().setEnabled(enabled);
    }

    public void setStartButtonActionListener(ActionListener listener){
        spielfeld.getMiddlePanel().getStartButton().addActionListener(listener);
    }
    public void setStartButtonEnabled(boolean enabled){
        spielfeld.getMiddlePanel().getStartButton().setEnabled(enabled);
    }

    public void setNextButtonActionListener(ActionListener listener){
        spielfeld.getMiddlePanel().getNextButton().addActionListener(listener);
    }
    public void setNextButtonEnabled(boolean enabled){
        spielfeld.getMiddlePanel().getNextButton().setEnabled(enabled);
    }

    public void setGemeinschaftskartenButtonActionListener(ActionListener listener){
        spielfeld.getMiddlePanel().getGemeinschaftskartenButton().addActionListener(listener);
    }
    public void setGemeinschaftskartenButtonEnabled(boolean enabled){
        spielfeld.getMiddlePanel().getGemeinschaftskartenButton().setEnabled(enabled);
    }

    public void setEreigniskartenButtonActionListener(ActionListener listener){
        spielfeld.getMiddlePanel().getEreigniskartenButton().addActionListener(listener);
    }
    public void setEreigniskartenButtonEnabled(boolean enabled){
        spielfeld.getMiddlePanel().getEreigniskartenButton().setEnabled(enabled);
    }

    public void setBuyButtonActionListener(ActionListener listener){
        for(int i=0;i<spielfeld.getFelder().size();i++){
            if(spielfeld.getFelder().get(i).getMenuPanel()!=null)
                spielfeld.getFelder().get(i).getMenuPanel().getbBuy().addActionListener(listener);
        }
    }

    public void setBuildHouseButtonActionListener(ActionListener listener){
        for(int i=0;i<spielfeld.getFelder().size();i++){
            if(spielfeld.getFelder().get(i).getMenuPanel()!=null)
                spielfeld.getFelder().get(i).getMenuPanel().getbBuild().addActionListener(listener);
        }
    }

    public void setHypothekButtonActionListener(){
        for(int i=0;i<spielfeld.getFelder().size();i++){
            if(spielfeld.getFelder().get(i).getMenuPanel()!=null)
                spielfeld.getFelder().get(i).getMenuPanel().getbHypothek().addActionListener(new HypothekenListener(spielfeld.getFelder().get(i)));
        }
    }

    public void setGrundstueckMouseListener(){
        for(int i=0;i<spielfeld.getFelder().size();i++){
            if(spielfeld.getFelder().get(i).getFeld() instanceof Grundstueck)
                spielfeld.getFelder().get(i).addMouseListener(new GuiFeldMouseListener(spielfeld.getFelder().get(i)));
        }
    }

    public void setGUIFeldHypothek(int pos, boolean visible){
        spielfeld.getFelder().get(pos).getLabelHypothek().setVisible(visible);
    }

    public void kaufeFeld(){
        spielfeld.getFelder().get(Verwalter.getInstance().getCurSpieler().getPlatz()).faerbeFeld(Verwalter.getInstance().getCurSpieler().getColor());
    }

    public void baueHaus(){
        spielfeld.getFelder().get(Verwalter.getInstance().getCurSpieler().getPlatz()).addHouse();
    }

    public StatusPanel getStatusPanel(int pos){
        return spielfeld.getMiddlePanel().getStatuspanel(pos);
    }

    public void createPopupDialog(String text){
        DialogCreator.createOKDialog(text, spielfeld);
    }

    public int createPopupChoiceDialog(String text){
        return DialogCreator.createChoiceDialog("Hypothek aufnehmen?", text, spielfeld);
    }

    public void addLogMessage(String text){
        spielfeld.getMiddlePanel().getChatpanel().addUsermessage("Spiel: ", text);
    }
    
    public void moveFigur(int felderZuGehen){
    	spielfeld.getGlassPane().moveFigur(felderZuGehen);
    }
    
    public void setFigurLabel(int pos, Spieler spieler){
    	spielfeld.getGlassPane().setFigurLabel(pos, spieler);
    }
}