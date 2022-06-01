package task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected;

    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Введите адрес сервера");
        return ConsoleHelper.readString();
    }

    protected int getServerPort(){
        ConsoleHelper.writeMessage("Введите порт сервера");
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        ConsoleHelper.writeMessage("Введите имя пользователя");
        return ConsoleHelper.readString();
    }

    public class SocketThread extends Thread {
        public void run() {
            String serverAddress = getServerAddress();
            int serverPort = getServerPort();
            try {
                Socket socket = new Socket(serverAddress,serverPort);
                connection = new Connection(socket);
                this.clientHandshake();
                this.clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }

        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    return;
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    processIncomingMessage(message.getData());
                } else if (message.getType() == MessageType.USER_ADDED){
                    informAboutAddingNewUser(message.getData());
                } else if (message.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());
                } else {
                    throw new IOException("Unexpected MessageType");
                }
                /*switch (message.getType()){
                    case TEXT:
                        processIncomingMessage(message.getData());
                        break;
                    case USER_ADDED:
                        informAboutAddingNewUser(message.getData());
                        break;
                    case USER_REMOVED:
                        informAboutDeletingNewUser(message.getData());
                        break;
                    default:
                        throw new IOException("Unexpected MessageType");
                }*/
            }
        }
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage("Участник с именем: " + userName + "присоединился к чату." );
        }
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage("Участник с именем: " + userName + "покинул чат.");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }
        
    }

    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("При отправке сообщения произошла ошибка");
            clientConnected = false;
        }
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        
            try {
                synchronized (this) {
                    wait();
                }

            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Во время ожидания потока произошла ошибка");
                return;
            }
        
        
            if (clientConnected)
                ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'");
            else 
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        while (clientConnected) {    
            String mes = ConsoleHelper.readString();
            if (mes.equals("exit"))
                break;
            
            if (shouldSendTextFromConsole()) 
                sendTextMessage(mes);
        }
    }



    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
