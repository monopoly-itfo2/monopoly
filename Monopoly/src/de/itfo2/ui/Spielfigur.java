package de.itfo2.ui;


import de.itfo2.objects.Spieler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spielfigur{
    private String name;
    private String path;
    public Spielfigur(String name, String path){
    	this.name = name;
    	this.path = path;
    }
    
    public String getPath(){
    	return path;
    }
    
    public String getName(){
    	return name;
    }
    
    public String getPathBig(){
    	return path.substring(0, path.length()-4) + "_big.gif";
    }
    
    public String getPathBack(){
    	return path.substring(0, path.length()-4) + "_back.gif";
    }
}
