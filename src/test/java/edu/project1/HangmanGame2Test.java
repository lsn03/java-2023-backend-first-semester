package edu.project1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HangmanGame2Test {
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
                    "ca, 0, IS_PLAYING, *******",
                    "ta, 0, IS_PLAYING, *******",
                    "fa, 0, IS_PLAYING, *******",
                    "aa, 0, IS_PLAYING, *******",
                    "ad, 0, IS_PLAYING, *******",
                    "ag, 0, IS_PLAYING, *******",
            }
    )
    public void testThatLongCharWillNotChangeGame(String inputChar,
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
