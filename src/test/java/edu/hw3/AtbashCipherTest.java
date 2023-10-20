package edu.hw3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AtbashCipherTest {
    @ParameterizedTest
    @CsvSource(value = {
            "Hello world!, Svool dliow!",
            "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler, Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
    })
    public void testingAtbash(String word, String expectedWord) {
//        Arrange
        AtbashCipher cipher = new AtbashCipher();
//        Act
        String res = cipher.atbash(word);
//        Assert
        assertEquals(expectedWord, res);
    }
    @Test
    public void testThatNull() {
//        Arrange
        String word = null;
        AtbashCipher cipher = new AtbashCipher();
//        Act && Assert

        assertThrows(NullPointerException.class,()->{
            cipher.atbash(word);
        });

    }
    @Test
    public void testThatEmpty() {
//        Arrange
        String word = "";
        AtbashCipher cipher = new AtbashCipher();
//        Act
        String res = cipher.atbash(word);
//        Assert
        String expectedWord = "";
        assertEquals(expectedWord, res);

    }
}
