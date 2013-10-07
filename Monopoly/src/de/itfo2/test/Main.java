package de.itfo2.test;

import java.io.IOException;

import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Main {
    public static void main(String[]args) throws IOException {

        Verwalter.getInstance();
        MonopolyGUI.getInstance();
        //KreisZeichnen z = new KreisZeichnen();
    }
}
