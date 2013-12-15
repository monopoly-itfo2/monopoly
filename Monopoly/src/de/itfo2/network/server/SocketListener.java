package de.itfo2.network.server;

import java.net.Socket;

public interface SocketListener {

	void newSocketAccepted(Socket clientSocket);
	
}
