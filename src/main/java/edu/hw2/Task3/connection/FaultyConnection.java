package edu.hw2.Task3.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class FaultyConnection implements Connection {
    private final Random random;
    private static final Logger LOGGER = LogManager.getLogger();

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