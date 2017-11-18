package no.leanh.Clients;


import no.leanh.Client;

import java.io.*;
import java.net.Socket;

public class Worker implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private DataOutputStream out;
    private String user;
    private boolean firstMenu = true;
    private boolean isRunning = true;
    Client client;

    public Worker(Socket clientSocket) {
        try {
            socket = clientSocket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        try {
            client = new Client();

            while (isRunning) {
               // out.writeUTF(client.MainMenu());


            }
        } catch (IOException e) {
            System.out.println(e);
        }


    }
}
