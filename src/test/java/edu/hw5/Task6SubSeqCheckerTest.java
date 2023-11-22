package edu.hw5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task6SubSeqCheckerTest {
    @ParameterizedTest
    @CsvSource(value = {
            "abc, qasdfghjkl;abcfghjkl",
            "abc, zaqwedxcderabfvbtyabuhjabcnmjiklav",
            "gpa, qazxswerfcftyugpahbvghuiop;lmnbvcvbnm"
    })
    public void successfullySolved(String source, String target) {
        assertTrue(Task6SubSeqChecker.isSubsequence(source, target));
    }
    @ParameterizedTest
    @CsvSource(value = {
            "abkc, qasdfghjkl;abcfghjkl",
            "abac, zaqwedxcderabfvbtyabuhjabcnmjiklav",
            "qweer, qazxswerfcftyugpahbvghuiop;lmnbvcvbnm",
            "qweer, ds"
    })
    public void unSuccessfullySolved(String source, String target) {
        assertFalse(Task6SubSeqChecker.isSubsequence(source, target));
    }

    @Test
    public void negativeTest(){
        assertThrows(NullPointerException.class,() -> {
           Task6SubSeqChecker.isSubsequence(null,null);
        });

        assertThrows(NullPointerException.class,() -> {
            Task6SubSeqChecker.isSubsequence("ds",null);
        });

        assertThrows(NullPointerException.class,() -> {
            Task6SubSeqChecker.isSubsequence(null,"ds");
        });

        assertThrows(IllegalArgumentException.class,() -> {
            Task6SubSeqChecker.isSubsequence(" ","null");
        });

        assertThrows(IllegalArgumentException.class,() -> {
            Task6SubSeqChecker.isSubsequence(" "," ");
        });
        assertThrows(IllegalArgumentException.class,() -> {
            Task6SubSeqChecker.isSubsequence("","");
        });
        assertThrows(IllegalArgumentException.class,() -> {
            Task6SubSeqChecker.isSubsequence("null"," ");
        });
    }
}
