package org.example.client;

import org.example.server.Server;

public class Client {
    private ClientView clientView;
    private User user;

    private Server server;

    public Client(ClientView clientView, Server server){
        this.clientView = clientView;
        this.server = server;
    }

    public void connectToServer(User user){
        this.user = user;
        server.connectClient(this);
    }

    public void disconnectFromServer(){
        server.disconnectClient(this);
    }

    public void sendMessage(String message){
        server.getMessageFromClient(message);
        server.answerToClients();
    }

    public String getMessage(){
       String messages = server.getMessages();
       clientView.getMessage(messages);
       return messages;
    }

    public User getUser() {
        return user;
    }
}
