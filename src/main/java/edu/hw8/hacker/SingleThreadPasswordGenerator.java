package edu.hw8.hacker;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SingleThreadPasswordGenerator {


    private final int length;
    private final Map<String, String> passwordDatabase;
    private final Map<String, String> foundPasswords = new HashMap<>();
    private final int maxCombinations;

    public SingleThreadPasswordGenerator() {
        this(MyUtility.DEFAULT_LENGTH, MyUtility.fillWithDefaultLength());
    }

    public SingleThreadPasswordGenerator(Integer length, Map<String, String> passwordDatabase) {
        validate(length, passwordDatabase);
        this.length = length;
        this.passwordDatabase = passwordDatabase;
        maxCombinations = (int) Math.pow(MyUtility.ALPHABET.length(), this.length);
    }


    public void launch() {

        StringBuilder password = new StringBuilder(length);
        char[] charsetArray = MyUtility.ALPHABET.toCharArray();

        for (int i = 0; i < maxCombinations; i++) {
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
    }

    public Map<String, String> getFoundPasswords() {
        return foundPasswords;
    }

    private void validate(Integer length, Map<String, String> passwordDatabase) {
        Objects.requireNonNull(length);

        Objects.requireNonNull(length);

        if (length < 0 || length > MyUtility.MAX_PASSWORD_LENGTH) {
            throw new IllegalArgumentException();
        }

        if (passwordDatabase.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

}
