package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Окно сервера
 */
public class ServerWindow extends JFrame implements Observed{
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private static final String SERVER_STATUS_ON = "Сервер запущен\n";
    private static final String SERVER_STATUS_OFF = "Сервер остановлен\n";


    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea(SERVER_STATUS_OFF);
    private boolean isServerWorking;

    private String messages = new String();

    private List<Observer> clientGUIS = new ArrayList<>(); // 1
    private List<ClientGUI> clientGUIList = new ArrayList<>(); // 2


    private FileWriter fileWriter;
    private FileReader fileReader;


    /**
     * Конструктор окна сервера
     */
    public ServerWindow() {
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isServerWorking) {
                    isServerWorking = false;
                    log.append(SERVER_STATUS_OFF);
                    addMessage("Сервер отключен\n");
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!isServerWorking) {
                    isServerWorking = true;
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

    /**
     * Добавление сообщения в лог
     * @param message - сообщение
     */
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

    /**
     * Метод проверки состояния сервера
     * @return true - если сервер включен
     */
    public boolean isServerWorking() {
        return isServerWorking;
    }

    /**
     * Метод заполнения лога серверного окна
     * @param msg - входящее сообщение
     */
    public void addMsg(String msg){
        this.log.append(msg);
        messages += msg;
    }

    /**
     * Логирование чата в файл
     * @param text - лог
     */

    public void logToFile(String text){
        fileWriter = null;
        try {
            fileWriter = new FileWriter("chatlog.txt", true);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e){
            this.log.append("Неудачная попытка записи чата в файл");
        }
    }

    public JTextArea getLog() {
        return log;
    }

    /**
     * TEST!!!
     * @param clientGUI
     */
    public void addGUI(ClientGUI clientGUI){
        this.clientGUIList.add(clientGUI);
    }

    public void removeGUI(ClientGUI clientGUI){
        this.clientGUIList.remove(clientGUI);
    }
    public void updateLog(){
        for (ClientGUI clientGUI: clientGUIList){
            clientGUI.addLog(messages);
        }
    }
}
