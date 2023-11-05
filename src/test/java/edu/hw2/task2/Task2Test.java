package edu.hw2.task2;

import edu.homework.hw2.Task2.Rectangle;
import edu.homework.hw2.Task2.Shape;
import edu.homework.hw2.Task2.Square;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
                Arguments.of(new Rectangle(20,5)),
                Arguments.of(new Square(10))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Shape rect) {
        Square square = new Square(20);
        square = square.setHeight(10);
        Shape shape2 = square;
        Shape shape = square.setWidth(10);
        assertThat(rect.area()).isEqualTo(100.0);
        assertThat(shape.area()).isEqualTo(100.0);
        assertThat(shape2.area()).isEqualTo(100.0);
    }
}
