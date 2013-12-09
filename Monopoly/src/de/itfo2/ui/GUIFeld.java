package de.itfo2.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import de.itfo2.fields.Feld;
import de.itfo2.fields.Freiparken;
import de.itfo2.fields.Grundstueck;
import de.itfo2.fields.Strasse;
import de.itfo2.objects.Spieler;
import de.itfo2.objects.Verwalter;

public class GUIFeld extends PicturePanel implements Observer {

    private JLabel labelName;
    private JLabel labelPreis;
    private JLabel labelHypothek;
    private MenuPanel menuPanel;
    private int gebaut = 0;
    private JLabel [] labelHaus = new JLabel[5];
    private JLabel [] figuren = new JLabel[4];
    private Feld feld;

    public GUIFeld(Feld feld, BufferedImage image) throws IOException {
        setBackgroundImage(image);
        this.setLayout(null);
        this.feld = feld;
        //setSize(image.getWidth(this), image.getHeight(this));
        init();
        setPreferredSize(new Dimension(90, 90));
    }



    public void init() throws IOException {

        BufferedImage img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/hausTransparent.jpg"));
        ImageIcon icon = new ImageIcon(img);
        labelHaus[0] = new JLabel(icon);
        labelHaus[0].setBounds(3, 3, 18, 17);
        this.add(labelHaus[0], 2);
        labelHaus[0].setVisible(false);

        labelHaus[1] = new JLabel(icon);
        labelHaus[1].setBounds(24, 3, 18, 17);
        this.add(labelHaus[1], 2);
        labelHaus[1].setVisible(false);

        labelHaus[2] = new JLabel(icon);
        labelHaus[2].setBounds(45, 3, 18, 17);
        this.add(labelHaus[2], 2);
        labelHaus[2].setVisible(false);

        labelHaus[3] = new JLabel(icon);
        labelHaus[3].setBounds(66, 3, 18, 17);
        this.add(labelHaus[3], 2);
        labelHaus[3].setVisible(false);

        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/hotelTransparent.jpg"));
        icon = new ImageIcon(img);
        labelHaus[4] = new JLabel(icon);
        labelHaus[4].setBounds(3, 3, 18, 17);
        this.add(labelHaus[4], 2);
        labelHaus[4].setVisible(false);

        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/hypothek.png"));
        labelHypothek = new JLabel(new ImageIcon(img));
        labelHypothek.setBounds(25, 37, 40, 50);
        this.add(labelHypothek, 1);
        labelHypothek.setVisible(false);

        
        BufferedImage image = null;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/figur_template.png"));
        icon = new ImageIcon(getClass().getResource("/de/itfo2/ui/resources/kokowei.gif"));
        ImageIcon spielfigur = new ImageIcon(ColorChanger.changeColor(image, Color.WHITE, Color.getHSBColor(0.9f, 0.1f, 0.7f)));
        figuren[0] = new JLabel(icon);
        figuren[0].setBounds(3, 40, 18, 17);
        this.add(figuren[0], 2);
        figuren[0].setVisible(false);

        icon = new ImageIcon(getClass().getResource("/de/itfo2/ui/resources/arkani_small.gif"));
        figuren[1] = new JLabel(icon);
        spielfigur = new ImageIcon(ColorChanger.changeColor(image, Color.WHITE, Color.getHSBColor(0.9f, 0.1f, 0.7f)));
        //figuren[1].setIcon(new ImageIcon(image));
        figuren[1].setBounds(24, 40, 18, 17);
        this.add(figuren[1], 2);
        figuren[1].setVisible(false);

        figuren[2] = new JLabel();
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/figur_template.png"));
        spielfigur = new ImageIcon(ColorChanger.changeColor(image, Color.WHITE, Color.RED));
        figuren[2].setIcon(spielfigur);
        figuren[2].setBounds(45, 40, 18, 17);
        this.add(figuren[2], 2);
        figuren[2].setVisible(false);

        figuren[3] = new JLabel();
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/figur_template.png"));
        spielfigur = new ImageIcon(ColorChanger.changeColor(image, Color.WHITE, Color.getHSBColor(0.9f, 0.1f, 0.7f)));
        figuren[3].setIcon(spielfigur);
        figuren[3].setBounds(66, 40, 18, 17);
        this.add(figuren[3], 2);
        figuren[3].setVisible(false);

        labelName = new JLabel(feld.getBezeichnung());
        labelName.setBounds(0, 20, 90, 10);
        labelName.setOpaque(false);
        labelName.setFont(new Font("Calibri", 0, 10));
        labelName.setHorizontalAlignment(JLabel.CENTER);
        this.add(labelName, 1);

        if(feld instanceof Grundstueck)
        {
            labelPreis = new JLabel(((Grundstueck) feld).getPreis() + "€");
            labelPreis.setBounds(0, 30, 90, 10);
            labelPreis.setOpaque(false);
            labelPreis.setFont(new Font("Calibri", 0, 10));
            labelPreis.setHorizontalAlignment(JLabel.CENTER);
            this.add(labelPreis, 1);

            boolean isStrasse = false;
            if(feld instanceof Strasse)
                isStrasse = true;
            menuPanel = new MenuPanel(this, isStrasse);
            //menuPanel.setVisible(false);
            menuPanel.setBounds(0, 60, 90, 30);
            this.add(menuPanel, 2);
        }
        else if(feld instanceof Freiparken){
            labelPreis = new JLabel(((Freiparken) feld).getSumme() + "€ im Pott");
            labelPreis.setBounds(0, 30, 90, 10);
            labelPreis.setOpaque(false);
            labelPreis.setFont(new Font("Calibri", 0, 10));
            labelPreis.setHorizontalAlignment(JLabel.CENTER);
            this.add(labelPreis, 1);
        }
    }

    public void addHouse()
    {
        if(gebaut==4){
            for(int i=0;i<=4;i++)
                labelHaus[i].setVisible(!labelHaus[i].isVisible());
            menuPanel.getbBuild().setEnabled(false);
            menuPanel.getbBuild().setVisible(false);
            labelHaus[gebaut].setVisible(true);
            gebaut++;
        }
        if(gebaut<=3){
            labelHaus[gebaut].setVisible(true);
            gebaut++;
        }
    }

    public JLabel getLabelHypothek() {
        return labelHypothek;
    }

    public void setSpielerVisible(int pos, boolean visible){
        figuren[pos].setVisible(visible);
    }

    public Feld getFeld(){
        return this.feld;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public void faerbeFeld(Color color){
        this.setBackgroundImage(ColorChanger.changeColor((BufferedImage) getImage(), Color.WHITE, color));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(feld instanceof Freiparken){
            labelPreis.setText(((Freiparken) feld).getSumme() + "€ im Pott");
        }
    }
}
