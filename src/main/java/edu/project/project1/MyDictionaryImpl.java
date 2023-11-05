package edu.project.project1;

import java.util.Objects;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class MyDictionaryImpl implements Dictionary {

    private final String[] words;
    private final Random random;

    public MyDictionaryImpl(String[] words) {
        stringValidator(words);
        random = new Random();
        this.words = words;
    }

    public MyDictionaryImpl() {
        this(new String[]{"tinkoff", "education", "fefu"});
    }

    public MyDictionaryImpl(String word) {
        this(new String[]{word});
    }

    @Override
    public @NotNull String randomWord() {
        int randomIndex = random.nextInt(words.length);
        return words[randomIndex];
    }

    private void stringValidator(String[] words) {
        Objects.requireNonNull(words);
        for (String str : words) {
            Objects.requireNonNull(str);
            if (str.isBlank()) {
                throw new IllegalArgumentException("The word can't be Empty");
            } else if (!str.matches("\\w+")) {
                throw new IllegalArgumentException("The word can't contain non latin character");
            }
        }
    }

}
