package edu.hw2.Task3;

import edu.hw2.Task3.manager.ConnectionManager;
import edu.hw2.Task3.manager.FaultyConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private static final Logger LOGGER = LogManager.getLogger();
    private PopularCommandExecutor executor;

//    private ConnectionManager connectionManager;

    public Server(PopularCommandExecutor executor) {
        this.executor = executor;
    }

    public void execute() {
        try {
            executor.updatePackages();
        } catch (Exception e) {
            LOGGER.info("Catched {} {}", e.getMessage(), e.getCause());
        }
    }

    @SuppressWarnings({"MagicNumber", "uncommentedmain"})
    public static void main(String[] args) {
        ConnectionManager manager = new FaultyConnectionManager();
        int maxAttempts = 10;
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(manager, maxAttempts);
        Server server = new Server(popularCommandExecutor);
        server.execute();
    }
}
