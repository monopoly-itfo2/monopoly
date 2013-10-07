package de.itfo2.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import de.itfo2.event.EventBus;
import de.itfo2.event.MonopolyEvent;
import de.itfo2.event.WuerfelEvent;
import de.itfo2.server.MonopolyServer;

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

	protected Connector() {
	}

	BufferedReader in;
	DataOutputStream out;

	public void ensureConnected() {
		Socket socket = null;
		List<Socket> sockets = null;
		sockets = discoverNetwork();
		if (sockets.size() > 0) {
			socket = sockets.get(0); // nimmt erstmal die erste gefundene
										// Verbindung
			ensureIO(socket);
		} else { // es wird selbst ein Server gestartet
			MonopolyServer server = new MonopolyServer();
			server.start();
			boolean notConnected = true;
			while (notConnected) { // wartet bis ein Client connected hat und
									// holt den Socket
				socket = server.getClientSocket();
				if (socket != null) {
					notConnected = false;
				}
			}
			ensureIO(socket);
		}
	}

	private void ensureIO(Socket socket) { // stellt die input/output
											// verbindungen sicher
		try {
			out = new DataOutputStream(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			Thread reader = new Thread(new Runnable() {
				@Override
				public void run() {
					String token;
					while (true) {
						try {
							token = in.readLine();
//							System.out.println(token+ " on Connector Read Thread");
							EventBus.getInstance().sinkNetworkEvent(token);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
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
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(con.size() + "connections found");
		return con;
	}

	public void sentEvent(MonopolyEvent event) {
		try {
			out.writeUTF(event.getTokenizer().getToken(event) + "\n");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
