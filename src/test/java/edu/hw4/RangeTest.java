package edu.hw4;

import edu.homework.hw4.Range;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class RangeTest {
    @Test
    public void rangeReturnException() {

//        Arrange


//        Act
        assertThrows(IllegalArgumentException.class, () -> {
            var a = new Range(5, 4);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            var a = new Range(-1, -10);

        });

        assertThrows(IllegalArgumentException.class, () -> {
            var a = new Range(-1, -5);

        });
        Range range = new Range(5, 9);

        assertThrows(IllegalArgumentException.class, () -> {
            range.setMax(4);

        });
        assertThrows(IllegalArgumentException.class, () -> {
            range.setMax(-5);

        });
        assertThrows(IllegalArgumentException.class, () -> {
            range.setMin(12);

        });
        assertThrows(IllegalArgumentException.class, () -> {
            range.setMin(-5);

        });
    }
}
