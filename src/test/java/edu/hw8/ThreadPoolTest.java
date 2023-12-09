package edu.hw8;

import edu.hw8.my_thread_pool.Fibonnachi;
import edu.hw8.my_thread_pool.FixedThreadPool;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ThreadPoolTest {
    @Test
    public void test1() {
        Map<Integer, Long> excpectedMap = Map.of(
                1, 1L,
                2, 1L,
                3, 2L,
                4, 3L,
                5, 5L,
                6, 8L,
                7, 13L,
                8, 21L,
                9, 34L
        );
//        int threads = Runtime.getRuntime().availableProcessors();
        int threads = 2;
        CountDownLatch latch = new CountDownLatch(excpectedMap.size());
        try {

            FixedThreadPool threadPool = new FixedThreadPool(threads);
            threadPool.execute(() -> {
                System.out.println("call execute after close");
            });
            threadPool.start();
            var keySet = excpectedMap.keySet();


            for (int elem : keySet) {

                final int finalI = elem;

                threadPool.execute(() -> {
                    long fib = Fibonnachi.calculate(finalI);
                    assertEquals(excpectedMap.get(elem), fib);
                    System.out.println(Thread.currentThread().getName() + "\t n = " + elem + "\t fib = " + fib);
                    latch.countDown();
                });
            }
            latch.await();

            threadPool.close();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void failedTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FixedThreadPool(200);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new FixedThreadPool(-1);
        });
        assertThrows(NullPointerException.class, () -> {
            new FixedThreadPool(null);
        });

    }

    @Test
    public void fibTestFailed() {
        assertThrows(IllegalArgumentException.class, () -> Fibonnachi.calculate(45));
        assertThrows(IllegalArgumentException.class, () -> Fibonnachi.calculate(-5));
        assertThrows(NullPointerException.class, () -> Fibonnachi.calculate(null));
    }
}
