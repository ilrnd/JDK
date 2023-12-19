package org.example.client;

/**
 * Класс пользователя
 */
public class User {
    private String login;
    private String password;
    private String ipAdress;
    private String port;

    public User(String login, String password, String ipAdress, String port) {
        this.login = login;
        this.password = password;
        this.ipAdress = ipAdress;
        this.port = port;
    }

    public String getLogin() {
        return login;
    }
}
