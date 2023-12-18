package org.example.client;

public interface ClientView {
    void login();
    void logOut();
    void sendMessage(String message);
    void getMessage(String message);

}
