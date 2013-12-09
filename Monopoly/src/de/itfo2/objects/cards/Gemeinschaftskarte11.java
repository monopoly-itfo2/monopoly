package de.itfo2.objects.cards;

import javax.swing.JOptionPane;

import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte11 extends Karte {
//	final String textAlt = "Zahle eine Strafe von 200 Euro oder nimm eine Ereigniskarte";
	final String text = "Zahle eine Strafe von 200 Euro. Möchtest du alternativ eine Ereigniskarte ziehen?";
	final String textZahlen = "Du zahlst eine Strafe von 200 Euro.";

	public Gemeinschaftskarte11() {

	}

	@Override
	public void effect() {

		int zahlen = MonopolyGUI.getInstance().createPopupChoiceDialog(text);
		if (zahlen == JOptionPane.NO_OPTION) {
			MonopolyGUI.getInstance().createPopupDialog(textZahlen);
			Verwalter.getInstance().getCurSpieler().addGeld(-200);
		} else {
			MonopolyGUI.getInstance().setEreigniskartenButtonEnabled(true);
			MonopolyGUI.getInstance().setNextButtonEnabled(false);
			MonopolyGUI.getInstance().setRollDiceButtonEnabled(false);
	        MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);
		}
		System.out.println(text);
	}
}
