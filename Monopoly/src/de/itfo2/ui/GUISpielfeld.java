package de.itfo2.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.itfo2.fields.Freiparken;
import de.itfo2.objects.Spieler;
import de.itfo2.objects.Spielfeld;

public class GUISpielfeld extends JFrame implements MouseMotionListener{

    /**
	 * 
	 */
	private static final long	serialVersionUID	= 6322876976013534321L;
	private JScrollPane scrollPane;
    private MiddlePanel middlePanel;
    Container pane;
    GridBagConstraints c;
    BufferedImage image;
    JLabel testlabel;
    private boolean mouseIsPressed;
    int lastX, lastY;
    private static final float SCROLLSPEED = 1.2f;
    private Spielfeld spielfeld;
    ArrayList<GUIFeld> felder = new ArrayList<GUIFeld>();

    public GUISpielfeld(Spielfeld spielfeld){
        this.spielfeld = spielfeld;
        scrollPane = new JScrollPane();
        setContentPane(scrollPane);
        scrollPane.setBackground(Color.WHITE);
        pane = new JPanel();
        pane.setBackground(Color.WHITE);
        pane.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        try {
            addPanels();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scrollPane.addMouseMotionListener(this);
        scrollPane.setViewportView(pane);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Monopoly");
    }

    private void addPanels() throws IOException {
        //von vorne nach hinten

        c.gridx = 10;
        c.gridy = 10;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_los.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(0), image));
        pane.add(felder.get(0), c);

        c.gridx = 9;
        c.gridy = 10;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_violett.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(1), image));
        pane.add(felder.get(1), c);

        c.gridx = 8;
        c.gridy = 10;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(2), image));
        pane.add(felder.get(2), c);

        c.gridx = 7;
        c.gridy = 10;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_violett.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(3), image));
        pane.add(felder.get(3), c);

        c.gridx = 6;
        c.gridy = 10;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(4), image));
        pane.add(felder.get(4), c);

        c.gridx = 5;
        c.gridy = 10;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(5), image));
        pane.add(felder.get(5), c);

        c.gridx = 4;
        c.gridy = 10;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_tuerkis.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(6), image));
        pane.add(felder.get(6), c);

        c.gridx = 3;
        c.gridy = 10;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(7), image));
        pane.add(felder.get(7), c);

        c.gridx = 2;
        c.gridy = 10;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_tuerkis.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(8), image));
        pane.add(felder.get(8), c);

        c.gridx = 1;
        c.gridy = 10;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_tuerkis.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(9), image));
        pane.add(felder.get(9), c);

        c.gridx = 0;
        c.gridy = 10;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_gefaengnis.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(10), image));
        pane.add(felder.get(10), c);

        c.gridx = 0;
        c.gridy = 9;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_rot.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(11), image));
        pane.add(felder.get(11), c);

        c.gridx = 0;
        c.gridy = 8;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(12), image));
        pane.add(felder.get(12), c);

        c.gridx = 0;
        c.gridy = 7;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_rot.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(13), image));
        pane.add(felder.get(13), c);

        c.gridx = 0;
        c.gridy = 6;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_rot.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(14), image));
        pane.add(felder.get(14), c);

        c.gridx = 0;
        c.gridy = 5;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(15), image));
        pane.add(felder.get(15), c);

        c.gridx = 0;
        c.gridy = 4;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_braun.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(16), image));
        pane.add(felder.get(16), c);

        c.gridx = 0;
        c.gridy = 3;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(17), image));
        pane.add(felder.get(17), c);

        c.gridx = 0;
        c.gridy = 2;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_braun.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(18), image));
        pane.add(felder.get(18), c);

        c.gridx = 0;
        c.gridy = 1;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_braun.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(19), image));
        pane.add(felder.get(19), c);

        c.gridx = 0;
        c.gridy = 0;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(20), image));
        ((Freiparken)spielfeld.getFeld(20)).addObserver(felder.get(20)); //damit der Pott aktualisiert wird
        pane.add(felder.get(20), c);

        c.gridx = 1;
        c.gridy = 0;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_orange.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(21), image));
        pane.add(felder.get(21), c);

        c.gridx = 2;
        c.gridy = 0;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(22), image));
        pane.add(felder.get(22), c);

        c.gridx = 3;
        c.gridy = 0;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_orange.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(23), image));
        pane.add(felder.get(23), c);

        c.gridx = 4;
        c.gridy = 0;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_orange.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(24), image));
        pane.add(felder.get(24), c);

        c.gridx = 5;
        c.gridy = 0;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(25), image));
        pane.add(felder.get(25), c);

        c.gridx = 6;
        c.gridy = 0;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_gelb.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(26), image));
        pane.add(felder.get(26), c);

        c.gridx = 7;
        c.gridy = 0;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_gelb.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(27), image));
        pane.add(felder.get(27), c);

        c.gridx = 8;
        c.gridy = 0;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(28), image));
        pane.add(felder.get(28), c);

        c.gridx = 9;
        c.gridy = 0;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_gelb.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(29), image));
        pane.add(felder.get(29), c);

        c.gridx = 10;
        c.gridy = 0;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(30), image));
        pane.add(felder.get(30), c);

        c.gridx = 10;
        c.gridy = 1;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_gruen.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(31), image));
        pane.add(felder.get(31), c);

        c.gridx = 10;
        c.gridy = 2;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_gruen.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(32), image));
        pane.add(felder.get(32), c);

        c.gridx = 10;
        c.gridy = 3;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(33), image));
        pane.add(felder.get(33), c);

        c.gridx = 10;
        c.gridy = 4;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_gruen.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(34), image));
        pane.add(felder.get(34), c);

        c.gridx = 10;
        c.gridy = 5;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(35), image));
        pane.add(felder.get(35), c);

        c.gridx = 10;
        c.gridy = 6;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(36), image));
        pane.add(felder.get(36), c);

        c.gridx = 10;
        c.gridy = 7;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_blau.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(37), image));
        pane.add(felder.get(37), c);

        c.gridx = 10;
        c.gridy = 8;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_template.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(38), image));
        pane.add(felder.get(38), c);

        c.gridx = 10;
        c.gridy = 9;
        image = ImageIO.read(this.getClass().getResource("/de/itfo2/ui/resources/feld_blau.jpg"));
        felder.add(new GUIFeld(spielfeld.getFeld(39), image));
        pane.add(felder.get(39), c);

        //mittelpanel einfügen hier

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 9;
        c.gridheight = 9;
        middlePanel = new MiddlePanel();
        pane.add(middlePanel, c);
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public void addSpieler(int pos, Spieler spieler){
        setSpielerVisible(0, pos, true);
    }

    public void setSpielerVisible(int feld, int pos, boolean visible){
    	getFeld(feld).setSpielerVisible(pos, visible);
    }

    public void updateFeld(){
        for(int i=0;i<3;i++)
            middlePanel.getStatuspanel(i).repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        scrollPane.getVerticalScrollBar().setValue((int) (scrollPane.getVerticalScrollBar().getValue()+(lastY-e.getY())*SCROLLSPEED));
        scrollPane.getHorizontalScrollBar().setValue((int) (scrollPane.getHorizontalScrollBar().getValue()+(lastX-e.getX())*SCROLLSPEED));
        lastY = e.getY();
        lastX = e.getX();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lastY = e.getY();
        lastX = e.getX();
    }

    public GUIFeld getFeld(int pos){
        return felder.get(pos%40);
    }

    public MiddlePanel getMiddlePanel() {
        return middlePanel;
    }

    public ArrayList<GUIFeld> getFelder() {
        return felder;
    }

}
