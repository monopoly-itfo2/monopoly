package de.itfo2.fields;

public class GeheGefaengnis implements Feld {

    String bezeichnung;

    public GeheGefaengnis(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }

    @Override
    public String getBezeichnung() {
        return bezeichnung;
    }

    @Override
    public void setBezeichnung(String bezeichnung) {

    }

    @Override
    public void handleFieldEffect() {

    }
}
