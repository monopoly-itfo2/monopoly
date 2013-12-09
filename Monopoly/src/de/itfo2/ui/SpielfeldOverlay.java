package de.itfo2.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.itfo2.objects.Spieler;
import de.itfo2.objects.Verwalter;

public class SpielfeldOverlay extends JComponent implements Runnable{
	JLabel[] figuren;
	private int felderZuGehen = 0;
	public SpielfeldOverlay(){
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/de/itfo2/ui/resources/kokowei.gif"));
        figuren =  new JLabel[4];
        figuren[0] = new JLabel(icon);
        figuren[1] = new JLabel(icon);
        figuren[2] = new JLabel(icon);
        figuren[3] = new JLabel(icon);
        figuren[0].setBounds(903, 940, 18, 17);
        figuren[1].setBounds(924, 940, 18, 17);
        figuren[2].setBounds(945, 940, 18, 17);
        figuren[3].setBounds(966, 940, 18, 17);
        add(figuren[0]);
        add(figuren[1]);
        add(figuren[2]);
        add(figuren[3]);
        figuren[0].setVisible(false);
        figuren[1].setVisible(false);
        figuren[2].setVisible(false);
        figuren[3].setVisible(false);
        
        
        //Für ne witzige Startfrequenz vielleicht^^
        icon = new ImageIcon(getClass().getResource("/de/itfo2/ui/resources/Gengar_back.gif"));
        JLabel cheatlabel01 = new JLabel(icon);
        cheatlabel01.setBounds(380, 150, 100, 100);
        icon = new ImageIcon(getClass().getResource("/de/itfo2/ui/resources/Nidorino_big.gif"));
        JLabel cheatlabel02 = new JLabel(icon);
        cheatlabel02.setBounds(460, 130, 100, 100);
        
        //add(cheatlabel01);
        //add(cheatlabel02);
	}
	
