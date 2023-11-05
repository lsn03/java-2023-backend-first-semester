package edu.project.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static void main(String[] args) {

        LOGGER.info("Do you want to add word or words?");
        LOGGER.info("Press \"1\" for word and \"2\" for words or \"0\" for load with default settings");


        Scanner scanner = new Scanner(System.in);
        int countOfWords = scanner.nextInt();

        Dictionary dictionary;
        String word;
        Session session;
        ConsoleHangman hangman;
        if (countOfWords == 0) {

            dictionary = new MyDictionaryImpl();

            word = dictionary.randomWord();

            session = new Session(word, word.length() / 2);

            hangman = new ConsoleHangman(session);

            hangman.run();

        } else if (countOfWords == 1) {
            LOGGER.info("Enter a word:");
            word = scanner.next();
            dictionary = new MyDictionaryImpl(word);
            LOGGER.info("Enter a Attempts:");
            int maxAttemps = scanner.nextInt();
            while (!(maxAttemps < word.length() && maxAttemps >= 0)) {
                LOGGER.info("The value should be in range [0; word.lenght())");
                LOGGER.info("Enter a Attempts:");
                maxAttemps = scanner.nextInt();
            }

            session = new Session(word, maxAttemps);

            hangman = new ConsoleHangman(session);

            hangman.run();

        } else if (countOfWords == 2) {
            LOGGER.info("Enter a words where \" \" is separator. Example: world,fefu");
            String[] words = scanner.nextLine().split(" ");
            dictionary = new MyDictionaryImpl(words);
            word = dictionary.randomWord();
            LOGGER.info("Enter a Attempts:");
            int maxAttemps = scanner.nextInt();
            while (!(maxAttemps < word.length() && maxAttemps >= 0)) {
                LOGGER.info("The value should be in range [0; " + word.length() + ")");
                LOGGER.info("Enter a Attempts:");
                maxAttemps = scanner.nextInt();
            }
            session = new Session(word, maxAttemps);

            hangman = new ConsoleHangman(session);

            hangman.run();
        }

        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
//        LOGGER.info("Hello and welcome!");
//
//        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
//        for (int i = 0; i <= 2; i++) {
//
//            // Press Shift+F9 to start debugging your code. We have set one breakpoint
//            // for you, but you can always add more by pressing Ctrl+F8.
//            LOGGER.info("i = {}", i);
//        }
    }
}
