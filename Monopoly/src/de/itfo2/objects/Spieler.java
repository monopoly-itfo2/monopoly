package de.itfo2.objects;

import java.awt.*;

public class Spieler {

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

	public void addMoney(int money) {
		konto += money;
	}

	public void addPlatz(int anzahl) {
		platz += anzahl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isImGefängnis() {
		return imGefaengnis;
	}

	public void setImGefängnis(boolean imGefängnis) {
				this.imGefaengnis = imGefängnis;
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
