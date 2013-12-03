package de.itfo2.ui;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import de.itfo2.objects.Spieler;

public class Spielfigur extends JLabel {
    /**
	 * 
	 */
	private static final long	serialVersionUID	= -6587021463210242922L;
	Spieler spieler;
    public Spielfigur(Spieler spieler){
        BufferedImage image = null;
        this.spieler = spieler;
        try {
            image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/figur.jpg"));
        } catch (IOException e) {}
        ImageIcon spielfigur = new ImageIcon(image);
        setIcon(spielfigur);
        setBounds(20, 20, 20, 20);
    }
}
