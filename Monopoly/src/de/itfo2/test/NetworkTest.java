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
		JButton btn = new JButton("sink Event");
		final JTextArea area = new JTextArea();
		
		con.ensureConnected();
		area.setText(area.getText()+"\n" + "connected");
		
		Spieler s = new Spieler(tbx.getText(), 1, Color.yellow);
		
		con.login(s);
		
		List<Spieler> liste = con.getSpielerliste();
        System.out.println(liste.size() + " Spieler eingeloggt");
        while (Connector.getInstance().getSpielerliste().size() == liste.size()){
		}
        System.out.println("Spielerliste aktualisiert");
        liste = Connector.getInstance().getSpielerliste();
        for (Spieler sp : liste) {
			if(!s.equals(s)){
				area.setText(area.getText()+"\n" + "Spieler gefunden: " + sp.getName());
			}
		}


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
