package edu.hw3;

import edu.homework.hw3.Clasterization;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Arrays;
import java.util.List;

public class ClasterizationTest {
    @ParameterizedTest
    @CsvSource(value = {
            "()()(), '(),(),()'",
            "((())), '((()))'",
            "((()))(())()()(()()), '((())),(()),(),(),(()())'",
            "((())())(()(()())), '((())()),(()(()()))'"
    })
    public void testThatWork(String word, String expectedResult) {
        Clasterization clasterization = new Clasterization();
        List<String> res = clasterization.clusterize(word);
        List<String> expected = Arrays.asList(expectedResult.split(","));
        assertEquals(expected, res);
    }
}
