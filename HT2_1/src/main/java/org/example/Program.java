package org.example;

import org.example.client.ClientGUI;
import org.example.server.ServerWindow;

public class Program {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI(serverWindow, "Client1");
        new ClientGUI(serverWindow, "Client2");    }
}