package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class HandlerServer extends Thread {

    Socket connection;
    MasterServer server;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    int number;

    public HandlerServer(Socket socket, MasterServer server, int number){
        try {
            this.connection = socket;
            dataInputStream = new DataInputStream(connection.getInputStream());
            dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeUTF(String.valueOf(number));
            this.number = number;
            this.server = server;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String input;
        try {
            //
            System.out.println("Waiting for messages...");
            //считываем сообщения клиента, передаем обратно
            while(true){
                if((input = dataInputStream.readUTF())!=null){
                    dataOutputStream.writeUTF("Сервер ответил: "+input+". Номер подключения: "+number);
                    System.out.println("Получили сообщение: "+input);
                    server.addMessage(input);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }
}