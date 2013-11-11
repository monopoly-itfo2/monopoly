package de.itfo2.test;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.xml.transform.Templates;

import de.itfo2.event.EventBus;
import de.itfo2.event.WuerfelEvent;
import de.itfo2.event.listeners.WuerfelEventListener;
import de.itfo2.network.Connector;
import de.itfo2.objects.Spieler;

public class NetworkTest {
	public static void main(String[] args) throws InterruptedException {
		final EventBus bus = EventBus.getInstance();
		final Connector con = Connector.getInstance();

		JPanel pnl = new JPanel();
		JFrame testramen = new JFrame();
		final JTextField tbx = new JTextField("Marco");
//		final JSlider sld = new JSlider(1, 12);
		JButton btn = new JButton("sink Event");
		final JTextArea area = new JTextArea();
		
//		bus.addWuerfelEventListener(new WuerfelEventListener() {
//			@Override
//			public void onEvent(WuerfelEvent event) {
//				System.out.println(event.getPlayer() + " würfelt " + event.getValue() + "\n");
//			}
//		});

		bus.addWuerfelEventListener(new WuerfelEventListener() {
			@Override
			public void onEvent(WuerfelEvent event) {
				System.out.println(event.getPlayer() + " wuerfelt " + event.getValue() + "\n");
			}

			private	String asString(List<Spieler> liste) {
				String result= "";
				for (int i = 0; i < liste.size(); i++) {
					result = result + liste.get(i).getName() + " ; ";
				}
				return result;
			}
		});
		tbx.setColumns(10);
		area.setColumns(20);
		area.setRows(20);
		pnl.add(tbx);
		pnl.add(area);
		pnl.add(btn);
		testramen.add(pnl);
		testramen.setSize(800, 600);
		testramen.setVisible(true);
		testramen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
