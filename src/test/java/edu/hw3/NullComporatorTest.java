package edu.hw3;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;

public class NullComporatorTest {
    @Test
    public void testThatNullCorrect(){
//        Arrange
        TreeMap<String, String> tree = new TreeMap<>(new NullComporator<>());
//        Act
        tree.put(null, "test");
//        Assert
        assertTrue(tree.containsKey(null));

    }
}
