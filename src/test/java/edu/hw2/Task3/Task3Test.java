package edu.hw2.Task3;

import edu.hw2.Task3.exceptions.ConnectionException;
import edu.hw2.Task3.exceptions.MaxAttemptsException;
import edu.hw2.Task3.manager.ConnectionManager;
import edu.hw2.Task3.manager.FaultyConnectionManager;
import edu.hw2.Task3.randomizer.RandomNumberGenerator;
import edu.hw2.Task3.randomizer.TestRandomNumberGenerator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    public void testThatAfterNTriesThrowMaxAttemptsException() {
        RandomNumberGenerator random = new TestRandomNumberGenerator(new int[]{2, 2, 2, 2, 2, 2, 2});
        ConnectionManager manager = new FaultyConnectionManager(random);
        int maxAttempts = 5;
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);
        assertThrows(MaxAttemptsException.class, executor::updatePackages);
    }

    @Test
    public void testThatAfterNTriesSaveConnectionException() {
        RandomNumberGenerator random = new TestRandomNumberGenerator(new int[]{2, 2, 2, 2, 2, 2, 2});
        ConnectionManager manager = new FaultyConnectionManager(random);
        int maxAttempts = 5;
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);
        try {
            executor.updatePackages();
        } catch (Exception e) {
            assertEquals(e.getCause().getClass(), ConnectionException.class);
        }


    }
}
