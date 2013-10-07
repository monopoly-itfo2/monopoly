package de.itfo2.objects;

public class Freiparken implements Feld{

	String bezeichnung;
    private int summe;

    public Freiparken(String bezeichnung){
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

    public int getSumme() {
        return summe;
    }

    public void setSumme(int summe) {
        this.summe = summe;
    }

    public void addMoney(int money){
        this.summe += money;
    }

	@Override
	public void handleFieldEffect() {
		
	}
}
