package de.itfo2.event;

import java.util.ArrayList;
import java.util.List;

import de.itfo2.event.listeners.RundenendeEventListener;
import de.itfo2.event.listeners.UpdateSpielerlisteEventListener;
import de.itfo2.event.listeners.WuerfelEventListener;
import de.itfo2.network.Connector;

/**
 * Der Eventbus verarbeitet die Events der Spieler und bietet die Moeglichkeiten
 * ein Event zu setzen ({@link sinkClientEvent()}) und auf Events zu warten (
 * {@link add...Listener()}).
 * 
 * 
 * @author Marco Ernst
 */
public final class EventBus {
	private static EventBus INSTANCE = new EventBus();

	public static EventBus getInstance() {
		return INSTANCE;
	}

	protected EventBus() {
	};

	private List<WuerfelEventListener> listerners_wuerfel = new ArrayList<WuerfelEventListener>();
	private List<UpdateSpielerlisteEventListener> listerners_spielerliste = new ArrayList<UpdateSpielerlisteEventListener>();
	private List<RundenendeEventListener> listerners_rundenende = new ArrayList<RundenendeEventListener>();

	public void sinkNetworkEvent(Object event) {
		if (event instanceof WuerfelEvent) {
			triggerWuerfelEvent((WuerfelEvent) event);
		} else if(event instanceof LoginEvent) {
			triggerLoginEvent((LoginEvent) event);
		} else if(event instanceof UpdateSpielerlisteEvent) {
			triggerUpdateSpielerlisteEvent((UpdateSpielerlisteEvent) event);
		}
	}

	public void sinkClientEvent(MonopolyEvent event) {
		Connector.getInstance().sentEvent(event);
	}

	// Listener registration
	public void addWuerfelEventListener(WuerfelEventListener listener) {
		listerners_wuerfel.add(listener);
	}
	public void addUpdateSpielerlisteEventListener(UpdateSpielerlisteEventListener listener) {
		listerners_spielerliste.add(listener);
	}
	
	public void addRundenendeEventListener(RundenendeEventListener listener) {
		listerners_rundenende.add(listener);
	}
	
	// WuerfelEvent
	private void triggerWuerfelEvent(WuerfelEvent event) {
		for (int i = 0; i < listerners_wuerfel.size(); i++) {
			listerners_wuerfel.get(i).onEvent(event);
		}
	}
	
	// SpieleranmeldungEvent
	private void triggerLoginEvent(LoginEvent event) {
		Connector.getInstance().addSpieler(event.getPlayer());
	}
	
	// SpieleranmeldungEvent
	private void triggerUpdateSpielerlisteEvent(UpdateSpielerlisteEvent event) {
		Connector.getInstance().setSpielerliste(event.getSpielerListe());
		for (int i = 0; i < listerners_spielerliste.size(); i++) {
			listerners_spielerliste.get(i).onEvent(event);
		}
	}
}
