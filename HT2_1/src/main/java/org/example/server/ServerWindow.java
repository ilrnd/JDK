package org.example.server;

import org.example.client.Client;
import org.example.client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Окно сервера
 */
public class ServerWindow extends JFrame implements ServerView{
    Server server;
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private static final String SERVER_STATUS_ON = "Сервер запущен\n";
    private static final String SERVER_STATUS_OFF = "Сервер остановлен\n";


    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    public JTextArea log = new JTextArea(SERVER_STATUS_OFF);

    /**
     * Конструктор окна сервера
     */
    public ServerWindow() {
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server = null;
                log.append(SERVER_STATUS_OFF);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createServer();
                log.append(SERVER_STATUS_ON);
                log.append(server.getMessages());
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        JScrollPane jScrollPane = new JScrollPane(log);
        add(jScrollPane);
        log.setEditable(false);

        JPanel panelBottom = new JPanel(new GridLayout(1,2));
        panelBottom.add(btnStart, BorderLayout.SOUTH);
        panelBottom.add(btnStop, BorderLayout.EAST);

        add(panelBottom, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void connectedUser(Client client) {
        log.append(client.getUser().getLogin() + " подключился к беседе\n");
    }

    @Override
    public void disconnectedUser(Client client) {
        log.append( client.getUser().getLogin() + " вышел из беседы\n");
    }

    @Override
    public void getMessageFromUser(String message) {
        log.append(message);
    }


    public Server getServer() {
        return server;
    }


    /**
     * Создание сервера при нажатии кнопки старт
     */
    public void createServer(){
        server = new Server(this, new FileRepository());
    }
}
