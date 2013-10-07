package de.itfo2.objects;

public class Los implements Feld
{
	String bezeichnung;

    public Los(String bezeichnung){
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
