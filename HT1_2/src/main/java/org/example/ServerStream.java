package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerStream implements Runnable{
    private Socket socket = null;
    public static List<String> messages = new ArrayList<>();
    BufferedReader in;
    PrintWriter out;

    public ServerStream(Socket socket){
        super();
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            while (true){
                String message = in.readLine();
                if (message == null){
                    break;
                }
                messages.add(message + "\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
