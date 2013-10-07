package de.itfo2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MonopolyServer extends Thread {
	Socket clientSocket = null;
	public void run(){
        ServerSocket serverSocket = null;

        boolean listeningSocket = true;
        try {
            serverSocket = new ServerSocket(28000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 28000");
        }

        while(listeningSocket){
        	try{
     			clientSocket = serverSocket.accept();
                System.out.println("accepted: " +clientSocket.getInetAddress());
        	}catch (IOException e) {
				System.err.println(e);
			}
         }   
    }
	
	public Socket getClientSocket(){
//		System.out.println("socket was requested");
		return clientSocket;
	}
}