	public void setFigurLabel(int pos, Spieler spieler){
		ImageIcon ico = new ImageIcon(getClass().getResource(spieler.getSpielfigur().getPath()));
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource(spieler.getSpielfigur().getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
        figuren[pos].setIcon(new ImageIcon(img));
        figuren[pos].setVisible(true);
        repaint();
	}

	
   public void moveFigur(int felderZuGehen)
   {
	  this.felderZuGehen = felderZuGehen;
      Thread th = new Thread(this);
      th.start();
   }

   public void run(	)
   {
	    int felderZuGehenBetrag = felderZuGehen;
	    if(felderZuGehen < 0)
	    	felderZuGehenBetrag = felderZuGehen*(-1);
	    System.out.println("############"+felderZuGehenBetrag);
	    int i = 30*felderZuGehenBetrag;
	    Spieler curSpieler = Verwalter.getInstance().getCurSpieler();
	    int spielerAmZug = Verwalter.getInstance().getSpielerAmZug();
	    int spielerX = ((figuren[spielerAmZug].getLocation().x%90));
	    System.out.println("spielerX: "+spielerX);
	    String oldDirection = "";
	    String picPath = "";
	    
	    while(i>0){
	    	Point curLocation = figuren[spielerAmZug].getLocation();
	    	int x = 0; 
	    	int y = 0;
	    	//Berechnung von x und y hinzufügen!!
	    	if(curLocation.x==spielerX){
	    		y = -1;
	    		x = 0;
	    	}
	    	if(curLocation.x==(spielerX+900)){
	    		y = 1;
	    		x = 0;
	    	}
	    	if(curLocation.y==40){
	    		x = 1;
	    		y = 0;
	    	}
	    	if(curLocation.y==940){
	    		x = -1;
	    		y = 0;
	    	}
	    	if(curLocation.x==spielerX && curLocation.y==40){
	    		x = 1;
	    		y = 0;
	    	}
	    	else if(curLocation.x==(spielerX+900) && curLocation.y==40){
	    		x = 0;
	    		y = 1;
	    	}
	    	else if(curLocation.x==(spielerX+900) && curLocation.y==940){
	    		x = -1;
	    		y = 0;
	    	}
	    	else if(curLocation.x==spielerX && curLocation.y==940){
	    		x = 0;
	    		y = -1;
	    	}
	    	
	    	if(felderZuGehen<0){
	    		x = x*(-1);
	    		y = y*(-1);
	    	}
	    	
	    	
	    	if(y<0 | x>0)
	    		picPath = curSpieler.getSpielfigur().getPathBack();
	    	else{
	    		picPath = curSpieler.getSpielfigur().getPathBig();
	    	}
	    	
	    	System.out.println("x: "+x+"; y: "+y+";");
	    	System.out.println("curLocation x: "+(curLocation.x) +"  "+ "curLocation y: "+(curLocation.y));
	    	
	        System.out.println("i: "+i);
	    	
	    	this.repaint();
			this.revalidate();
			this.updateUI();
			figuren[spielerAmZug].setLocation((curLocation.x+(x*3)), (curLocation.y+(y*3)));
			
			if(i>30*felderZuGehenBetrag-10){
				ImageIcon ico = new ImageIcon(getClass().getResource(curSpieler.getSpielfigur().getPath()));
				ico.setImage(ico.getImage().getScaledInstance(figuren[spielerAmZug].getIcon().getIconWidth()+6,figuren[spielerAmZug].getIcon().getIconHeight()+6,Image.SCALE_DEFAULT)); 
		        figuren[spielerAmZug].setIcon(ico);
		        figuren[spielerAmZug].setSize(figuren[spielerAmZug].getWidth()+6, figuren[spielerAmZug].getHeight()+6);
			}
			else if(i==felderZuGehenBetrag*30-10){
				ImageIcon ico = new ImageIcon(getClass().getResource(picPath));
				ico.setImage(ico.getImage().getScaledInstance(78,77,Image.SCALE_DEFAULT)); 
				figuren[spielerAmZug].setSize(78, 77);
		        figuren[spielerAmZug].setIcon(ico);
			}
			else if(i==11){
				ImageIcon ico = new ImageIcon(getClass().getResource(curSpieler.getSpielfigur().getPath()));
		        ico.setImage(ico.getImage().getScaledInstance(78,77,Image.SCALE_DEFAULT)); 
		        figuren[spielerAmZug].setSize(78, 77);
		        figuren[spielerAmZug].setIcon(ico);
			}
			else if(i<11){
				ImageIcon ico = new ImageIcon(getClass().getResource(curSpieler.getSpielfigur().getPath()));
		        ico.setImage(ico.getImage().getScaledInstance(figuren[spielerAmZug].getIcon().getIconWidth()-6,figuren[spielerAmZug].getIcon().getIconHeight()-6,Image.SCALE_DEFAULT)); 
		        figuren[spielerAmZug].setIcon(ico); 
		        figuren[spielerAmZug].setSize(figuren[spielerAmZug].getWidth()-6, figuren[spielerAmZug].getHeight()-6);
			}
			else{
				if(!picPath.equals(oldDirection)){
					ImageIcon ico = new ImageIcon(getClass().getResource(picPath));
					figuren[spielerAmZug].setIcon(ico); 
					figuren[spielerAmZug].setSize(78, 77);
				}
				
			}
			oldDirection = picPath;
			
	        try {
	           Thread.sleep((long) ((40f/(float)felderZuGehenBetrag)+(.6f*felderZuGehenBetrag)));
	        } catch (InterruptedException e) {
	           
	        }
	        i--;
	    }
	    oldDirection = picPath;
	    correctPosition(spielerX);
   }
   
   public void correctPosition(int spielerX){
	   int x = MonopolyGUI.getInstance().spielfeld.getFeld(Verwalter.getInstance().getCurSpieler().getPlatz()).getLocation().x;
	   int y = MonopolyGUI.getInstance().spielfeld.getFeld(Verwalter.getInstance().getCurSpieler().getPlatz()).getLocation().y;
	   System.out.println("x: "+x+"; y: "+y);
	   figuren[Verwalter.getInstance().getSpielerAmZug()].setLocation(x+spielerX, y+40);
	  
   }
}
