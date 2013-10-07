package de.itfo2.event;

public interface EventTokenizer<T extends MonopolyEvent> {
	String getToken(T event);
	T getEvent(String token);
}
