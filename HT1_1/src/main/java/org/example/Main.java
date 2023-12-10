package org.example;

import java.awt.*;


public class Main {
    public static void main(String[] args) {

        ServerWindow serverWindow = new ServerWindow();
        ClientGUI clientGUI1 = new ClientGUI(serverWindow, "Client1");
        ClientGUI clientGUI2 = new ClientGUI(serverWindow, "Client2");
        serverWindow.printClients();

    }
}