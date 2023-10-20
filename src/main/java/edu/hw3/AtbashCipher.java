package edu.hw3;

import java.util.Objects;

public class AtbashCipher {

    private static final int LOWER_OFFSET = 97;
    private static final int MIN_LOWER_VALUE = 97;
    private static final int MAX_LOWER_VALUE = 122;
    private static final int HIGH_OFFSET = 65;
    private static final int MIN_HIGH_VALUE = 65;
    private static final int MAX_HIGH_VALUE = 90;
    private static final int LENGHT_OF_LATIN_ALPHABET = 25;

    public String atbash(String word) {

        Objects.requireNonNull(word);

        char[] newWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            char symbol = word.charAt(i);

            int currentOffset = 0;

            if (symbol <= MAX_LOWER_VALUE && symbol >= MIN_LOWER_VALUE) {
                currentOffset = LOWER_OFFSET;
            } else if (symbol >= MIN_HIGH_VALUE && symbol <= MAX_HIGH_VALUE) {
                currentOffset = HIGH_OFFSET;
            }

            if (currentOffset == 0) {
                newWord[i] = symbol;
                continue;
            }

            char newSymbol = (char) ((LENGHT_OF_LATIN_ALPHABET - ((int) symbol - currentOffset)) + currentOffset);

            newWord[i] = newSymbol;
        }
        return new String(newWord);
    }
}
