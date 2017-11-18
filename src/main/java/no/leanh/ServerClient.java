package no.leanh;

import no.leanh.Clients.Server;
import no.leanh.Clients.Worker;

import java.io.IOException;

public class ServerClient {
    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
