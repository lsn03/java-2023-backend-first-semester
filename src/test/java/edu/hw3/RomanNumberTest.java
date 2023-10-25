package edu.hw3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RomanNumberTest {
    @ParameterizedTest
    @CsvSource(value = {
            "158, CLVIII",
            "2583, MMDLXXXIII",
            "1245, MCCXLV",
            "3954, MMMCMLIV",
            "8, VIII",

    })
    public void TestThatCorrectConverter(int sourceValue,String expectedValue){
//        Arrange
        RomanNumber rn = new RomanNumber();
//        Act
        String res = rn.convertToRoman(sourceValue);
//        Assert
        assertEquals(expectedValue,res);
    }
    @ParameterizedTest
    @CsvSource(value = {
            "-1",
            "0",
            "-100",
            "4000",
            "5000"
    })
    public void testThatIlligalArgumentExeption(int sourceValue){
        //        Arrange
        RomanNumber rn = new RomanNumber();
//        Act && Assert
        assertThrows(IllegalArgumentException.class, ()->{
            rn.convertToRoman(sourceValue);
        });
    }
}
