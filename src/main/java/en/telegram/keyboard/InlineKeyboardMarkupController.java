package en.telegram.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Buttons under sended message
 */
public class InlineKeyboardMarkupController {
    public static InlineKeyboardMarkup createReplyKeyboardMarkup(String[] btns) {
        InlineKeyboardMarkup ikm = new InlineKeyboardMarkup();
        KeyboardRow kbr = new KeyboardRow();
        List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

        for(int i=0; i<btns.length; i++) {
            keyboardRow.add(new InlineKeyboardButton(btns[i]).setCallbackData(btns[++i]));
        }
        return ikm.setKeyboard(Collections.singletonList(keyboardRow));
    }
}
