package de.itfo2.test;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import de.itfo2.objects.Spieler;
import de.itfo2.event.EventBus;
import de.itfo2.event.WuerfelEvent;
import de.itfo2.event.WuerfelEventListener;

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
				System.out.println(event.getTokenizer().getToken(event)+"\n");
			}
		});
		
		btn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				bus.sinkClientEvent(new WuerfelEvent(new Spieler(tbx.getText(), 100, Color.BLUE),sld.getValue()));
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


		// while(true){
		// System.out.println("sinkClientEvent...");
		// bus.sinkClientEvent(new WuerfelEvent(new Spieler("clinkz"), 4));
		// Thread.sleep(4000);
		// }
	}
}
