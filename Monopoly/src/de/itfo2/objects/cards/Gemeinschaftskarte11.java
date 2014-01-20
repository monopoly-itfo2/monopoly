package de.itfo2.objects.cards;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import de.itfo2.objects.Verwalter;
import de.itfo2.ui.MessageBox;
import de.itfo2.ui.MonopolyGUI;

public class Gemeinschaftskarte11 extends Karte {
//	final String textAlt = "Zahle eine Strafe von 200 Euro oder nimm eine Ereigniskarte";
	final String text = "Zahle eine Strafe von 200 Euro. Möchtest du alternativ eine Ereigniskarte ziehen?";
	final String textZahlen = "Du zahlst eine Strafe von 200 Euro.";

	public Gemeinschaftskarte11() {

	}

	@Override
	public void effect() {

		MessageBox msg = new MessageBox(text);
		msg.setTitle("Gemeinschaftskarte");
		JButton strafe = msg.addOption("Strafe zahlen");
		JButton ziehen = msg.addOption("Karte ziehen");
		
		strafe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MonopolyGUI.getInstance().createPopupDialog(textZahlen);
				Verwalter.getInstance().getCurSpieler().addGeld(-200);
			}
		});
		ziehen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MonopolyGUI.getInstance().setEreigniskartenButtonEnabled(true);
				MonopolyGUI.getInstance().setNextButtonEnabled(false);
				MonopolyGUI.getInstance().setRollDiceButtonEnabled(false);
				MonopolyGUI.getInstance().addLogMessage(Verwalter.getInstance().getCurSpieler().getName() +": "+ text);
			}
		});
		msg.show();
		System.out.println(text);
	}
}
