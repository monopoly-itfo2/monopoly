package de.itfo2.ui;

import de.itfo2.objects.InitSpielfeld;
import de.itfo2.objects.Spieler;
import de.itfo2.objects.Spielfeld;
import de.itfo2.objects.Verwalter;
import de.itfo2.util.DialogCreator;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MonopolyGUI implements MonopolyGUIInterface {

    private static MonopolyGUI instance = null;
    GUISpielfeld spielfeld;

    public MonopolyGUI() throws IOException {

    }

    public void setSpielfeld(Spielfeld spielfeld) throws IOException {
        this.spielfeld = new GUISpielfeld(spielfeld);
    }

    public static MonopolyGUI getInstance() throws IOException {
        if(instance == null){
            instance = new MonopolyGUI();
        }
        return instance;
    }

    @Override
    public void rueckeVor(int anzahl) throws IOException {
        Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
        spielfeld.setSpielerVisible(curSpieler.getPlatz(), Verwalter.getInstance().getSpielerAmZug(), false);
        spielfeld.setSpielerVisible(curSpieler.getPlatz()+anzahl, Verwalter.getInstance().getSpielerAmZug(), true);
        if(spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel()!=null){
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().setVisible(false);
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().setEnabled(false);
        }
        if(spielfeld.getFeld(curSpieler.getPlatz()+anzahl).getMenuPanel()!=null){
            spielfeld.getFeld(curSpieler.getPlatz()+anzahl).getMenuPanel().setVisible(true);
            spielfeld.getFeld(curSpieler.getPlatz()+anzahl).getMenuPanel().setEnabled(true);
        }
    }

    @Override
    public void rueckeAuf(int platz) throws IOException {
        Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
        spielfeld.setSpielerVisible(curSpieler.getPlatz(), Verwalter.getInstance().getSpielerAmZug(), false);
        spielfeld.setSpielerVisible(platz, Verwalter.getInstance().getSpielerAmZug(), true);
        if(spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel()!=null){
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().setVisible(false);
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().setEnabled(false);
        }
        if(spielfeld.getFeld(platz).getMenuPanel()!=null){
            spielfeld.getFeld(platz).getMenuPanel().setVisible(true);
            spielfeld.getFeld(platz).getMenuPanel().setEnabled(true);
        }
    }

    public void naechsterSpieler() throws IOException {
        Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
        if(spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel()!=null){
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().setVisible(false);
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().setEnabled(false);
        }
        setNextButtonEnabled(false);
    }

    @Override
    public void geheInsGefaengnis(int spieler) {

    }

    @Override
    public void wuerfeln() {

    }

    @Override
    public void addSpieler(int pos, Spieler spieler) throws IOException {
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

    public void setEreigniskartenButtonActionListener(ActionListener listener){
        spielfeld.getMiddlePanel().getEreigniskartenButton().addActionListener(listener);
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

    public void kaufeFeld(){
        try {
            spielfeld.getFelder().get(Verwalter.getInstance().getCurSpieler().getPlatz()).faerbeFeld(Verwalter.getInstance().getCurSpieler().getColor());
        } catch (IOException e) {
        }
    }

    public void baueHaus(){
        try {
            spielfeld.getFelder().get(Verwalter.getInstance().getCurSpieler().getPlatz()).addHouse();
        } catch (IOException e) {
        }
    }

    public StatusPanel getStatusPanel(int pos){
        return spielfeld.getMiddlePanel().getStatuspanel(pos);
    }

    public void createPopupDialog(String text){
        DialogCreator.createOKDialog(text, spielfeld);
    }

    public void addLogMessage(String text){
        spielfeld.getMiddlePanel().getChatpanel().addUsermessage("Spiel: ", text);
    }
}
