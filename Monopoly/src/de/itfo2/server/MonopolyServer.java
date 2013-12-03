package de.itfo2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MonopolyServer extends Thread {
	Socket clientSocket = null;
	private boolean error;
	public void run(){
        ServerSocket serverSocket = null;

        boolean listeningSocket = true;
        try {
            serverSocket = new ServerSocket(28000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 28000");
            error = true;
        }

        while(listeningSocket && !error){
        	try{
     			clientSocket = serverSocket.accept();
                System.out.println("accepted: " +clientSocket.getInetAddress());
        	}catch (IOException e) {
				break;
			}
         }   
    }
	
	public Socket getClientSocket(){
		return clientSocket;
	}
}