package edu.hw8.hacker;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractPasswordGenerator {
    protected int length;
    protected Map<String, String> passwordDatabase;
    protected Map<String, String> foundPasswords;
    protected int maxCombinations;

    public AbstractPasswordGenerator(int length, Map<String, String> passwordDatabase) {
        validate(length, passwordDatabase);
        this.length = length;
        this.passwordDatabase = passwordDatabase;
        this.maxCombinations = (int) Math.pow(MyUtility.ALPHABET.length(), this.length);
    }

    public abstract void launch();

    public Map<String, String> getFoundPasswords() {
        return foundPasswords;
    }

    protected void validate(Integer length, Map<String, String> passwordDatabase) {
        Objects.requireNonNull(length);

        Objects.requireNonNull(length);

        if (length < 0 || length > MyUtility.MAX_PASSWORD_LENGTH) {
            throw new IllegalArgumentException();
        }

        if (passwordDatabase.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    protected void generatePasswords(int start, int end) {
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
    }
}
