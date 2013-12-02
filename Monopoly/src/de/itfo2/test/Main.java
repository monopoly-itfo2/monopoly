package de.itfo2.test;

import de.itfo2.network.Connector;
import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Main {
    public static void main(String[]args) {
    	Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Connector.getInstance().ensureConnected();
				Verwalter.getInstance().connectionEstablished();
			}
		});
    	t.start();
    	
        Verwalter.getInstance();
        MonopolyGUI.getInstance();
    }
}
