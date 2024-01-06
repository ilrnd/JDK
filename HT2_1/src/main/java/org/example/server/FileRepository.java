
package org.example.server;

import java.io.*;

public class FileRepository implements Logged{
    private FileWriter  fileWriter;
    private final File chatLogFile = new File("chatlog.txt");


    @Override
    public void writeLog(String text) {
        fileWriter = null;
        try {
            fileWriter = new FileWriter(chatLogFile, true);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Ошибка записи в файл");
        }
    }

    @Override
    public String readLog() {
        String messages = new String("");
        StringBuilder  stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(chatLogFile));
            String str;
            while ((str = bufferedReader.readLine()) != null){
                stringBuilder.append(str).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Неудачная попытка чтения чата из файла\n");
        }
        messages = stringBuilder.toString();
        return messages;
    }
}
