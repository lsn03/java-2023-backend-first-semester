package edu.hw5;

import edu.homework.hw5.Task5CarSignPattern;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CarSignPatternTest {

    @ParameterizedTest
    @CsvSource(value = {
            "А123ВЕ777",
            "О777ОО177",
            "А123ВЕ77",
    })
    public void validSign(String rawString){
        assertTrue(Task5CarSignPattern.isSignValid(rawString));
    }
    @ParameterizedTest
    @CsvSource(value = {
            "А123ВГ777",
            "О777ОД177",
            "А123ВЕ7",
            "А123ВЕ7889",
            "АА123В78",
            "А12345ВА78",
    })
    public void notValidSign(String rawString){
        assertFalse(Task5CarSignPattern.isSignValid(rawString));
    }

    @Test
    public void nullChecker(){
        assertThrows(NullPointerException.class, () -> {
            Task5CarSignPattern.isSignValid(null);
        });
    }
    @Test
    public void emptyBlankChecker(){
        assertThrows(IllegalArgumentException.class, () -> {
            Task5CarSignPattern.isSignValid("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Task5CarSignPattern.isSignValid("     ");
        });
    }
}
