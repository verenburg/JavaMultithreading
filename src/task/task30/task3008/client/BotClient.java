package task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {
    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() {
            String textOfMeeting = "Привет чатику. Я бот. Понимаю команды: дата, день, " +
                    "месяц, год, время, час, минуты, секунды.";
            sendTextMessage(textOfMeeting);
            try {
                super.clientMainLoop();
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
            }
        }

        @Override
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
            String[] nameAndText = message.split(": ");
            if (nameAndText.length != 2)
                return;
            String sdFormat = null;
            switch (nameAndText[1]) {
                case "дата":
                    sdFormat = "d.MM.YYYY";
                    break;
                case "день":
                    sdFormat = "d";
                    break;
                case "месяц":
                    sdFormat = "MMMM";
                    break;
                case "год":
                    sdFormat = "YYYY";
                    break;
                case "время":
                    sdFormat = "H:mm:ss";
                    break;
                case "час":
                    sdFormat = "H";
                    break;
                case "минуты":
                    sdFormat = "m";
                    break;
                case "секунды":
                    sdFormat = "s";
                    break;
            }
            if (sdFormat != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(sdFormat);
                BotClient.this.sendTextMessage("Информация для " + nameAndText[0] + ": " 
                        + sdf.format(Calendar.getInstance().getTime()));
            }
        }
    }

    protected SocketThread getSocketThread(){
        return new com.javarush.task.task30.task3008.client.BotClient.BotSocketThread();
    }

    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    protected String getUserName() {
        return "date_bot_" + (int) (Math.random()*100);
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
