package de.itfo2.fields;

public class Ereignisfeld implements Feld
{
	String bezeichnung;

    public Ereignisfeld(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }

	@Override
	public String getBezeichnung() {
		return bezeichnung;
	}

	@Override
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
		
	}

	@Override
	public void handleFieldEffect() {
		// TODO Auto-generated method stub
		
	}
}
