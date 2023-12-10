package org.example;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private static final String SERVER_STATUS_ON = "Server started";
    private static final String SERVER_STATUS_OFF = "Server stopped";


    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea(SERVER_STATUS_OFF);
    private boolean isServerWorking;

    public ServerWindow() {
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isServerWorking) {
                    isServerWorking = false;
                    log.setText(SERVER_STATUS_OFF);
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isServerWorking) {
                    isServerWorking = true;
                    log.setText(SERVER_STATUS_ON);
                }
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(1,2));
        panelTop.add(btnStart,BorderLayout.NORTH);
        panelTop.add(btnStop, BorderLayout.NORTH);
        add(panelTop);
        log.setEditable(false);
        add(log, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void getMessage(){

    }

}
