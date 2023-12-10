package org.example;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientGUI extends JFrame {
    ServerWindow serverWindow;

    private static final int WIDHT = 400;
    private static final int HEIGHT = 400;

    JTextArea chat;

    private final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private final JTextField tfIPAdress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("test_user");
    private final  JPasswordField tfPassword = new JPasswordField("test");
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private JButton btnSend, btnLogin;

    JScrollPane chatArea = new JScrollPane();


    ClientGUI(ServerWindow serverWindow, String title) {
        this.serverWindow = serverWindow;
        serverWindow.addClient(this);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDHT, HEIGHT);
        setTitle(title);

        panelTop.add(tfIPAdress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(createButtonLogin());
        add(panelTop, BorderLayout.NORTH);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(createButtonSendMessage(), BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);
        add(chatArea);
        setVisible(true);

    }

    private Component createButtonLogin(){
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        return btnLogin;
    }

    private Component createButtonSendMessage(){
        btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        return btnSend;
    }



    private void login(){

    }


    private void sendMessage(){
        String message = tfMessage.getText();
        tfMessage.setText("");
        Message message1 = new Message(message, this);
        serverWindow.addMessage(message1);
        serverWindow.updateChat();
    }


    public String clientInfo(){
        return tfLogin.getText();
    }


}
