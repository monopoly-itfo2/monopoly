package de.itfo2.ui;

import de.itfo2.objects.Spieler;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class StatusPanel extends PicturePanel implements Observer{

    JLabel lab_name, lab_geld;
    BufferedImage img;

    public StatusPanel() throws IOException {
        setPreferredSize(new Dimension(150, 200));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/statuspanel_test.png"));
        try {
            BufferedImage img = ImageIO.read(getClass().getResource("/de/itfo2/ui/resources/statuspanel_test.png"));
            setBackgroundImage(img);
        } catch (IOException e) {
            System.out.println("Tooot!");
        }

        lab_name = new JLabel("");
        lab_name.setBounds(0, 0, 200, 30);
        lab_name.setFont(new Font("Calibri", 0, 15));
        lab_name.setHorizontalAlignment(JLabel.CENTER);
        add(lab_name);

        lab_geld = new JLabel("");
        lab_geld.setBounds(0, 0, 200, 30);
        lab_geld.setFont(new Font("Calibri", 0, 15));
        lab_geld.setHorizontalAlignment(JLabel.CENTER);
        add(lab_geld);
    }

    //mit änderungslistener realisieren, sonst stackoverflow!
    @Override
    public void repaint(){
        /*
        if(this.spieler != null){
            lab_name.setText(spieler.getName());
            lab_geld.setText(""+spieler.getKonto());
            this.setBackground(spieler.getColor());
            if(img != null)
                this.setBackgroundImage(img);
        }
        else
        {

        }
        */
    }


    @Override
    public void update(Observable o, Object arg) {
        Spieler spieler = (Spieler)o;
        //lab_name.setForeground(spieler.getColor());
        lab_name.setText(spieler.getName());
        lab_geld.setText(""+spieler.getKonto()+"€");
    }
}
