package edu.hw2.Task3;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.ConnectionException;
import edu.hw2.Task3.manager.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;
    private int attempts = 0;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public ConnectionManager getManager() {
        return manager;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void updatePackages() throws Exception {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) throws Exception {
        LOGGER.info("tryExecute");

        attempts = 0;
        Connection connection = manager.getConnection();
        while (attempts < maxAttempts) {
            try {

                connection.execute(command);
                LOGGER.info("while connection.execute");
                break;
            } catch (Exception e) {
                LOGGER.info("catch ConnectionException");
                attempts++;
                if (attempts >= maxAttempts) {

                    throw new ConnectionException("Failed execute command after " + maxAttempts + " attempts.", e);

                }
            } finally {
                LOGGER.info("Close at finally");
                connection.close();
            }
        }
        LOGGER.info("Close after while");
        connection.close();

    }
}
