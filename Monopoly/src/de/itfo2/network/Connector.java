package de.itfo2.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import de.itfo2.event.EventBus;
import de.itfo2.event.LoginEvent;
import de.itfo2.event.MonopolyEvent;
import de.itfo2.event.UpdateSpielerlisteEvent;
import de.itfo2.network.server.Server;
import de.itfo2.network.server.SocketListener;
import de.itfo2.objects.Spieler;

/**
 * 
 * @author Marco Ernst
 * 
 */
public final class Connector {
	private static Connector INSTANCE = new Connector();

	public static Connector getInstance() {
		return INSTANCE;
	}

	private Connector() {
	}

	ObjectInputStream in;
	List<ObjectOutputStream> outStreams = new ArrayList<ObjectOutputStream>();
	private boolean isServer = true;
	private static List<Spieler> spielerliste = new ArrayList<Spieler>();

	public void login(Spieler spieler) {
		sentEvent(new LoginEvent(spieler));
		addSpieler(spieler);
	}

	public void ensureConnected() {
		Socket socket = null;
		List<Socket> sockets = discoverNetwork();

		if (sockets.size() > 0) {
			socket = sockets.get(0); // nimmt erstmal die erste gefundene
										// Verbindung
			isServer = false;
			ensureIO(socket);
		} else { // es wird selbst ein Server gestartet
			isServer = true;
			Server server = new Server();
			server.addSocketListener(new SocketListener() {
				@Override
				public void newSocketAccepted(Socket s) {
					System.out.println("Connection Accepted.");
					ensureIO(s);
				}
			});
			server.start();
			System.out.println("Server gestartet!");
		}
	}

	protected void ensureIO(final Socket socket) {
		try {
			final ObjectOutputStream out = new ObjectOutputStream(
					socket.getOutputStream());
			outStreams.add(out);
			Thread reader = new Thread() {
				@Override
				public void run() {
					try {
						ObjectInputStream in = new ObjectInputStream(
								socket.getInputStream());
						while (true) {
							Object event = in.readObject();
							if (isServer) {
								for (ObjectOutputStream outStr : outStreams) {
									if (!outStr.equals(out)) {
										outStr.writeObject(event);
									}
								}
							}
							EventBus.getInstance().sinkNetworkEvent(event);
						}
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null,
								"Die Verbindung wurde unterbrochen.",
								"Verbindung unterbrochen",
								JOptionPane.OK_CANCEL_OPTION);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			};
			reader.start();
		} catch (IOException e) {
			System.err.println("Error during 'ensureIO' : " + e);
		}
	}

	public List<Socket> discoverNetwork() {
		List<Socket> con = new ArrayList<Socket>();
		List<String> addresses = new ArrayList<String>();
		try {
			int[] localhost = NetworkInformation.getLocalhost();
			int[] subnetmask = NetworkInformation.getSubnetmask();
			int[] network = NetworkInformation
					.getNetwork(localhost, subnetmask);
			int[] broadcast = NetworkInformation.getBroadcast(localhost,
					subnetmask);
			for (int octetA = network[0]; octetA <= broadcast[0]; octetA++) {
				for (int octetB = network[1]; octetB <= broadcast[1]; octetB++) {
					for (int octetC = network[2]; octetC <= broadcast[2]; octetC++) {
						for (int octetD = network[3]; octetD <= broadcast[3]; octetD++) {
							addresses.add(octetA + "." + octetB + "." + octetC
									+ "." + octetD);
						}
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error during 'discoverNetwork' : " + e);
		}

		for (int i = 1; i < addresses.size() - 1; i++) {
			ConnectionTester discover = new ConnectionTester();
			discover.setAdress(addresses.get(i));
			discover.setConnectionsList(con);
			discover.start();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(con.size() + " connections found");
		return con;
	}

	public void sentEvent(MonopolyEvent event) {
		for (ObjectOutputStream out : outStreams) {
			try {
				out.writeObject(event);
				out.flush();
			} catch (IOException e) {
				System.err.println("Could not write to Outstream");
			}
		}
	}

	public void addSpieler(Spieler player) {
		if (!spielerliste.contains(player)) {
			spielerliste.add(player);
		}
		UpdateSpielerlisteEvent listevt = new UpdateSpielerlisteEvent(
				spielerliste);
		sentEvent(listevt);
	}

	public void setSpielerliste(List<Spieler> spielerliste) {
		Connector.spielerliste = spielerliste;
	}

	public List<Spieler> getSpielerliste() {
		return spielerliste;
	}
}
