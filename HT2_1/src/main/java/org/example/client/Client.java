package org.example.client;

import org.example.server.Server;

/**
 * Класс клиента
 */
public class Client {
    private ClientView clientView;
    private User user;

    private Server server;

    /**
     * Конструктор класса клиент
     * @param clientView интерфейс "вида" клиента
     * @param server сервер
     */
    public Client(ClientView clientView, Server server){
        this.clientView = clientView;
        this.server = server;
    }

    /**
     * Подключение текущего клиента к серверу
     * @param user пользователь
     */
    public void connectToServer(User user){
        this.user = user;
        server.connectClient(this);
    }

    /**
     * Отключение текущего клиента от сервера
     */
    public void disconnectFromServer(){
        server.disconnectClient(this);
    }

    /**
     * Отправка клиентом сообщения на сервер
     * @param message сообщение
     */
    public void sendMessage(String message){
            server.getMessageFromClient(message);
            server.answerToClients();
    }

    /**
     * Получение лога сообщенией от сервера, передача лога в интерфейс
     */
    public void getMessage(){
       String messages = server.getMessages();
       clientView.getMessage(messages);
    }

    /**
     * Остановка сервера
     */
    public void shutDownServer(){
        clientView.shutDownServer();
    }

    public User getUser() {
        return user;
    }
}
