package edu.hw2.Task3.manager;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.FaultyConnection;
import edu.hw2.Task3.connection.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final Random random;

    public DefaultConnectionManager() {
        this.random = new Random();
    }

    @Override
    public Connection getConnection() {
        int value = random.nextInt();
        return value % 2 == 0 ? new StableConnection() : new FaultyConnection();
    }
}
