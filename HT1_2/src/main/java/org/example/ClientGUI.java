package org.example;

import javax.swing.*;
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

    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;

    private static final int WIDHT = 400;
    private static final int HEIGHT = 400;

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private final JTextField tfIPAdress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("test_user");
    private final  JPasswordField tfPassword = new JPasswordField("test");
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private JButton btnSend, btnLogin;

    ClientGUI(){
        this.serverWindow = serverWindow;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDHT, HEIGHT);
        setTitle("Chat Client");

        panelTop.add(tfIPAdress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(createButtonLogin());
        add(panelTop, BorderLayout.NORTH);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(createButtonSendMessage(), BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);
        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        try {
            InetAddress address = InetAddress.getLocalHost();
            tfIPAdress.setText(address.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        setVisible(true);
    }

    private Component createButtonLogin(){
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(){
                    @Override
                    public void run() {
                       int port = Integer.parseInt(tfPort.getText());
                       try {
                           socket = new Socket(tfIPAdress.getText(), port);
                           log.append("Соединение с сервером уставновлено\n");
                       } catch (Exception e1) {
                           e1.printStackTrace();
                       }
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        while (true){
                            try {
                                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                String message = br.readLine();
                                log.setText(log.getText() + message +"\r\n");
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        });
        return btnSend;
    }

    private Component createButtonSendMessage(){
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        return btnLogin;
    }



    private void login(){

    }




    private void sendMessage(){
        String message = tfMessage.getText();
        tfMessage.setText("");
        log.append(message);
        log.append("\n");
    }
}
