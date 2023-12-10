package org.example;

public class Message {
    private String text;
    private ClientGUI clientGUI;

    public Message(String text, ClientGUI clientGUI) {
        this.text = text;
        this.clientGUI = clientGUI;
    }

    public String getText() {
        return text;
    }

    public void setClientGUI(ClientGUI clientGUI) {
        this.clientGUI = clientGUI;
    }
}
