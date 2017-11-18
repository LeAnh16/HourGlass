package no.leanh.Clients;

import no.leanh.file.Prop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

public class Remote {
    private final BufferedReader inFromUser;
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader inFromServer;
    Properties prop;


    public Remote() throws IOException //Constructor
    {
        Prop p = new Prop();
        prop = p.getServerProperties();
        inFromUser = new BufferedReader(new InputStreamReader(System.in));
        clientSocket = new Socket(prop.getProperty("ip"), Integer.parseInt(prop.getProperty("port")));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    public void start(){


    }
}