package de.itfo2.ui;

import de.itfo2.fields.Grundstueck;
import de.itfo2.objects.InitSpielfeld;
import de.itfo2.objects.Spieler;
import de.itfo2.objects.Spielfeld;
import de.itfo2.objects.Verwalter;
import de.itfo2.util.DialogCreator;
import de.itfo2.util.GuiFeldMouseListener;
import de.itfo2.util.HypothekenListener;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.LinkedList;

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
        Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
        spielfeld.setSpielerVisible(curSpieler.getPlatz(), Verwalter.getInstance().getSpielerAmZug(), false);
        spielfeld.setSpielerVisible(curSpieler.getPlatz()+anzahl, Verwalter.getInstance().getSpielerAmZug(), true);
        if(spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel()!=null){
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setVisible(false);
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setEnabled(false);
        }
        if(spielfeld.getFeld(curSpieler.getPlatz()+anzahl).getMenuPanel()!=null){
            Grundstueck gr = (Grundstueck)spielfeld.getFeld(curSpieler.getPlatz()+anzahl).getFeld();
            if(gr.getBesitzer()==null){
                spielfeld.getFeld(curSpieler.getPlatz()+anzahl).getMenuPanel().getbBuy().setVisible(true);
                spielfeld.getFeld(curSpieler.getPlatz()+anzahl).getMenuPanel().getbBuy().setEnabled(true);
            }
        }
    }

    @Override
    public void rueckeAuf(int platz){
        Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
        spielfeld.setSpielerVisible(curSpieler.getPlatz(), Verwalter.getInstance().getSpielerAmZug(), false);
        spielfeld.setSpielerVisible(platz, Verwalter.getInstance().getSpielerAmZug(), true);
        if(spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel()!=null){
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setVisible(false);
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setEnabled(false);
        }
        if(spielfeld.getFeld(platz).getMenuPanel()!=null){
            Grundstueck gr = (Grundstueck)spielfeld.getFeld(platz).getFeld();
            if(gr.getBesitzer()==null){
                spielfeld.getFeld(platz).getMenuPanel().getbBuy().setVisible(true);
                spielfeld.getFeld(platz).getMenuPanel().getbBuy().setEnabled(true);
            }
        }
    }

    public void naechsterSpieler(){
        Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
        if(spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel()!=null){
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setVisible(false);
            spielfeld.getFeld(curSpieler.getPlatz()).getMenuPanel().getbBuy().setEnabled(false);
        }
        setNextButtonEnabled(false);
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
    
    public void setLoginButtonActionListener(ActionListener listener){
        spielfeld.getMiddlePanel().getLoginButton().addActionListener(listener);
    }
    public void setLoginButtonEnabled(boolean enabled){
        spielfeld.getMiddlePanel().getLoginButton().setEnabled(enabled);
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
        return DialogCreator.createChoiceDialog(text, spielfeld);
    }

    public void addLogMessage(String text){
        spielfeld.getMiddlePanel().getChatpanel().addUsermessage("Spiel: ", text);
    }
    
    public void createLoginDialog(){
    	JDialog dialog = new JDialog(spielfeld, "Login");
    	dialog.setSize(new Dimension(300, 200));
    	JTextField fieldName = new JTextField();
    	fieldName.setSize(200, 30);
    	
    	JComboBox cbColor = new JComboBox();  
    	cbColor.setSize(200, 30);
    	cbColor.addItem(Color.GREEN);
    	cbColor.addItem(Color.RED);
    	
    	JButton buttonLogin = new JButton("Login");
    	buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//hier die Felder auswerten und was machen!
				
			}
		});
    	
    	dialog.setLayout(new BorderLayout());
    	dialog.add(fieldName, BorderLayout.NORTH);
    	dialog.add(cbColor, BorderLayout.EAST);
    	dialog.add(buttonLogin, BorderLayout.SOUTH);
    	dialog.pack();
    	dialog.setVisible(true);
    }
}
