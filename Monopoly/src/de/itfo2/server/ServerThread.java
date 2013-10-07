package de.itfo2.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import de.itfo2.event.EventBus;
import de.itfo2.event.MonopolyEvent;
import de.itfo2.network.Connector;

/**
 * 
 * @author Marco Ernst
 *
 */
public class ServerThread extends Thread {

	private Socket socket = null;

	public ServerThread(Socket socket) {
		super("MiniServer");
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String token = "";
			while (true) {
				token = in.readLine();
				EventBus.getInstance().sinkNetworkEvent(token);
				System.out.println("serverThread : " + token);
			}

		} catch (IOException e) {
			System.err.println(e);
		}
	}

	// implement your methods here

}
