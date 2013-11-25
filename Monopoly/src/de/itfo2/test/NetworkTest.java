package de.itfo2.test;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import de.itfo2.event.EventBus;
import de.itfo2.event.UpdateSpielerlisteEvent;
import de.itfo2.event.listeners.UpdateSpielerlisteEventListener;
import de.itfo2.network.Connector;
import de.itfo2.objects.Spieler;

public class NetworkTest {
	protected static Spieler s;

	public static void main(String[] args) throws InterruptedException {
		final EventBus bus = EventBus.getInstance();
		final Connector con = Connector.getInstance();

		JPanel pnl = new JPanel();
		JFrame testramen = new JFrame();
		final JTextField tbx = new JTextField("Marco");
		JButton btn = new JButton("login");
		final JTextArea area = new JTextArea();
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
		
		con.ensureConnected();
		System.out.println("connected");
		
		
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				s = new Spieler(tbx.getText(), 1);
				con.login(s);
				System.out.println(s.getName() +" eingeloggt");
			}
		});
		
		bus.addUpdateSpielerlisteEventListener(new UpdateSpielerlisteEventListener() {
			@Override
			public void onEvent(UpdateSpielerlisteEvent event) {
				List<Spieler> liste = con.getSpielerliste();
				printList(area, liste);
				
			}

			private void printList(JTextArea area, List<Spieler> liste) {
				StringBuffer sb = new StringBuffer();
				for(Spieler sp : liste){
					sb.append("\n" + sp.getName());
				}
				area.setText(sb.toString());
			}
		});
	}
}
