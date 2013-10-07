package de.itfo2.objects;

public class Gemeinschaftsfeld implements Feld
{
	String bezeichnung;

    public Gemeinschaftsfeld(String bezeichnung)
    {
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
