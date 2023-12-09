package edu.hw9.solver;

import edu.hw9.maze.MultiThreadSolver;
import edu.project2.generator.Generator;
import edu.project2.generator.GrowingTreeGenerator;
import edu.project2.maze_sekelet.Cell;
import edu.project2.maze_sekelet.Coordinate;
import edu.project2.maze_sekelet.Maze;
import edu.project2.renderer.PrettyRenderer;
import edu.project2.renderer.Renderer;
import edu.project2.solver.Solver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Random;

public class MazeMultiSolverTest {
    int seed = 5;
    Random random = new Random(seed);
    int height = 10;
    int width = 15;
    Generator generator = new GrowingTreeGenerator(random);
    Renderer renderer = new PrettyRenderer();
    Solver solver;
    Maze maze = generator.generate(height, width);
    Coordinate start;
    Coordinate end;
    int threads = 4;


    @Test
    public void failed() {
        assertThrows(NullPointerException.class, () -> {
            new MultiThreadSolver(null, null);
        });
        assertThrows(NullPointerException.class, () -> {
            new MultiThreadSolver(new Maze(0, 0, new Cell[][]{}), null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadSolver(new Maze(0, 0, new Cell[][]{}), -5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadSolver(new Maze(0, 0, new Cell[][]{}), 500);
        });
    }

    @Test
    public void pathNotFound() {
        start = new Coordinate(0, 0);
        end = new Coordinate(8, 12);


        solver = new MultiThreadSolver(maze, threads);
        var res = solver.solve(start, end);

        assertTrue(res.isEmpty());

    }


    @Test
    public void pathFound() {
        start = new Coordinate(1, 1);
        end = new Coordinate(3, 4);

        solver = new MultiThreadSolver(maze, threads);

        var res = solver.solve(start, end);

        assertFalse(res.isEmpty());

        List<Coordinate> expected = List.of(
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(3, 1),
                new Coordinate(3, 2),
                new Coordinate(3, 3),
                new Coordinate(3, 4)
        );
        assertEquals(expected, res);
    }


}
