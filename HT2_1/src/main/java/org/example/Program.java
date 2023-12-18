package org.example;

import org.example.client.ClientGUI;
import org.example.server.ServerWindow;

public class Program {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        ClientGUI clientGUI1 = new ClientGUI(serverWindow, "Client1");
        ClientGUI clientGUI2 = new ClientGUI(serverWindow, "Client2");    }
}