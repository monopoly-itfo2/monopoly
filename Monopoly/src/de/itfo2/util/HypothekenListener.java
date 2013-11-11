package de.itfo2.util;

import de.itfo2.fields.Grundstueck;
import de.itfo2.objects.Spieler;
import de.itfo2.objects.Verwalter;
import de.itfo2.ui.GUIFeld;
import de.itfo2.ui.MonopolyGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HypothekenListener implements ActionListener {

    GUIFeld guifeld;

    public HypothekenListener(GUIFeld feld){
        guifeld = feld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Spieler spieler = Verwalter.getInstance().getCurSpieler();
        MonopolyGUI gui = MonopolyGUI.getInstance();
        Grundstueck gr = (Grundstueck) guifeld.getFeld();
        if(spieler.getKonto() >= gr.getHypothek()+(gr.getHypothek()/10)){
            spieler.addGeld(-gr.getHypothek() + (gr.getHypothek() / 10));
            gui.addLogMessage(spieler.getName() +" bezahlte die Hypothek für " + gr.getBezeichnung());
            gr.setBelastet(false);
            guifeld.getMenuPanel().getbHypothek().setVisible(false);
            guifeld.getMenuPanel().getbHypothek().setEnabled(false);
            guifeld.getLabelHypothek().setVisible(false);
            guifeld.getMenuPanel().repaint();
        }else{
            gui.createPopupDialog("Nicht genug Geld um Hypothek zu bezahlen. (" + gr.getHypothek()/10 + "€)");
        }
    }
}
