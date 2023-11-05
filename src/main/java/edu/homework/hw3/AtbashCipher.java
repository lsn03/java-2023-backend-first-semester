package edu.homework.hw3;

import java.util.Objects;

public final class AtbashCipher {

    private static final int LOWER_OFFSET = 97;
    private static final int MIN_LOWER_VALUE = 97;
    private static final int MAX_LOWER_VALUE = 122;
    private static final int HIGH_OFFSET = 65;
    private static final int MIN_HIGH_VALUE = 65;
    private static final int MAX_HIGH_VALUE = 90;
    private static final int LENGHT_OF_LATIN_ALPHABET = 25;

    private AtbashCipher() {

    }

    public static String atbash(String word) {

        Objects.requireNonNull(word);

        char[] newWord = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char symbol = word.charAt(i);

            int currentOffset = getOffset(symbol);

            if (currentOffset != 0) {
                char newSymbol = (char) ((LENGHT_OF_LATIN_ALPHABET - ((int) symbol - currentOffset)) + currentOffset);

                newWord[i] = newSymbol;
            }

        }
        return new String(newWord);
    }

    private static int getOffset(char symbol) {
        int currentOffset = 0;
        if (symbol <= MAX_LOWER_VALUE && symbol >= MIN_LOWER_VALUE) {
            currentOffset = LOWER_OFFSET;
        } else if (symbol >= MIN_HIGH_VALUE && symbol <= MAX_HIGH_VALUE) {
            currentOffset = HIGH_OFFSET;
        }
        return currentOffset;
    }
}
