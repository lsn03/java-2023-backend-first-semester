package edu.hw2.Task3;

import edu.hw2.Task3.manager.ConnectionManager;
import edu.hw2.Task3.manager.FaultyConnectionManager;
import edu.hw2.Task3.randomizer.RandomNumberGenerator;
import edu.hw2.Task3.randomizer.TestRandomNumberGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Server {
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
            LOGGER.error("Catched {} {}", e.getMessage(), e.getCause());
        }
    }

    @SuppressWarnings({"MagicNumber", "uncommentedmain"})
    public static void main(String[] args) {
        RandomNumberGenerator random = new TestRandomNumberGenerator(new int[]{2, 2, 2, 2, 2, 5});
        ConnectionManager manager = new FaultyConnectionManager(random);
        int maxAttempts = 10;
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(manager, maxAttempts);
        Server server = new Server(popularCommandExecutor);
        server.execute();

        
    }
}
