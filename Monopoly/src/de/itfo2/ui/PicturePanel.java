package de.itfo2.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLayeredPane;

public class PicturePanel extends JLayeredPane {
    /**
	 * 
	 */
	private static final long	serialVersionUID	= -6688826506122252036L;
	private Image image;

    public PicturePanel() {
        super();
    }

    public void setBackgroundImage(Image image) {
        this.image = image;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }

    public Image getImage(){
        return this.image;
    }
}