package de.itfo2.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import de.itfo2.objects.Feld;
import de.itfo2.objects.Freiparken;
import de.itfo2.objects.Grundstueck;
import de.itfo2.objects.Strasse;

public class GUIFeld extends PicturePanel implements MouseListener {

    JLabel labelName;
    JLabel labelPreis;
    MenuPanel menuPanel;
    int gebaut = 0;
    JLabel [] labelHaus = new JLabel[5];
    JLabel [] figuren = new JLabel[4];
    Feld feld;

    public GUIFeld(Feld feld, BufferedImage image) throws IOException {
        addMouseListener(this);
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

        icon = new ImageIcon(img);
        labelHaus[4] = new JLabel(icon);
        labelHaus[4].setBounds(3, 3, 18, 17);
        this.add(labelHaus[4], 2);
        labelHaus[4].setVisible(false);

        figuren[0] = new JLabel();
        BufferedImage image = null;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/figur.jpg"));
        ImageIcon spielfigur = new ImageIcon(image);
        figuren[0].setIcon(spielfigur);
        figuren[0].setBounds(3, 40, 18, 17);
        this.add(figuren[0], 2);
        figuren[0].setVisible(false);

        figuren[1] = new JLabel();
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/figur.jpg"));
        spielfigur = new ImageIcon(image);
        figuren[1].setIcon(spielfigur);
        figuren[1].setBounds(24, 40, 18, 17);
        this.add(figuren[1], 2);
        figuren[1].setVisible(false);

        figuren[2] = new JLabel();
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/figur.jpg"));
        spielfigur = new ImageIcon(image);
        figuren[2].setIcon(spielfigur);
        figuren[2].setBounds(45, 40, 18, 17);
        this.add(figuren[2], 2);
        figuren[2].setVisible(false);

        figuren[3] = new JLabel();
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/figur.jpg"));
        spielfigur = new ImageIcon(image);
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
            menuPanel.setVisible(false);
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
            gebaut++;
        }
        if(gebaut<=3){
            labelHaus[gebaut].setVisible(true);
            gebaut++;
        }
    }

    public void setSpielerVisible(int pos, boolean visible){
        figuren[pos].setVisible(visible);
    }

    public Feld getFeld(){
        return this.feld;
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
        if(menuPanel!=null)
            menuPanel.setVisible(true);
        //this.setBackgroundImage(ColorChanger.changeColor((BufferedImage) getImage(), Color.WHITE, Color.YELLOW));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(menuPanel!=null)
            menuPanel.setVisible(false);
        //this.setBackgroundImage(ColorChanger.changeColor((BufferedImage)getImage(), Color.YELLOW, Color.WHITE));
    }
}
