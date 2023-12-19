package org.example.server;

public interface Logged {
    void writeLog(String text);
    String readLog();
}
