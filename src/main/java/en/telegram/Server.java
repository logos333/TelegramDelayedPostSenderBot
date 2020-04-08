package en.telegram;

import org.telegram.telegrambots.meta.api.objects.User;

public class Server {
    private static Server server = null;
    private static User currentUser = null;
    private Server(){

    }

    public static Server init(User user) {
        currentUser = user;
        return getServer();
    }

    public static Server getServer() {
        try {
            if(server == null) {
                if(currentUser == null)
                    throw new Exception("en.telegram.Server has not been initialized with getMe() method!");
                server = new Server();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return server;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
