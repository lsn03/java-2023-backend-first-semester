package edu.project1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HangmanGame7Test {
    @Test
    public void testThatGiveUpCommandWork() {
//        Arrange
        Dictionary dictionary = new MyDictionaryImpl("abradcadabra");
        Session session = new Session(dictionary.randomWord());
        ConsoleHangman game = new ConsoleHangman(session);

        String expectedGameState = "GAME_OVER";
        int expectedAttempts = 0;
        String expectedUserAsnswer = "************";

//        Act
        game.setCharacter("-1");
        session = game.getSession();
//       Assert
        assertEquals(expectedGameState,session.getGameState());
        assertEquals(expectedAttempts,session.getAttempts());
        assertEquals(expectedUserAsnswer,session.getUserAnswer());

    }
}
