package de.itfo2.test;

import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Main {
	public static void main(String[] args) {
		Verwalter.getInstance();
		MonopolyGUI.getInstance();
	}
}