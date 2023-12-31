package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Класс клиентского окна
 */
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
    private JTextField tfMessage;
    private JButton btnSend, btnLogin, btnLogOut;

    private boolean isLogged;

    /**
     * Конструктор клиентского окна
     * @param serverWindow - принимает окно сервера
     * @param title - заголовок окна
     */
    ClientGUI(ServerWindow serverWindow, String title) {
        this.serverWindow = serverWindow;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDHT, HEIGHT);
        setTitle(title);

        panelTop.add(tfIPAdress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(createButtonLogin());
        panelTop.add(createButtonLogOut());
        add(panelTop, BorderLayout.NORTH);
        panelBottom.add(createTfMessage(), BorderLayout.CENTER);
        panelBottom.add(createButtonSendMessage(), BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);
        add(log);
        log.setEditable(false);
        setVisible(true);

    }

    /**
     * Создание кнопки Login
     * @return компонент кнопки Login
     */
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

    private Component createButtonLogOut(){
        btnLogOut = new JButton("Log Out");
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logOut();
            }
        });
        return btnLogOut;
    }

    /**
     * Создание кнопки Send
     * @return компонент кнопки Send
     */
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

    /**
     * Создание текстового поля для ввода сообщений
     * @return компонент текстового поля для ввода сообщений
     */
    private Component createTfMessage(){
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    sendMessage();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        return tfMessage;
    }


    /**
     * Метод вызываемые при нажатии кнопки Login
     */
    private void login(){
        if (!isLogged){
            if (serverWindow.isServerWorking()){
                log.append("Вы успешно подключились!\n");
                serverWindow.addMsg(String.format("%s подключился к беседе\n", tfLogin.getText()));
                isLogged = true;
                tfLogin.setEditable(false);
                tfIPAdress.setEditable(false);
                tfPort.setEditable(false);
                tfPassword.setEditable(false);
                serverWindow.addObserver(this); // 1

                serverWindow.addGUI(this); // 2
            } else {
                log.append("Невозможно подключиться к серверу\n");
            }
        } else {
            log.append("Вы уже авторизованы\n");
        }


    }
    /**
     * Метод вызываемые при нажатии кнопки LogOut
     */
    private void logOut(){
        if(isLogged){
            log.append("Вы успешно отключились от чата!\n");
            serverWindow.addMsg(String.format("%s вышел из беседы\n", tfLogin.getText()));
            isLogged = false;
            tfLogin.setEditable(true);
            tfIPAdress.setEditable(true);
            tfPort.setEditable(true);
            tfPassword.setEditable(true);
            serverWindow.removeObserver(this);

            serverWindow.removeGUI(this);

        } else {
            log.append("Вы не авторизованы\n");
        }

    }

/**
 * TODO: Сделать архив чата
 * TODO: при подключении загрузить сообщения
 */

    /**
     * Метод отправки сообщения в лог
     */
    private void sendMessage(){
        String message;
        if(serverWindow.isServerWorking()){
            if (isLogged) {
                message = tfLogin.getText() + ": " + tfMessage.getText() + "\n";
                tfMessage.setText("");
                //serverWindow.addMessage(message);
                serverWindow.addMsg(message);
                serverWindow.logToFile(message);

                serverWindow.updateLog();
            } else {
                message = "Необходимо вначале авторизоваться\n";
                log.append(message);
            }
        } else {
            message = "Сервер недоступен\n";
            log.append(message);
        }
    }


    @Override
    public void updateMessages(String message) {
        if(isLogged){
            log.append(message);
      }
    }


    public void addLog(String message){
        this.log.setText(message);
    }

}
