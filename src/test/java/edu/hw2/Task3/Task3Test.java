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
//        Arrange
        int errorFreq = 2;
        RandomNumberGenerator random = new TestRandomNumberGenerator(new int[]{2, 2, 2, 2, 2, 2, 2}, errorFreq);
        ConnectionManager manager = new FaultyConnectionManager(random);

        int maxAttempts = 5;
        Retrier retrier = new Retrier(maxAttempts);
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, retrier);
//        Act && Assert
        assertThrows(MaxAttemptsException.class, executor::updatePackages);
    }

    @Test
    public void testThatAfterNTriesSaveConnectionException() {
        int errorFreq = 2;
        RandomNumberGenerator random = new TestRandomNumberGenerator(new int[]{2, 2, 2, 2, 2, 2, 2}, errorFreq);
        ConnectionManager manager = new FaultyConnectionManager(random);
        int maxAttempts = 5;
        Retrier retrier = new Retrier(maxAttempts);
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, retrier);
        try {
            executor.updatePackages();
        } catch (Exception e) {
            assertEquals(e.getCause().getClass(), ConnectionException.class);
        }


    }

    @Test
    public void testThatStateChangesSuccessful() {
        int errorFreq = 2;
        RandomNumberGenerator random = new TestRandomNumberGenerator(new int[]{2, 2, 2, 1, 1, 1}, errorFreq);
        ConnectionManager manager = new FaultyConnectionManager(random);
        int maxAttempts = 5;

        Retrier retrier = new Retrier(maxAttempts);
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, retrier);

        try {
            executor.updatePackages();
        } catch (Exception e) {
            assertEquals(e.getCause().getClass(), ConnectionException.class);
        } finally {
            int result = executor.getAttempts();
            assertEquals(3, result);
        }


    }
}
