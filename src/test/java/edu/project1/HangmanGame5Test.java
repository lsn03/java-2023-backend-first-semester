package edu.project1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
