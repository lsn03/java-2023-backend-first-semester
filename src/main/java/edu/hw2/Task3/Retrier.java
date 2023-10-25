package edu.hw2.Task3;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.exceptions.ConnectionException;
import edu.hw2.Task3.exceptions.MaxAttemptsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Retrier {
    private static final Logger LOGGER = LogManager.getLogger();
    private final int maxAttempts;
    private int attempts = 0;

    public Retrier(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public void procceed(Connection connection, String command) throws Exception {
        attempts = 0;
        while (attempts < maxAttempts) {
            try (connection) {
                connection.execute(command);

                LOGGER.info("while connection.execute");
                break;
            } catch (ConnectionException e) {
                LOGGER.error("Catch ConnectionException", e);

                attempts++;

                if (attempts >= maxAttempts) {

                    MaxAttemptsException exception =
                            new MaxAttemptsException("Failed execute command after " + maxAttempts + " attempts.", e);

                    LOGGER.error("Throw MaxAttemptsException ", e);

                    throw exception;

                }
            }
        }
    }

    public int getAttempts() {
        return attempts;
    }
}
