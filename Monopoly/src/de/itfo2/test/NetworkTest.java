package de.itfo2.test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import de.itfo2.event.EventBus;
import de.itfo2.event.WuerfelEvent;
import de.itfo2.event.listeners.WuerfelEventListener;

public class NetworkTest {
	public static void main(String[] args) throws InterruptedException {
		final EventBus bus = EventBus.getInstance();
		JPanel pnl = new JPanel();
		JFrame testramen = new JFrame();
		final JTextField tbx = new JTextField();
		final JSlider sld = new JSlider(1, 12);
		JButton btn = new JButton("sink Event");

		bus.addWuerfelEventListener(new WuerfelEventListener() {
			@Override
			public void onEvent(WuerfelEvent event) {
				System.out.println(event.getPlayer() + " würfelt " + event.getValue() + "\n");
			}
		});

		btn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				bus.sinkClientEvent(new WuerfelEvent(tbx.getText(), sld.getValue()));
			}
		});
		tbx.setColumns(10);
		pnl.add(tbx);
		pnl.add(sld);
		pnl.add(btn);
		testramen.add(pnl);
		testramen.setSize(800, 600);
		testramen.setVisible(true);
		testramen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
