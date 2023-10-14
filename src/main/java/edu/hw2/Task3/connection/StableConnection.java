package edu.hw2.Task3.connection;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StableConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void execute(String command) {
        LOGGER.info("Call execute({}) at StableConnection",command);
    }

    @Override
    public void close() throws ConnectionException {
        LOGGER.info("Call close() at StableConnection");
    }
}
