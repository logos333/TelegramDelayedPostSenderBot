package en.telegram.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

/**
 * Buttons instead of standart keyboard
 */
public class ReplyKeyBoardMarkupController {
    public static ReplyKeyboardMarkup createReplyKeyboardMarkup(List<KeyboardRow> kbrlist, boolean oneTImeKeyboard) {
        ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();
        rkm.setResizeKeyboard(true);
        rkm.setOneTimeKeyboard(oneTImeKeyboard);
        return rkm.setKeyboard(kbrlist);
    }
}
