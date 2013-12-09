package de.itfo2.objects.cards;


import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte08 extends Karte{

    final String text = "Gehe in das Gefaengnis. Begib dich direkt dorthin, gehe nicht ueber LOS, ziehe nicht 4000 Euro ein";

    public Gemeinschaftskarte08() {

    }

    @Override
    public void effect() {
    	MonopolyGUI.getInstance().rueckeAuf(10);
        Verwalter.getInstance().getCurSpieler().setPlatz(10); 
        Verwalter.getInstance().getCurSpieler().setImGefaengnis(true);
        
        System.out.println(text);
        MonopolyGUI.getInstance().createPopupDialog(text);
        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);

    }
}
