package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte08 extends Karte{

    final String text = "Gehe in das Gef�ngnis. Begib dich direkt dorthin, gehe nicht �ber LOS, ziehe nicht 4000 DM ein";

    public Gemeinschaftskarte08() {

    }

    @Override
    public void effect() {
        MonopolyGUI.getInstance().createPopupDialog(getText()); //Hiermit erstellt man diese tollen Popups zum Bestätigen.
        Verwalter.getInstance().getCurSpieler().setPlatz(10);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
    }
}
