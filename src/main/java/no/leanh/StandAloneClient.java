package no.leanh;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class StandAloneClient {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        Scanner sc = new Scanner(System.in);
        System.out.println(client.printMainMenu());
        client.mainMenu();

    }
}
