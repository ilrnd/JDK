package org.example.server;

import org.example.client.Client;

public interface ServerView {


    /**
     * Подключение пользователя к серверу
     * @param client клиент, подключаемый к серверу
     */
    void connectedUser(Client client);

    /**
     * Отключение пользователя от сервера
     * @param client клиент, отключаемый от сервера
     */
    void disconnectedUser(Client client);

    void getMessageFromUser(String message);

}
