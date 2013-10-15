package de.itfo2.test;

import java.awt.Color;

import de.itfo2.event.EventBus;
import de.itfo2.event.SpieleranmeldungEvent;
import de.itfo2.event.WuerfelEvent;
import de.itfo2.event.listeners.SpieleranmeldungEventListener;
import de.itfo2.event.listeners.WuerfelEventListener;
import de.itfo2.objects.Spieler;

public class TestArne2 {
	
	boolean spielerAktiv = false;
	boolean istServer = false;
	static Spieler gegner = null;

	public static void main(String[] args) {
		
		final EventBus bus = EventBus.getInstance();
		
		Spieler p = new Spieler("marco", 0, Color.RED);
		
		bus.addSpieleranmeldungEventListener(new SpieleranmeldungEventListener() {
			
			@Override
			public void onEvent(SpieleranmeldungEvent event) {
				gegner = event.getPlayer();
				System.out.println(event.getPlayer().getName() + "ist dein Gegner!");
				
			}
		});
		
		
		
		

	}

}
