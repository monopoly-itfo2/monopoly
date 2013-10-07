package de.itfo2.ui;


import de.itfo2.objects.Spieler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spielfigur extends JLabel {
    Spieler spieler;
    public Spielfigur(Spieler spieler){
        BufferedImage image = null;
        this.spieler = spieler;
        try {
            image = ImageIO.read(this.getClass().getResource("/GUI/bilder/figur.jpg"));
        } catch (IOException e) {}
        ImageIcon spielfigur = new ImageIcon(image);
        setIcon(spielfigur);
        setBounds(20, 20, 20, 20);
    }
}
