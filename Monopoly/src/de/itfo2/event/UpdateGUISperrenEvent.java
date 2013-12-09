package de.itfo2.event;

public class UpdateGUISperrenEvent implements MonopolyEvent {

	private static final long serialVersionUID = 8277155583888076994L;
	
	private String name;
	
	public UpdateGUISperrenEvent(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
