package en.telegram.bots;

import en.telegram.MyLogger;
import en.telegram.Server;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.util.Properties;


public class SenderDelayedPostBot extends TelegramLongPollingBot {

    public SenderDelayedPostBot() {
        super();

        try {
            //saving current user
            Server.init(getMe());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Command command = new Command.Builder().setUpdates(update).build();
        String answ = sendMessage(command.createMessage());

        //call logger in the end
        new MyLogger(update, new String[]{"Answer: " + answ}).start();
    }

    public String getBotUsername() {
        return "@en.telegram.bots.SenderDelayedPostBot";
    }

    @Override
    public String getBotToken() {
        String token = "";
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/version.txt"));
            token = properties.getProperty("token", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    private String sendMessage(SendMessage message) {
        try {
            execute(message);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return message.getText();
    }

    public static User getServer() {
        return Server.getServer().getCurrentUser();
    }

}
