package en.telegram.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class KeyboardRowController {
    public static KeyboardRow createKeyboardRow(String[] btns) {
        KeyboardRow kbr = new KeyboardRow();
        for (String btn : btns) {
            kbr.add(btn);
        }
        return kbr;
    }
}
