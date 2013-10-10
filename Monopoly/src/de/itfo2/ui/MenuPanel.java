package de.itfo2.ui;

import de.itfo2.fields.Grundstueck;
import de.itfo2.objects.Spieler;
import de.itfo2.objects.Verwalter;
import de.itfo2.util.DialogCreator;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuPanel extends JPanel implements MouseListener{

    private JButton bBuy, bBuild;
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
        bBuy = new JButton(icon){
            @Override
            public boolean isVisible(){
                Grundstueck gr = (Grundstueck) parent.getFeld();
                return gr.getBesitzer()==null ? true : false;
            }
        };
        bBuy.setSize(icon.getIconWidth(), icon.getIconHeight());
        bBuy.setMargin(new java.awt.Insets(0, 0, 0, 0));
        bBuy.setOpaque(false);
        bBuy.addMouseListener(this);
        bBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                        Grundstueck gr = (Grundstueck) parent.getFeld();
                        Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
                        if(gr != null)
                        {
                            if(curSpieler.getKonto()>=gr.getPreis() && gr.getBesitzer() == null)
                            {
                                curSpieler.addMoney(-gr.getPreis());
                                gr.setBesitzer(curSpieler);
                                parent.setBackgroundImage(ColorChanger.changeColor((BufferedImage) parent.getImage(), Color.WHITE, curSpieler.getColor()));
                            }
                            else{
                                //DialogCreator.createOKDialog("Geh arbeiten Junge!", parent);
                            }
                        }
                    } catch (IOException exception) {
                }
            }
        });
        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/geldschein_hover.jpg"));
        icon = new ImageIcon(img);
        bBuy.setPressedIcon(icon);
        add(bBuy);

        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/hausTransparent.jpg"));
        icon = new ImageIcon(img);
        bBuild = new JButton(icon);
        bBuild.setSize(icon.getIconWidth(), icon.getIconHeight());
        bBuild.setMargin(new java.awt.Insets(0, 0, 0, 0));
        bBuild.addMouseListener(this);
        bBuild.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                parent.addHouse();
            }
        });
        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/haus_hover.jpg"));
        icon = new ImageIcon(img);
        bBuild.setPressedIcon(icon);

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
        this.setVisible(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setVisible(false);
    }

    public JButton getbBuy() {
        return bBuy;
    }

    public JButton getbBuild() {
        return bBuild;
    }
}
