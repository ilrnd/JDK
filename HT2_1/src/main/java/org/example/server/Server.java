package org.example.server;

import org.example.client.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс сервера
 */
public class Server {
    private ServerView serverView;
    private Logged logged;
    private String messages = "";

    private List<Client> clients = new ArrayList<>();

    /**
     * Конуструктор сервера
     * @param serverView - передача интерфейса "вида" сервера
     * @param logged - передача интерфейса логирования сообщений
     */
    public Server(ServerView serverView,Logged logged) {
        this.serverView = serverView;
        this.logged = logged;
        messages = logged.readLog();
    }

    /**
     * Подключение клиента к серверу
     * @param client - клиент
     */
    public void connectClient(Client client){
            clients.add(client);
            serverView.connectedUser(client);
    }

    /**
     * Отключение клиента от сервера
     * @param client - клиент
     */
    public void disconnectClient(Client client){
            clients.remove(client);
            serverView.disconnectedUser(client);
    }

    /**
     * Получение сообщение от клиента
     * @param message сообщение
     */
    public void getMessageFromClient(String message){
        messages += message;
        serverView.getMessageFromClient(message);
        logged.writeLog(message);
    }

    /**
     * Ответ клиентам от сервера (всем подключенным)
     */
    public void answerToClients(){
        for (Client client: clients){
            client.getMessage();
        }
    }


    /**
     * Отключение всех клиентов от сервера
     */
    public void disconnectAllClients(){
        for (Client client:clients){
            client.shutDownServer();
        }
    }

    public String getMessages() {
        return messages;
    }
}
