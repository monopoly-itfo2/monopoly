package de.itfo2.objects;

import java.awt.*;
import java.io.Serializable;
import java.util.Observable;

public class Spieler extends Observable implements Serializable{

	private String name;
	private int konto;
	private int platz = 0;
	private boolean imGefaengnis = false;
	private int gefaengnisFrei = 0;
	private Color color;

	public Spieler(String name, int konto, Color color) {
		this.name = name;
		this.konto = konto;
		this.color = color;
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

	public void setGefaengnisFrei(int gefaengnisFrei) {
		this.gefaengnisFrei = gefaengnisFrei;
	}

	public Color getColor() {
		return this.color;
	}
}
