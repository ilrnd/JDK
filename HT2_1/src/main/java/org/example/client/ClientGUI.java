package org.example.client;

import org.example.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Класс клиентского окна
 */
public class ClientGUI extends JFrame implements ClientView {
    private ServerWindow serverWindow;
    private Client client;

    private static final int WIDHT = 400;
    private static final int HEIGHT = 400;

    JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private final JTextField tfIPAdress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    public final JTextField tfLogin = new JTextField("test_user");
    private final  JPasswordField tfPassword = new JPasswordField("test");
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private JTextField tfMessage;
    private JButton btnSend, btnLogin, btnLogOut;

    /**
     * Конструктор клиентского окна
     * @param serverWindow - принимает окно сервера
     * @param title - заголовок окна
     */
    public ClientGUI(ServerWindow serverWindow, String title) {
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
        JScrollPane jScrollPane = new JScrollPane(log);
        add(jScrollPane);
        loginPanelEditable(true);
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
                String message = tfLogin.getText() + ": " + tfMessage.getText() + "\n";
                sendMessage(message);
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

            /**
             * Перегрузка метода нажатия на клавишу Enter
             * @param e the event to be processed
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    sendMessage("test"); /// TODO
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
    @Override
    public void login(){
        if(serverWindow.getServer() != null){
            client = new Client(this, serverWindow.getServer());
            client.connectToServer(new User(tfLogin.getText(), tfPassword.getText(), tfIPAdress.getText(), tfPort.getText()));
            log.append("Вы успешно подключились\n");
            loginPanelEditable(false);
            client.getMessage();

        } else {
            log.append("Сервер не доступен\n");
        }
    }
    /**
     * Метод вызываемые при нажатии кнопки LogOut
     */
    @Override
    public void logOut(){
        log.append("Вы вышли из беседы\n");
        client.disconnectFromServer();
        loginPanelEditable(true);
    }

    /**
     * Метод отправки сообщения в лог
     */
    @Override
    public void sendMessage(String message){
        if(serverWindow.getServer() != null){
            if(!tfMessage.getText().equals("")){
                tfMessage.setText("");
                client.sendMessage(message);
            } else {
                log.append("Вы пытаетесь отправить пустое сообщение\n");
            }
        } else {
            log.append("Упс... проблемы с сервером");
        }
    }

    @Override
    public void getMessage(String message) {
        log.setText(message);
    }


    /**
     * Скрытие/активация панелей и кнопок авторизации пользователя
     * @param isEditable скрыть/показать
     */
    public void loginPanelEditable(boolean isEditable){
        tfLogin.setEditable(isEditable);
        tfPassword.setEditable(isEditable);
        tfIPAdress.setEditable(isEditable);
        tfPort.setEditable(isEditable);
        btnLogin.setEnabled(isEditable);
        btnLogOut.setEnabled(!isEditable);
    }

    /**
     * Метод при закрытии окна чата пользователем
     * @param e  the window event
     */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            if(client != null)
            client.disconnectFromServer();
        }
    }
}
