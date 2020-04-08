package en.telegram.bots;

import en.telegram.MainClass;
import en.telegram.client.TgUser;
import en.telegram.keyboard.InlineKeyboardMarkupController;
import en.telegram.keyboard.KeyboardRowController;
import en.telegram.keyboard.ReplyKeyBoardMarkupController;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

public class Command implements ICommand {

    public static class Builder {
        private Update update;

        public Builder setUpdates(Update update) {
            this.update = update;
            if (!TgUser.hasLastUpdate())
                TgUser.setLastUpdate(update);
            return this;
        }

        public Command build() {
            return new Command(this);
        }

    }

    UpdateHelper updateHelper;
    String command;
    SendMessage message;
    CallbackHandler callbackHandler;

    private Command(@NotNull Builder builer) {
        updateHelper = new UpdateHelper(builer.update);
        command = updateHelper.getMessageText();
        message = new SendMessage();
        callbackHandler = new CallbackHandler(builer.update);
    }

    public SendMessage createMessage() {
        message.setChatId(updateHelper.getChatId());
        message.setText(doCommand());
        return message;
    }

    @Override
    public String doCommand() {
        String answer;

        //if inline button was pressed
        if (updateHelper.getUpdate().hasCallbackQuery()) {
            answer = callbackHandler.handleCallbackQuery();
        } else {
            //if command was typed
            switch (command) {
                case "/start":
                    answer = start();
                    break;
                case "/time":
                case "settings":
                    answer = time();
                    break;
                case "/creator":
                    answer = creator();
                    break;
                case "/help":
                    answer = help();
                    break;
                case "/about":
                    answer = about();
                    break;
                default:
                    answer = otherCommand();
            }
        }
        return answer;
    }


    @NotNull
    private String start() {
        message.setReplyMarkup(
                ReplyKeyBoardMarkupController.createReplyKeyboardMarkup(
                        Arrays.asList(
                                KeyboardRowController.createKeyboardRow(new String[]{"Create new post", "Create delayed post"}),
                                KeyboardRowController.createKeyboardRow(new String[]{"Settings"})), false));
        return String.format("Hello %s!\nThere are commands that you can use:/start\n/about\n/time", updateHelper.getUser().getFirstName());
    }

    @NotNull
    private String creator() {
        return "@temur333";
    }

    @NotNull
    private String time() {
        message.setReplyMarkup(InlineKeyboardMarkupController.createReplyKeyboardMarkup(new String[]{"Change time zone", "change time zone"}));
        return String.format("Current TimeZone GMT+8 (You can change it in the settings)\n%s", convertTime(updateHelper.getDate()));
    }

    @NotNull
    private String defaultAnswer() {
        if (TgUser.hasLastUpdate())
            return callbackHandler.handleCallbackQuery(TgUser.getLastUpdate());
        return "I don't understand \uD83D\uDE25";
    }


    private String about() {
        String aboutStr = "The aim of this bot is to help you manage your posts into group. " +
                "You can create posts with buttons, or create delayed posts.\nVersion: " + getVersion() + "\nCreated by @temur333";
        return aboutStr;
    }

    private String help() {
        return "Delayed post will be posted to group according your current time zone: GMT " + getTimeZone("+8");
    }

    private String otherCommand() {
        String res = defaultAnswer();
        String msg = updateHelper.getMessageText();

        if (TgUser.hasLastUpdate()) {
            if (TgUser.getLastUpdate().getCallbackQuery().getData().equals("change time zone"))
                if (Pattern.matches("(\\+|\\-)\\d{1,2}", msg)) {
                    res = "Is it your date and time?\n" + getTimeZone(msg);
                    message.setReplyMarkup(InlineKeyboardMarkupController.createReplyKeyboardMarkup(new String[]{"Yes", "true time", "No", "change time zone"}));
                }
        }


        return res;
    }


    private String getVersion() {
        return MainClass.VERSION;
    }

    public static String convertTime(Integer unixDate) {
        long unixSeconds = Long.valueOf(unixDate);
// convert seconds to milliseconds
        Date date = new java.util.Date(unixSeconds * 1000L);
// the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// give a timezone reference for formatting (see comment at the bottom)

        //sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+8"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    private String getTimeZone(String timezone) {
        long unixSeconds = updateHelper.getDate();
        Date userDate = new java.util.Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT" + timezone));
        return sdf.format(userDate);
    }
}
