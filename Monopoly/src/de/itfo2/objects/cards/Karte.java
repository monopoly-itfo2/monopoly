package de.itfo2.objects.cards;


public abstract class Karte {
    private String text;
    private String typ;

    Karte() {
        this.text = "Start";
    }
    public String getText(){
        return text;
    }

    public void effect() {

    }
	public String getTyp() {
		return typ;
	}

}
