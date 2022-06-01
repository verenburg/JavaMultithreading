package task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.javarush.task.task30.task3008.MessageType.NAME_REQUEST;

public class Server {
    private static volatile Map<String,Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        int port  = ConsoleHelper.readInt();

        try(ServerSocket serverSocket = new ServerSocket(port)){
            ConsoleHelper.writeMessage("Чат сервер запущен.");
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Произошла ошибка при запуске или работе сервера ");
        }
    }
   
    private static class Handler extends Thread {
        private Socket socket;

       public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
           ConsoleHelper.writeMessage("Установлено соединение с удаленным адресом " + socket.getRemoteSocketAddress());
           String clientName = null;
           
           try (Connection connection = new Connection(socket)){
                clientName = serverHandshake(connection);
                
                
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));
                
                notifyUsers(connection,clientName);

                serverMainLoop(connection, clientName);
                
           } catch (IOException | ClassNotFoundException e) {
               ConsoleHelper.writeMessage("Ошибка при обмене данными с " + socket.getRemoteSocketAddress());
           }
           
           if (clientName != null) {
               connectionMap.remove(clientName);
               sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));
           }
           
           ConsoleHelper.writeMessage("Соединение с " + socket.getRemoteSocketAddress() + "закрыто.");
        }

       

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            String userName;
            while (true) {
                connection.send(new Message(NAME_REQUEST));
                Message receivedMessage = connection.receive();
                if (receivedMessage.getType() != MessageType.USER_NAME) {
                    ConsoleHelper.writeMessage("Полученно сообщение от " + socket.getRemoteSocketAddress() +
                            ". Тип сообщения не соответсвует протоколу.");
                    continue;
                }

                userName = receivedMessage.getData();
                if (userName.isEmpty()){
                    ConsoleHelper.writeMessage("Попытка подключения к серверу с пустым именем от " + socket.getRemoteSocketAddress());
                    continue;
                }

                if (connectionMap.containsKey(userName)) {
                    ConsoleHelper.writeMessage("Попытка подключения к серверу с уже используемым именем от " + socket.getRemoteSocketAddress());
                    continue;
                }

                connectionMap.put(userName,connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                return userName;
            }
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (String name : connectionMap.keySet()){
                if (!name.equals(userName))
                    connection.send(new Message(MessageType.USER_ADDED, name));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();

                if (message.getType() == MessageType.TEXT) {
                    String data = message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + data));
                } else {
                    ConsoleHelper.writeMessage("Полученно сообщение от " + connection.getRemoteSocketAddress() + "не является текстом");
                }
            }
        }
    }





    public static void sendBroadcastMessage(Message message) {

        for (Connection connection : connectionMap.values()) {
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Не удалось отправить сообщение " + connection.getRemoteSocketAddress());
            }
        }
    }
}
