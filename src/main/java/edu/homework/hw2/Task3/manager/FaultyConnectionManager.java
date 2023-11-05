package edu.homework.hw2.Task3.manager;

import edu.homework.hw2.Task3.connection.Connection;
import edu.homework.hw2.Task3.connection.FaultyConnection;
import edu.homework.hw2.Task3.randomizer.RandomNumberGenerator;

public class FaultyConnectionManager implements ConnectionManager {
    private final RandomNumberGenerator random;

    public FaultyConnectionManager(RandomNumberGenerator randomNumberGenerator) {
        this.random = randomNumberGenerator;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(random);
    }
}
