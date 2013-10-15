package de.itfo2.test;

import java.awt.Color;

import de.itfo2.event.EventBus;
import de.itfo2.event.SpieleranmeldungEvent;
import de.itfo2.event.WuerfelEvent;
import de.itfo2.event.listeners.SpieleranmeldungEventListener;
import de.itfo2.objects.Spieler;

public class TestArne {
	
	boolean spielerAktiv = false;
	boolean istServer = true;
	static Spieler gegner = null;

	public static void main(String[] args) {
		
		final EventBus bus = EventBus.getInstance();
		
		Spieler p = new Spieler("arne", 0, Color.GREEN);
		
		bus.sinkClientEvent(new SpieleranmeldungEvent(p));
		
		bus.addSpieleranmeldungEventListener(new SpieleranmeldungEventListener() {
			
			@Override
			public void onEvent(SpieleranmeldungEvent event) {
				gegner = event.getPlayer();
				System.out.println(event.getPlayer().getName() + "ist dein Gegner!");
				
			}
		});
		
		
		
		

	}

}
