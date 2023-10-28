package edu.hw3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIteratorTest {

    @Test
    public void testThatBackwardWorkForInteger() {
        List<Integer> list = Arrays.asList(3, 2, 1);
        var backwardIterator = new BackwardIterator<Integer>(list);

        int index = list.size() - 1;

        while (backwardIterator.hasNext()) {
            Integer elementExpected = list.get(index--);
            Integer elementActual = backwardIterator.next();
            assertEquals(elementExpected, elementActual);
        }
    }

    @Test
    public void testThatBackwardWorkForString() {
        List<String> list = Arrays.asList("ab", "beb", "kek", "Lol");
        var backwardIterator = new BackwardIterator<>(list);

        int index = list.size() - 1;

        while (backwardIterator.hasNext()) {
            var elementExpected = list.get(index--);
            var elementActual = backwardIterator.next();
            assertEquals(elementExpected, elementActual);
        }

    }

    @Test
    public void testThatBackwardThrowException() {
        List<String> list = List.of();
        var backwardIterator = new BackwardIterator<>(list);

        assertThrows(NoSuchElementException.class, () -> {
            backwardIterator.next();
        });


    }
}
