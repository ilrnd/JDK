package org.example.server;

import org.example.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerView serverView;
    private String messages = "";

    private List<Client> clients = new ArrayList<>();

    public Server(ServerView serverView) {
        this.serverView = serverView;
    }
    public void connectClient(Client client){
            clients.add(client);
            serverView.connectedUser(client);
    }

    public void disconnectClient(Client client){
            clients.remove(client);
            serverView.disconnectedUser(client);
    }

    public void getMessageFromClient(String message){
        messages += message;
        serverView.getMessageFromUser(message);
    }

    public void answerToClients(){
        for (Client client: clients){
            client.getMessage();
        }
    }

    public String getMessages() {
        return messages;
    }
}
