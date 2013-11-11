package de.itfo2.objects.cards;

import javax.swing.JOptionPane;

import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte11 extends Karte {
	final String text = "Zahle eine Strafe von 200 DM oder nimm eine Ereigniskarte";

	public Gemeinschaftskarte11() {

	}

	@Override
	public void effect() {

		int zahlen = MonopolyGUI.getInstance().createPopupChoiceDialog(text);
		if (zahlen == JOptionPane.YES_OPTION) {
			Verwalter.getInstance().getCurSpieler().addGeld(-200);
		} else {
			MonopolyGUI.getInstance().setEreigniskartenButtonEnabled(true);
			MonopolyGUI.getInstance().setNextButtonEnabled(false);
			MonopolyGUI.getInstance().setRollDiceButtonEnabled(false);

			System.out.println(text);
			MonopolyGUI.getInstance().createPopupDialog(text);
		}
	}
}
