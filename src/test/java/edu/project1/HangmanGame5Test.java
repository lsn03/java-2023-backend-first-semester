package edu.project1;

import edu.project.project1.MyDictionaryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HangmanGame5Test {
    @Test
    public void testThatThrowIlligalArgumentException() {
//        Arrange


//        Act && Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new MyDictionaryImpl("abra cadabra");
        });


    }
}
