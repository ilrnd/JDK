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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerWindow extends JFrame implements Observed{
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private static final String SERVER_STATUS_ON = "Сервер запущен\n";
    private static final String SERVER_STATUS_OFF = "Сервер остановлен\n";


    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea(formatter.format(date) + " " + SERVER_STATUS_OFF);
    private boolean isServerWorking;

    private String messages = new String();

    private List<Observer> clientGUIS = new ArrayList<>();

    private static SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private static Date date = new Date();

    private static String dateNow = formatter.format(date);




    public ServerWindow() {
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isServerWorking) {
                    isServerWorking = false;
                    log.append(dateNow + " ");
                    log.append(SERVER_STATUS_OFF);
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!isServerWorking) {
                    isServerWorking = true;
                    log.append(dateNow + " ");
                    log.append(SERVER_STATUS_ON);
                }
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);

        add(log);
        log.setEditable(false);

        JPanel panelBottom = new JPanel(new GridLayout(1,2));
        panelBottom.add(btnStart, BorderLayout.SOUTH);
        panelBottom.add(btnStop, BorderLayout.EAST);

        add(panelBottom, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void addMessage(String message){
        this.messages = message;
        notifyObserver();
    }


    @Override
    public void addObserver(Observer observer) {
        this.clientGUIS.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {
        this.clientGUIS.remove(observer);

    }

    @Override
    public void notifyObserver() {
        for (Observer observer : clientGUIS){
            observer.updateMessages(this.messages);
        }
    }

    public boolean isServerWorking() {
        return isServerWorking;
    }

    public void addLog(String log){
        this.log.append(dateNow +" " + log);
    }
}
