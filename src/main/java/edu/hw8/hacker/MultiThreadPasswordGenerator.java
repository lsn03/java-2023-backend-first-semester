package edu.hw8.hacker;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MultiThreadPasswordGenerator extends AbstractPasswordGenerator {


    private final int threads;

    public MultiThreadPasswordGenerator(Integer threads) {
        this(MyUtility.DEFAULT_LENGTH, threads);
    }

    private MultiThreadPasswordGenerator(Integer length, Integer threads) {
        this(length, threads, MyUtility.fillWithDefaultLength());
    }

    public MultiThreadPasswordGenerator(Integer length, Integer threads, Map<String, String> passwordDatabase) {
        super(length, passwordDatabase);

        validate(threads);
        this.threads = threads;
        foundPasswords = Collections.synchronizedMap(new HashMap<>());

    }


    public void launch() {
        int combinationPerThread = maxCombinations / threads;

        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 0; i < threads; i++) {
            int start = i * combinationPerThread;
            int end = (i + 1) * combinationPerThread;
            Callable<Void> task = new PasswordGeneratorTask(start, end);
            Future<Void> future = executorService.submit(task);
            futures.add(future);
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private void validate(Integer threads) {
        Objects.requireNonNull(threads);

        if (threads < 0 || threads > Runtime.getRuntime().availableProcessors()) {
            throw new IllegalArgumentException();
        }

    }

    private class PasswordGeneratorTask implements Callable<Void> {
        private final int start;
        private final int end;

        PasswordGeneratorTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Void call() {
            generatePasswords(start, end);

            return null;
        }
    }

}
