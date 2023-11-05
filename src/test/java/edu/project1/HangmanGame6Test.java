package edu.project1;

import edu.project.project1.ConsoleHangman;
import edu.project.project1.Dictionary;
import edu.project.project1.GameState;
import edu.project.project1.MyDictionaryImpl;
import edu.project.project1.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HangmanGame6Test {
    private static ConsoleHangman game;
    private static Session session;

    @BeforeAll
    public static void before() {
        Dictionary dictionary = new MyDictionaryImpl("abradcadabra");
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
                    "c, 0, IS_PLAYING, *****c******",
                    "e, 1, IS_PLAYING, *****c******",
                    "a, 1, IS_PLAYING, a**a*ca*a**a",
                    "a, 1, IS_PLAYING, a**a*ca*a**a",
                    "a, 1, IS_PLAYING, a**a*ca*a**a",
                    "b, 1, IS_PLAYING, ab*a*ca*ab*a",
                    "f, 2, IS_PLAYING, ab*a*ca*ab*a",
                    "d, 2, IS_PLAYING, ab*adcadab*a",
                    "r, 2, GAME_OVER, abradcadabra",
            }
    )
    public void testThatGameReturnWin(String inputChar,
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
