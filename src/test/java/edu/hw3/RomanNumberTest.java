package edu.hw3;

import edu.homework.hw3.RomanNumber;
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
    public void TestThatCorrectConverter(int sourceValue, String expectedValue) {
//        Act
        String res = RomanNumber.convertToRoman(sourceValue);
//        Assert
        assertEquals(expectedValue, res);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-1",
            "0",
            "-100",
            "4000",
            "5000"
    })
    public void testThatIllegalArgumentException(int sourceValue) {

//        Act && Assert
        assertThrows(IllegalArgumentException.class, () -> {
            RomanNumber.convertToRoman(sourceValue);
        });
    }

    @Test
    public void testThatNullInteger() {
        Integer sourceValue = null;
        assertThrows(NullPointerException.class, () -> {
            RomanNumber.convertToRoman(sourceValue);
        });
    }
}
