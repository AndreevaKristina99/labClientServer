package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class MasterServer extends Thread {

    ServerSocket server = null;
    LinkedList<HandlerServer> connections;
    ArrayList<String> messages;

    public MasterServer() {
        try {
            connections = new LinkedList<>();
            messages = new ArrayList<>();
            server = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Can't connect to port");
            System.exit(-1);
        }
    }

    @Override
    public void run(){
        //Ждем подключения клиента, запускаем поток на каждое подключение
        while(true){
            try{
                System.out.println("Waiting for a Client...");
                Socket connection = server.accept();
                HandlerServer handler = new HandlerServer(connection,this,connections.size()+1);
                connections.add(handler);
                handler.start();
                System.out.println("Client connected");
            }catch(IOException e){
                System.out.println("");
                System.exit(-1);
            }
        }
    }

    public void sendMessageToAll(HandlerServer handler, String message){
        try {
            for (HandlerServer h : connections) {
                h.getDataOutputStream().writeUTF(message);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addMessage(String message){
        messages.add(message);
    }

    public String getMessages(){
        String ret="";
        for (int i=0; i<messages.size(); i++){
            ret += messages.get(i);
        }
        return ret;
    }
}
