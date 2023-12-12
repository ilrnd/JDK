package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Окно сервера
 */
public class ServerWindow extends JFrame{
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

    private List<ClientGUI> clientGUIList = new ArrayList<>();


    private FileWriter fileWriter;
    private FileReader fileReader;

    private final File chatLogFile = new File("chatlog.txt");


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
                    for(ClientGUI clientGUI : clientGUIList){
                        clientGUI.setStatusConnection("Сервер недоступен\n");
                    }
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!isServerWorking) {
                    isServerWorking = true;
                    log.append(SERVER_STATUS_ON);
                    for(ClientGUI clientGUI : clientGUIList){
                        clientGUI.setStatusConnection("Сервер работает\n");
                    }
                    readFromLogFile(chatLogFile);
                    log.append(messages);
                }
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
            fileWriter = new FileWriter(chatLogFile, true);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e){
            this.log.append("Неудачная попытка записи чата в файл");
        }
    }

    /**
     * Чтение записи чата из файла
     * @param file - файл лога чата
     */
    public void readFromLogFile(File file){
        StringBuilder  stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(chatLogFile));
            String str;
            while ((str = bufferedReader.readLine()) != null){
                stringBuilder.append(str).append("\n");
            }
        } catch (IOException e) {
            this.log.append("Неудачная попытка чтения чата из файла\n");
        }
        messages = stringBuilder.toString();
    }

    /**
     * Добавление подключения пользователя к серверу (имитация)
     * @param clientGUI пользователь
     */
    public void addGUI(ClientGUI clientGUI){
        this.clientGUIList.add(clientGUI);
        this.log.append(String.format("%s подключился к беседе\n", clientGUI.tfLogin.getText()));
    }

    /**
     * Удаление подключения пользователя к серверу (имитация)
     * @param clientGUI пользователь
     */
    public void removeGUI(ClientGUI clientGUI){
        this.clientGUIList.remove(clientGUI);
        this.log.append(String.format("%s вышел из беседы\n", clientGUI.tfLogin.getText()));
    }

    /**
     * Обновление лога чата
     */
    public void updateLog(){
        for (ClientGUI clientGUI: clientGUIList){
            clientGUI.addMsg(messages);
        }
    }
}
