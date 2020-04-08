package en.telegram.client;

import org.telegram.telegrambots.meta.api.objects.Update;

public class TgUser {
    private static Update lastUpdate = null;

    public static Update getLastUpdate() {
        return lastUpdate;
    }

    public static void setLastUpdate(Update lastUpdate) {
        TgUser.lastUpdate = lastUpdate;
    }

    public static boolean hasLastUpdate() {
        return lastUpdate != null;
    }

}
