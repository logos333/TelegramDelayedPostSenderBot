package en.telegram;

import en.telegram.bots.Command;
import en.telegram.bots.UpdateHelper;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;

public class MyLogger extends Thread {
    private UpdateHelper updateChild;
    String[] messages;

    public MyLogger(Update update, String[] messages) {
        this.updateChild = new UpdateHelper(update);
        this.messages = messages;
    }

    @Override
    public void run() {
        System.out.println(String.format("Time(Unix): %s, Converted date: %s, en.telegram.Server date: %s", updateChild.getMessageText(),
                Command.convertTime(updateChild.getDate()),
               LocalDateTime.now()));
        System.out.println("Chat ID: "+ updateChild.getChatId());
        System.out.println("Update ID: "+ updateChild.getUpdate().getUpdateId());
        System.out.println("UserName: "+ updateChild.getUser().getUserName());
        System.out.println("Text: "+ updateChild.getMessageText());

        for(String message : messages) {
            System.out.println(message);
        }

        System.out.println();
    }

}
