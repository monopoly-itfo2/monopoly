package de.itfo2.event;

import java.util.ArrayList;
import java.util.List;

import de.itfo2.network.Connector;

/**
 * Der Eventbus verarbeitet die Events der Spieler und bietet die Möglichkeiten
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
		Connector.getInstance().ensureConnected();
		System.out.println("eventbus instatiation complete");
	};

	private List<WuerfelEventListener> listerners_wuerfel = new ArrayList<WuerfelEventListener>();

	@SuppressWarnings("unchecked")
	public void sinkNetworkEvent(String token) {
//		System.out.println("sink event from Network: " + token);
		try {
			if (token.contains("WuerfelEvent")) {
				triggerWuerfelEvent(WuerfelEvent.Tokenizer.class.newInstance()
						.getEvent(token));
			}
		} catch (InstantiationException | IllegalAccessException e) {
			System.err.println(token + " not constructable: " + e);
		}
	}

	public void sinkClientEvent(MonopolyEvent event) {
//		System.out.println("sink event from Client: " + event.toString());
		Connector.getInstance().sentEvent(event);
	}

	private void triggerWuerfelEvent(WuerfelEvent event) {
		for (int i = 0; i < listerners_wuerfel.size(); i++) {
			listerners_wuerfel.get(i).onEvent(event);
		}
	}

	public void addWuerfelEventListener(WuerfelEventListener listener) {
		listerners_wuerfel.add(listener);
	}
}
