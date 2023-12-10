package org.example;

import java.awt.*;


public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerWindow serverWindow = new ServerWindow();
                    serverWindow.setVisible(true);
                    ClientGUI clientGUI1 = new ClientGUI();
                    clientGUI1.setVisible(true);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}