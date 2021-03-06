package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte16 extends Karte{

    final String text = "Bankirrtum zu deinen Gunsten, ziehe 4000 Euro ein";

    public Gemeinschaftskarte16() {

    }

    @Override
    public void effect() {
       Verwalter.getInstance().getCurSpieler().addGeld(4000);
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

    }
}
