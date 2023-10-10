package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class MyDictionaryImpl implements Dictionary {

    String[] words;

    private void stringValidator(String[] words) {
        for (String str : words) {
            if (str.isBlank()) {
                throw new IllegalArgumentException("The word can't be Empty");
            }
        }
    }

    public MyDictionaryImpl() {
        words = new String[]{"tinkoff", "education", "fefu"};
    }

    public MyDictionaryImpl(String[] words) {
        stringValidator(words);
        this.words = words;
    }

    public MyDictionaryImpl(String word) {
        stringValidator(new String[]{word});
        words = new String[]{word};
    }

    @Override
    public @NotNull String randomWord() {
        int randomIndex = new Random().nextInt(words.length);
        return words[randomIndex];
    }
}
