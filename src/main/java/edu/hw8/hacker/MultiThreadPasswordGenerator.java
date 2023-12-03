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

public class MultiThreadPasswordGenerator {


    private final int length;
    private final Map<String, String> passwordDatabase;
    private final Map<String, String> foundPasswords = Collections.synchronizedMap(new HashMap<>());
    private final int threads;

    public MultiThreadPasswordGenerator(Integer threads) {
        this(MyUtility.DEFAULT_LENGTH, threads);
    }

    private MultiThreadPasswordGenerator(Integer length, Integer threads) {
        this(length, threads, MyUtility.fillWithDefaultLength());
    }

    public MultiThreadPasswordGenerator(Integer length, Integer threads, Map<String, String> passwordDatabase) {
        validate(length, threads, passwordDatabase);
        this.length = length;
        this.threads = threads;
        this.passwordDatabase = passwordDatabase;
    }


    public void launch() {

        int maxCombinations = (int) Math.pow(MyUtility.ALPHABET.length(), length);
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

    public Map<String, String> getFoundPasswords() {
        return foundPasswords;
    }

    private void validate(Integer length, Integer threads, Map<String, String> passwordDatabase) {
        Objects.requireNonNull(length);
        Objects.requireNonNull(threads);
        Objects.requireNonNull(length);

        if (length < 0 || length > MyUtility.MAX_PASSWORD_LENGTH) {
            throw new IllegalArgumentException();
        }
        if (threads < 0 || threads > Runtime.getRuntime().availableProcessors()) {
            throw new IllegalArgumentException();
        }
        if (passwordDatabase.isEmpty()) {
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
            StringBuilder password = new StringBuilder(length);
            char[] charsetArray = MyUtility.ALPHABET.toCharArray();

            for (int i = start; i < end; i++) {
                int current = i;
                password.setLength(0);

                for (int j = 0; j < length; j++) {
                    password.append(charsetArray[current % MyUtility.ALPHABET.length()]);
                    current /= MyUtility.ALPHABET.length();
                }

                String hash = MyUtility.hashMD5(password.toString());
                if (passwordDatabase.containsKey(hash)) {
                    foundPasswords.put(passwordDatabase.get(hash), password.toString());
                }
            }

            return null;
        }
    }

}
