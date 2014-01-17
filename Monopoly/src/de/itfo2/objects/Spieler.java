package de.itfo2.objects;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.Observable;

import de.itfo2.ui.Spielfigur;

public class Spieler extends Observable implements Serializable{

	private String name;
	private int konto;
	private int platz = 0;
	private boolean imGefaengnis = false;
	private int gefaengnisFrei = 0;
	private Color color;
	private Spielfigur spielfigur;

	public Spieler(String name, int konto, Color color, Spielfigur spielfigur) {
		this.name = name;
		this.konto = konto;
		this.color = color;
		this.spielfigur = spielfigur;
	}
	public void setColor(Color color){
		this.color = color;
	}
	
	public Spielfigur getSpielfigur(){
		return spielfigur;
	}

	public void addGeld(int geld) {
		konto += geld;
        setChanged();
        notifyObservers();
	}

	public void addPlatz(int anzahl) {
		
        if((anzahl+platz)>=40)
            addGeld(4000);
		platz = (anzahl+platz)%40;

//		try {
//			Verwalter.getInstance().getSpielfeld().getFeld(platz).handleFieldEffect();    
//        } catch (IOException e1) {
//        }
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
        setChanged();
        notifyObservers();
	}

	public int getKonto() {
		return konto;
	}

	public void setKonto(int konto) {
		this.konto = konto;
	}

	public int getPlatz() {
		return platz;
	}

	public void setPlatz(int platz) {
		this.platz = platz;
		try {
			Verwalter.getInstance().getSpielfeld().getFeld(platz).handleFieldEffect();    
        } catch (IOException e1) {
        }
	}

	public boolean isImGefaengnis() {
		return imGefaengnis;
	}

	public void setImGefaengnis(boolean imGefaengnis) {
				this.imGefaengnis = imGefaengnis;
	}

	public int getGefaengnisFrei() {
		return gefaengnisFrei;
	}

	public void addGefaengnisFrei(int gefaengnisFrei) {
		this.gefaengnisFrei += gefaengnisFrei;
	}

	public Color getColor() {
		return this.color;
	}
}
