package org.example;

import java.awt.*;


public class Main {
    public static void main(String[] args) {

        ServerWindow serverWindow = new ServerWindow();
        Observer clientGUI1 = new ClientGUI(serverWindow, "Client1");
        Observer clientGUI2 = new ClientGUI(serverWindow, "Client2");

    }
}