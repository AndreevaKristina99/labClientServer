package client;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client extends Thread {

    private int number;//полученный от сервера номер
    private Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    private TextArea textArea;

    public Client(TextArea textArea){
        try {
            socket = new Socket("localhost", 4444);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            number = Integer.parseInt(dataInputStream.readUTF());
            this.textArea = textArea;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Welcome to Client!");
            Scanner sc = new Scanner(System.in);

            while (!socket.isClosed()) {
                if (sc.hasNext()) {
                    String message = sc.next();
                    System.out.println("Считали сообщение для отправки: " + message);
                    dataOutputStream.writeUTF(message);
                    System.out.println("Сообщение отправлено!");
                    message = dataInputStream.readUTF();
                    System.out.println("Получили ответ: " + message);
                    textArea.appendText(message);
                }
            }

            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumber(){
        return number;
    }
}