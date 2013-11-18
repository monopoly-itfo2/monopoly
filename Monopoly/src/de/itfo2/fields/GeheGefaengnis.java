package de.itfo2.fields;

import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

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
        Verwalter.getInstance().getCurSpieler().setPlatz(10);
        Verwalter.getInstance().getCurSpieler().setImGefaengnis(true);
        MonopolyGUI.getInstance().rueckeAuf(10);
    }
}
