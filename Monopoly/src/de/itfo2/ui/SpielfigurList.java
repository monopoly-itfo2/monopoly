package de.itfo2.ui;

import java.io.File;
import java.util.LinkedList;

public class SpielfigurList {
	LinkedList<Spielfigur> spielfiguren;
	static SpielfigurList instance;
	
	public SpielfigurList(){
		spielfiguren = new LinkedList<Spielfigur>();
		initSpielfiguren();
	}
	
	public static SpielfigurList getInstance(){
		if(instance == null){
			instance = new SpielfigurList();
		}
		return instance;
	}
	
	public void initSpielfiguren(){
		File dir = new File(this.getClass().getResource("/de/itfo2/ui/resources/figures").getFile());
		File[] fileList = dir.listFiles();
		for(File f : fileList) {
			if(!f.getName().substring(f.getName().length()-8, f.getName().length()).equals("_big.gif") && !f.getName().substring(f.getName().length()-9, f.getName().length()).equals("_back.gif")){
				String path = "/de/itfo2/ui/resources/figures/"+f.getName();
				Spielfigur spielfigur = new Spielfigur(f.getName().substring(0, f.getName().length()-4), path);
			    spielfiguren.add(spielfigur);
			}	
		}
	}
	
	public Spielfigur getSpielfigurByName(String name){
		for(Spielfigur spielfigur : spielfiguren){
			if(spielfigur.getName().equals(name))
				return spielfigur;
		}
		return null;
	}
}
