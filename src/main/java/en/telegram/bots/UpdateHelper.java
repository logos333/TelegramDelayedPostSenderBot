package en.telegram.bots;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class UpdateHelper {
    private Update update;
    private String message;
    private Long chatId;
    private Integer date;
    private User user;


    public UpdateHelper(Update update) {
        this.update = update;
        message = update.hasCallbackQuery() ? update.getCallbackQuery().getData() : update.getMessage().getText().toLowerCase().trim();
        chatId = update.hasCallbackQuery() ? update.getCallbackQuery().getMessage().getChatId() : update.getMessage().getChatId();
        date = update.hasCallbackQuery() ? update.getCallbackQuery().getMessage().getDate() : update.getMessage().getDate();
        user = update.hasCallbackQuery() ? update.getCallbackQuery().getFrom() : update.getMessage().getFrom();
    }

    public String getMessageText() {
        return message;
    }

    public Long getChatId() {
        return chatId;
    }

    public Update getUpdate() {
        return update;
    }

    public Integer getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }
}
