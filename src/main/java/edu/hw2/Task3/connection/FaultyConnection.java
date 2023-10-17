package edu.hw2.Task3.connection;

import edu.hw2.Task3.exceptions.ConnectionException;
import edu.hw2.Task3.randomizer.RandomNumberGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private final RandomNumberGenerator random;


    public FaultyConnection(RandomNumberGenerator generator) {
        random = generator;
    }

    @Override
    public void execute(String command) throws ConnectionException {
        int value = random.nextInt();
        LOGGER.info("Call execute({}) at FaultyConnection", command);

        if (value % 2 == 0) {
            LOGGER.warn("Throw ConnectionException at FaultyConnection");
            throw new ConnectionException("Cannot connect to server");
        }
    }

    @Override
    public void close() {
        LOGGER.info("Call close() at FaultyConnection");
    }
}
