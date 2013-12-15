package de.itfo2.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{ 
        Socket clientSocket = null; 
        List<SocketListener> listeners = new ArrayList<SocketListener>(); 
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
                for(SocketListener sl : listeners){ 
                        sl.newSocketAccepted(clientSocket); 
                } 
                }catch (IOException e) { 
                                System.err.println(e); 
                        } 
         }    
    } 
        public void addSocketListener(SocketListener socketListener) { 
                listeners.add(socketListener); 
        } 
} 