package de.itfo2.objects;
public class Steuerfeld implements Feld
{
	String bezeichnung;
    int zuZahlen = 0; //Einkommens- und Zusatzsteuer sind unterrschiedlich hoch, das komtm hier rein

    public Steuerfeld(String bezeichnung, int zuZahlen)
    {
        this.bezeichnung = bezeichnung;
        this.zuZahlen = zuZahlen;
    }

	@Override
	public String getBezeichnung() {
		return bezeichnung;
	}

	@Override
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
		
	}

    public int getZuZahlen() {
        return zuZahlen;
    }

    public void setZuZahlen(int zuZahlen) {
        this.zuZahlen = zuZahlen;
    }

	@Override
	public void handleFieldEffect() {
		// TODO Auto-generated method stub
		
	}

}
