package edu.hw2.Task3.connection;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Random random;

    public FaultyConnection() {
        random = new Random();
    }

    @Override
    public void execute(String command) throws ConnectionException {
        int value = random.nextInt();
        LOGGER.info("Call execute({}) at FaultyConnection", command);
        if (value % 2 != 0) {
            LOGGER.info("Throw ConnectionException at FaultyConnection");
            throw new ConnectionException();
        }
    }

    @Override
    public void close() throws ConnectionException {
        LOGGER.info("Call close() at FaultyConnection");
    }
}
