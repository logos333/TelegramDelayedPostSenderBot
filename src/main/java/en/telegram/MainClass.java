package en.telegram;

import en.telegram.bots.SenderDelayedPostBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MainClass {

    public static final String VERSION ="0.1";
    public static SenderDelayedPostBot senderDelayedPostBot;

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            senderDelayedPostBot = new SenderDelayedPostBot();
            telegramBotsApi.registerBot(senderDelayedPostBot);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
