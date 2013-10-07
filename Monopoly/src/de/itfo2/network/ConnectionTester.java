package de.itfo2.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
/**
 * 
 * @author Marco Ernst
 *
 */
public class ConnectionTester extends Thread {
	List<Socket> connections;
	String address;
	@Override
	public void run() {
		try {
			Socket s = new Socket();
			s.connect(new InetSocketAddress(address, 28000),500);
			connections.add(s);
			System.out.println("added : " + address);
		} catch (IOException e) {}
	}
	public void setConnectionsList(List<Socket> connections){
		this.connections = connections;
	}
	public void setAdress(String address) {
		this.address = address;
	}
}
