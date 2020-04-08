package en.telegram.bots;

import en.telegram.client.TgUser;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackHandler {
    private UpdateHelper updateHelper;

    public CallbackHandler(Update update) {
        this.updateHelper = new UpdateHelper(update);
    }

    public String handleCallbackQuery() {
        String answer;
        switch (updateHelper.getMessageText()) {
            case "change time zone":
                TgUser.setLastUpdate(updateHelper.getUpdate());
                answer = setTimeZone();
                break;
            case "true time":
                //save date into DB
                answer = "Good!";
                break;
            default:
                answer = "Oops there is something wrong\uD83D\uDE25";
        }
        return answer;
    }

    public String handleCallbackQuery(Update update) {
        this.updateHelper = new UpdateHelper(update);
        return handleCallbackQuery();
    }

    private String setTimeZone() {
        String result = "Enter your current time zone in GMT format. For example:\n" +
                "+0, +1, +2 ... +12 or -1, -2, -3 ... -13.\n" +
                "If your GMT zone GMT+5, you should send me +5";
        return result;
    }
}
