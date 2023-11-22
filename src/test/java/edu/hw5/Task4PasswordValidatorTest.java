package edu.hw5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task4PasswordValidatorTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1234~",
            "1!234",
            "1@34",
            "12#34",
            "123$4",
            "123%4",
            "12^34",
            "12&34",
            "1|234",
            "1|23$%&^4",
            "1|2@#$34",

    })
    public void successfulSolvedPassword(String rawPassword){
        assertTrue(Task4PasswordValidator.solve(rawPassword));
    }
    @ParameterizedTest
    @CsvSource(value = {
            "1234",
            "dsSAsaAS",
            "dsSAs23aA5S",
    })
    public void unSuccessfulSolvedPassword(String rawPassword){
        assertFalse(Task4PasswordValidator.solve(rawPassword));
    }

    @Test
    public void negativeTests(){
        assertThrows(NullPointerException.class,() -> {
            Task4PasswordValidator.solve(null);
        });

        assertThrows(IllegalArgumentException.class,() -> {
            Task4PasswordValidator.solve("");
        });
        assertThrows(IllegalArgumentException.class,() -> {
            Task4PasswordValidator.solve("    ");
        });
    }

}
