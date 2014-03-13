package de.itfo2.objects;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import de.itfo2.objects.cards.Karte;
import de.itfo2.ui.Spielfigur;

public class Spieler extends Observable implements Serializable{

	private String name;
	private int konto;
	private int platz = 0;
	private boolean imGefaengnis = false;
	private List<Karte> gefaengnisfreiKarten = new ArrayList<Karte>();
	private Color color;
	private Spielfigur spielfigur;
	private int rundenImGefaengnis = 0;

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

	public Color getColor() {
		return this.color;
	}
	public List<Karte> getGefaengnisfreiKarten() {
		return gefaengnisfreiKarten;
	}
	public void addGefaengnisfreiKarten(Karte gefaengnisfreiKarte) {
		gefaengnisfreiKarten.add(gefaengnisfreiKarte);
	}
	public void removeGefaengnisfreiKarte() {
		if(gefaengnisfreiKarten.size() > 0){
			Karte karte = gefaengnisfreiKarten.get(0);
			gefaengnisfreiKarten.remove(karte);
			if(karte.getTyp().equals("E")){
				Verwalter.getInstance().spielfeld.addEreigniskarte(karte);
			}else if(karte.getTyp().equals("G")){
				Verwalter.getInstance().spielfeld.addGemeinschaftskarte(karte);
			}
		}
	}
	public int getRundenImGefaengnis() {
		return rundenImGefaengnis;
	}
	public void setRundenImGefaengnis(int rundenImGefaengnis) {
		this.rundenImGefaengnis = rundenImGefaengnis;
	}
}
