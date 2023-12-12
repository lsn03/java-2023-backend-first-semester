package edu.hw10.task_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProxyInDiskTest {
    @Test
    public void inDiskTest1() {

        String pathToDir = "src/test/resources/hw10/";
        String fullPath = "src/test/resources/hw10/" + FibImpl.class.getSimpleName() + ".txt";
        Path path = Path.of(pathToDir);

        FibImpl fib = new FibImpl();
        FibCalculator fibProxy = CacheProxy.create(fib, FibCalculator.class, pathToDir);
        int n = 5;
        Object[] args = new Object[]{n};
        fibProxy.fib(n);

        List<String> expectedList = List.of(Arrays.hashCode(args) + ":" + 5);

        CacheProxy cacheProxy = (CacheProxy) Proxy.getInvocationHandler(fibProxy);
        try {
            var actual = Files.readAllLines(Path.of(fullPath));
            assertEquals(expectedList, actual);
            assertTrue(cacheProxy.isLastRequestFromInvoke());
            Files.delete(Path.of(fullPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void inMemoryTest2() {
        String pathToDir = "src/test/resources/hw10/";
        String fullPath = "src/test/resources/hw10/" + FibImpl.class.getSimpleName() + ".txt";
        Path path = Path.of(pathToDir);

        FibImpl fib = new FibImpl();
        FibCalculator fibProxy = CacheProxy.create(fib, FibCalculator.class, pathToDir);
        int n = 5;
        Object[] args = new Object[]{n};
        fibProxy.fib(n);
        fibProxy.fib(n);

        List<String> expectedList = List.of(Arrays.hashCode(args) + ":" + 5);

        CacheProxy cacheProxy = (CacheProxy) Proxy.getInvocationHandler(fibProxy);
        try {
            var actual = Files.readAllLines(Path.of(fullPath));
            assertEquals(expectedList, actual);
            assertFalse(cacheProxy.isLastRequestFromInvoke());
            Files.delete(Path.of(fullPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void inMemoryTest3() {
        String pathToDir = "src/test/resources/hw10/";
        String fullPath = "src/test/resources/hw10/" + FibImpl.class.getSimpleName() + ".txt";
        List<String> expectedList = new ArrayList<>();

        FibImpl fib = new FibImpl();
        FibCalculator fibProxy = CacheProxy.create(fib, FibCalculator.class, pathToDir);
        int n = 5;
        Object[] args = new Object[]{n};
        fibProxy.fib(n);
        fibProxy.fib(n);
        fibProxy.fib(6);

        expectedList.add(Arrays.hashCode(args) + ":" + 5);

        args = new Object[]{6};
        expectedList.add(Arrays.hashCode(args) + ":" + 8);

        CacheProxy cacheProxy = (CacheProxy) Proxy.getInvocationHandler(fibProxy);
        try {
            var actual = Files.readAllLines(Path.of(fullPath));
            assertEquals(expectedList, actual);
            assertTrue(cacheProxy.isLastRequestFromInvoke());
            Files.delete(Path.of(fullPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
