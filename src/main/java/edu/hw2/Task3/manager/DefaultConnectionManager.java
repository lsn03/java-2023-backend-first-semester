package edu.hw2.Task3.manager;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.FaultyConnection;
import edu.hw2.Task3.connection.StableConnection;
import edu.hw2.Task3.randomizer.RandomNumberGenerator;


public class DefaultConnectionManager implements ConnectionManager {
    private final RandomNumberGenerator random;

    public DefaultConnectionManager(RandomNumberGenerator randomNumberGenerator) {
        this.random = randomNumberGenerator;
    }

    @Override
    public Connection getConnection() {
        int value = random.nextInt();
        return random.isError(value) ? new FaultyConnection(random) : new StableConnection();
    }
}
