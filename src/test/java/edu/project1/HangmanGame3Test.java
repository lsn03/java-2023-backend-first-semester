package edu.project1;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HangmanGame3Test {
    private static ConsoleHangman game;

    @BeforeAll
    public static void before() {
        Dictionary dictionary = new MyDictionaryImpl("tinkoff");
        Session session = new Session(dictionary.randomWord());
        game = new ConsoleHangman(session);
    }
    @ParameterizedTest(name =
            "Test # {index}: " +
                    "inputChar = {0}, " +
                    "attempts = {1}, " +
                    "gameState = {2}, " +
                    "userAnswer = {3} ")
    @CsvSource(
            value = {
                    ", 0, IS_PLAYING, *******",
                    ", 0, IS_PLAYING, *******",
                    ", 0, IS_PLAYING, *******",
                    ", 0, IS_PLAYING, *******",
                    ", 0, IS_PLAYING, *******",
                    ", 0, IS_PLAYING, *******",
            }
    )
    public void testThatNullCharCatchNullException(String inputChar) {
//        Arrange
        before();
//        Act && Assert
        assertThrows(NullPointerException.class, () -> {
                    game.setCharacter(inputChar);
                }
        );
//


    }
}
