package de.itfo2.util;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import de.itfo2.fields.Grundstueck;
import de.itfo2.objects.Verwalter;
import de.itfo2.ui.GUIFeld;
import de.itfo2.ui.MonopolyGUI;

public class GuiFeldMouseListener implements MouseListener {

    GUIFeld feld;
    public GuiFeldMouseListener(GUIFeld feld){
        this.feld = feld;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(Verwalter.getInstance().isHypothekenauswahl()){
            if(feld.getFeld() instanceof Grundstueck){
                if(((Grundstueck) feld.getFeld()).getBesitzer().equals(Verwalter.getInstance().getCurSpieler())){
                    if(!((Grundstueck)feld.getFeld()).isBelastet()){
                        ((Grundstueck)feld.getFeld()).setBelastet(true);
                        feld.getLabelHypothek().setVisible(true);
                        MonopolyGUI.getInstance().addLogMessage(feld.getFeld().getBezeichnung() + " wurde als Hypothek verwendet.");
                        Verwalter.getInstance().getCurSpieler().addGeld(((Grundstueck) feld.getFeld()).getHypothek());
                        Verwalter.getInstance().setHypothekenauswahl(false, false);
                        try {
                            if(((Grundstueck)(Verwalter.getInstance().getSpielfeld().getFeld(Verwalter.getInstance().getCurSpieler().getPlatz()))).getBesitzer() != null){
                                //Miete zahlen
                                if(((Grundstueck)(Verwalter.getInstance().getSpielfeld().getFeld(Verwalter.getInstance().getCurSpieler().getPlatz()))).getBesitzer().equals(Verwalter.getInstance().getCurSpieler())){
                                    Verwalter.getInstance().getSpielfeld().getFeld(Verwalter.getInstance().getCurSpieler().getPlatz()).handleFieldEffect();
                                    MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getSpielfeld().getFeld(Verwalter.getInstance().getCurSpieler().getPlatz()).getBezeichnung() + " als Hyptothek gewaehlt (Wert: " + ((Grundstueck)(Verwalter.getInstance().getSpielfeld().getFeld(Verwalter.getInstance().getCurSpieler().getPlatz()))).getHypothek() + ")");
                                }
                                //Steuern
                                else if(!(Verwalter.getInstance().getSpielfeld().getFeld(Verwalter.getInstance().getCurSpieler().getPlatz()) instanceof Grundstueck)){
                                    //egal
                                }
                                //kaufen
                                else{
                                    //egal
                                }
                            }

                        } catch (IOException e1) {
                            System.out.println(e1.getStackTrace());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
