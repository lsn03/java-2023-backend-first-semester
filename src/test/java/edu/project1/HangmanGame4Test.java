package edu.project1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HangmanGame4Test {
    private static ConsoleHangman game;
    private static Session session;

    @BeforeAll
    public static void before() {
        Dictionary dictionary = new MyDictionaryImpl("tinkoff");
        session = new Session(dictionary.randomWord());
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
                    "c, 1, IS_PLAYING, *******",
                    "e, 2, IS_PLAYING, *******",
                    "a, 3, GAME_OVER, *******",
                    "a, 3, GAME_OVER, *******",
                    "a, 3, GAME_OVER, *******",
            }
    )
    public void testThatGameReturnLost(String inputChar,
                                       int exceptedAttempts,
                                       GameState exceptedGameState,
                                       String exceptedUserAnswer) {
//        Arrange

//        Act
        game.setCharacter(inputChar);
//        Assert

        assertEquals(exceptedAttempts, session.getAttempts());
        assertEquals(exceptedGameState, session.getGameState());
        assertEquals(exceptedUserAnswer, session.getUserAnswer());

    }
}
