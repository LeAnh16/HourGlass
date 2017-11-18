package no.leanh.Clients;

import no.leanh.file.Prop;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;


public class Server {
    private static ServerSocket listener;
    Properties prop;

    public Server() throws IOException {
        Prop p = new Prop();
         prop = p.getServerProperties();

        listener = new ServerSocket(Integer.parseInt(prop.getProperty("port")));
        listener.accept();
        try {
            while (true) {
                Worker worker = new Worker(listener.accept());
                Thread thread = new Thread(worker);
                thread.start();
            }
        }finally {
                listener.close();
            }
        }
    }

