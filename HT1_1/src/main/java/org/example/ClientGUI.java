package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientGUI extends JFrame implements Observer {
    ServerWindow serverWindow;

    private static final int WIDHT = 400;
    private static final int HEIGHT = 400;

    JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private final JTextField tfIPAdress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("test_user");
    private final  JPasswordField tfPassword = new JPasswordField("test");
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private JButton btnSend, btnLogin;

    private boolean isLogged;

    ClientGUI(ServerWindow serverWindow, String title) {
        this.serverWindow = serverWindow;
        serverWindow.addObserver(this);

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
        add(log);
        setVisible(true);

    }

    private Component createButtonLogin(){
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
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
        if (!isLogged){
            if (serverWindow.isServerWorking()){
                log.append("Вы успешно подключились!\n");
                serverWindow.addLog(String.format("%s подключился к беседе\n", tfLogin.getText()));
                isLogged = true;
            } else {
                log.append("Невозможно подключиться к серверу\n");
            }
        } else {
            log.append("Вы уже авторизованы\n");
        }


    }


    private void sendMessage(){
        String message;
        if (isLogged) {
            message = tfLogin.getText() + ": " + tfMessage.getText() + "\n";
            tfMessage.setText("");
            serverWindow.addMessage(message);
            serverWindow.addLog(message);
        } else {
            message = "Необходимо вначале авторизоваться\n";
            log.append(message);
        }
    }


    @Override
    public void updateMessages(String message) {
      //  chat.setText("");
        if(isLogged){
            log.append(message);
      }
    }

    public JTextField getTfLogin() {
        return tfLogin;
    }
}
