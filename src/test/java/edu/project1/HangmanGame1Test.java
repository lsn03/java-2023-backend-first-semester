package edu.project1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class HangmanGame1Test {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

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
                    "c, 1, IS_PLAYING, *******",
                    "t, 1, IS_PLAYING, t******",
                    "f, 1, IS_PLAYING, t****ff",
                    "a, 2, IS_PLAYING, t****ff",
                    "a, 3, GAME_OVER, t****ff",
                    //"a, 2, IS_PLAYING, t****ff",
            }
    )
    public void testThatEverythingChangesSuccessful(String inputChar,
                                                    int exceptedAttempts,
                                                    String exceptedGameState,
                                                    String exceptedUserAnswer) {
//        Arrange

//        Act
        game.setCharacter(inputChar);
//        Assert
        Session gameSession = game.getSession();
        assertEquals(exceptedAttempts, gameSession.getAttempts());
        assertEquals(exceptedGameState, gameSession.getGameState());
        assertEquals(exceptedUserAnswer, gameSession.getUserAnswer());

    }






}
