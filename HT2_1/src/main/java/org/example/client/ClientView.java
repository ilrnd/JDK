package org.example.client;

public interface ClientView {
    /**
     * Подключение к серверу
     */
    void login();

    /**
     * Отключение от сервера
     */
    void logOut();

    /**
     * Отправка сообщения на сервер
     * @param message сообщение на сервер
     */
    void sendMessage(String message);

    /**
     * Получение сообщения от сервера
     * @param message сообщение от сервера
     */
    void getMessage(String message);

    /**
     * Отключение (остановка) сервера
     */
    void  shutDownServer();

}
