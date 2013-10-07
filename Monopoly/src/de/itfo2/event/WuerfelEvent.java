package de.itfo2.event;

import java.awt.Color;

import de.itfo2.objects.Spieler;


/**
 * 
 * @author Marco Ernst
 *
 */
public class WuerfelEvent implements MonopolyEvent {

	private int value = 0;
	private Spieler player = null;

	public WuerfelEvent(Spieler spieler, int wert) {
		player = spieler;
		value = wert;
	}
	
	public Spieler getPlayer() {
		return player;
	}

	public int getValue() {
		return value;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public EventTokenizer getTokenizer() {
		return new Tokenizer();
	}
	
	public static class Tokenizer implements EventTokenizer<WuerfelEvent>{
		@Override
		public String getToken(WuerfelEvent event) {
			StringBuffer sb = new StringBuffer();
			sb.append(event.getClass().getSimpleName());
			sb.append("(");
			sb.append(event.getPlayer().getName());
			sb.append(",");
			sb.append(event.getPlayer().getKonto());
			sb.append(",");
			sb.append(event.getPlayer().getColor());
			sb.append(",");
			sb.append(event.getValue());
			sb.append(")");
			return sb.toString();
		}

		@Override
		public WuerfelEvent getEvent(String token) {
			String[] args = token.substring(token.indexOf('(')+1, token.indexOf(')')).split(",");
			String value = token.substring(token.indexOf(',')+1, token.indexOf(')'));
			return new WuerfelEvent(new Spieler(args[0],Integer.parseInt(args[1]),Color.decode(args[2])), Integer.parseInt(args[3]));
		}
	}
	
}
