package org.example.server;

import org.example.client.Client;

public interface ServerView {


    /**
     * Подключение клиента к серверу
     * @param client клиент, подключаемый к серверу
     */
    void connectedUser(Client client);

    /**
     * Отключение клиента от сервера
     * @param client клиент, отключаемый от сервера
     */
    void disconnectedUser(Client client);

    /**
     * Получение сообщения от клиента
     * @param message сообщение
     */
    void getMessageFromClient(String message);

}
