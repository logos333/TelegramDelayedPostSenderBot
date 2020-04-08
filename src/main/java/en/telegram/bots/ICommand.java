package en.telegram.bots;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ICommand {
    String doCommand();
}
