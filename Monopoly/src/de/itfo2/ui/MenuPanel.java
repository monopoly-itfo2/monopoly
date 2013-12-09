package de.itfo2.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.itfo2.fields.Grundstueck;
import de.itfo2.fields.Strasse;
import de.itfo2.objects.Spieler;
import de.itfo2.objects.Verwalter;
import de.itfo2.util.DialogCreator;

public class MenuPanel extends JPanel implements MouseListener{

    private JButton bBuy, bBuild, bHypothek;
    private GUIFeld parent;
    private boolean haus;
    public MenuPanel(GUIFeld feld, boolean haus) throws IOException {
        parent = feld;
        this.haus = haus;

        this.setOpaque(false);
        this.addMouseListener(this);
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(90, 30));

        BufferedImage img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/geldscheinTransparent.jpg"));
        ImageIcon icon = new ImageIcon(img);
        bBuy = new JButton(icon);
        bBuy.setVisible(false);
        bBuy.setEnabled(false);
        bBuy.setSize(icon.getIconWidth(), icon.getIconHeight());
        bBuy.setMargin(new java.awt.Insets(0, 0, 0, 0));
        bBuy.setOpaque(false);
        bBuy.addMouseListener(this);

        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/geldschein_hover.jpg"));
        icon = new ImageIcon(img);
        bBuy.setPressedIcon(icon);
        add(bBuy);

        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/hausTransparent.jpg"));
        icon = new ImageIcon(img);
        bBuild = new JButton(icon){
            @Override
            public boolean isVisible(){
                if(parent.getFeld() instanceof Strasse){
                    Strasse st = (Strasse) parent.getFeld();
                    if(st.isAlleFarben() && st.getMietePointer()!=6)
                        return true;
                    else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
            @Override
            public boolean isEnabled(){
                if(parent.getFeld() instanceof Strasse){
                    Strasse st = (Strasse) parent.getFeld();
                    if(st.isAlleFarben() && st.getMietePointer()!=6)
                        return true;
                    else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        };
        bBuild.setSize(icon.getIconWidth(), icon.getIconHeight());
        bBuild.setMargin(new java.awt.Insets(0, 0, 0, 0));
        bBuild.addMouseListener(this);
        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/haus_hover.jpg"));
        icon = new ImageIcon(img);
        bBuild.setPressedIcon(icon);
        bBuild.setEnabled(false);
        bBuild.setVisible(false);

        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/hypothekButton.png"));
        icon = new ImageIcon(img);
        bHypothek = new JButton(icon);
        bHypothek.setSize(icon.getIconWidth(), icon.getIconHeight());
        bHypothek.setMargin(new java.awt.Insets(0, 0, 0, 0));
        bHypothek.addMouseListener(this);
        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/hypothekButton_hover.png"));
        icon = new ImageIcon(img);
        bHypothek.setPressedIcon(icon);
        bHypothek.setEnabled(false);
        bHypothek.setVisible(false);
        add(bHypothek);

        if(haus)
            add(bBuild);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //this.setVisible(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //this.setVisible(false);
    }

    public JButton getbBuy() {
        return bBuy;
    }

    public JButton getbBuild() {
        return bBuild;
    }

    public JButton getbHypothek() {
        return bHypothek;
    }
}
