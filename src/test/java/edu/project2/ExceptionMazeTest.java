package edu.project2;

import edu.project2.generator.Generator;
import edu.project2.generator.GrowingTreeGenerator;
import edu.project2.maze_sekelet.Cell;
import edu.project2.maze_sekelet.Coordinate;
import edu.project2.maze_sekelet.Maze;
import edu.project2.renderer.PrettyRenderer;
import edu.project2.renderer.Renderer;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.ArrayList;
import java.util.List;

public class ExceptionMazeTest {


    Generator generator = new GrowingTreeGenerator();
    Renderer renderer = new PrettyRenderer();

    @ParameterizedTest
    @CsvSource(value = {
            "0, 0",
            "-5, -5",
            "-5, 5",
            "5, -5",
            "1000, -5",
            "1000, 0",
            "10000, 10000",
            "0, 10000",
            "0, 20",
            "20, 0",
    })
    public void throwsIllegalArgumentExceptionAtGenerator(int height, int width) {

        assertThrows(IllegalArgumentException.class, () -> {
            generator.generate(height, width);
        });
    }

    @ParameterizedTest
    @CsvSource(value = {

            "-5, -5, WALL",
            "-5, 5, PASSAGE",
            "5, -5, PASSAGE",


    })
    public void throwsIllegalArgumentExceptionAtCell(int row, int col, Cell.Type type) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cell(row, col, type);
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "5, 5,",
            "1000, 5,",
    })
    public void throwsNullPointerExceptionAtCell(int row, int col, Cell.Type type) {
        assertThrows(NullPointerException.class, () -> {
            new Cell(row, col, type);
        });
    }

    @ParameterizedTest
    @CsvSource(value = {

            "-5, -5",
            "-5, 5",
            "5, -5",
            "1000, -5",

    })
    public void throwsIllegalArgumentExceptionAtCoordinate(int row, int col) {

        assertThrows(IllegalArgumentException.class, () -> {
            new Coordinate(row, col);
        });
    }

    @Test
    public void throwsNullPointerExceptionAtRendererPart1() {

        assertThrows(NullPointerException.class, () -> {
            Maze maze = null;
            renderer.render(maze);
        });
    }

    @Test
    public void throwsNullPointerExceptionAtRendererPart2() {

        assertThrows(NullPointerException.class, () -> {
            Maze maze = new Maze(10, 10, null);
            renderer.render(maze);
        });
    }

    @Test
    public void throwsNullPointerExceptionAtRendererPart3() {

        assertThrows(NullPointerException.class, () -> {
            Maze maze = new Maze(10, 10, new Cell[][]{});
            List<Coordinate> list = null;
            renderer.render(maze, list);
        });
    }

    @Test
    public void throwsNullPointerExceptionAtRendererPart4() {

        assertThrows(NullPointerException.class, () -> {
            Maze maze = new Maze(10, 10, new Cell[][]{});
            List<Coordinate> list = new ArrayList<>();
            list.add(null);
            renderer.render(maze, list);
        });
    }
}
