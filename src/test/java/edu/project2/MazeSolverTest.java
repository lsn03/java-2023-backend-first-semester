package edu.project2;

import edu.project2.generator.Generator;
import edu.project2.generator.GrowingTreeGenerator;
import edu.project2.maze_sekelet.Coordinate;
import edu.project2.maze_sekelet.Maze;
import edu.project2.renderer.PrettyRenderer;
import edu.project2.renderer.Renderer;
import edu.project2.solver.BFSSolver;
import edu.project2.solver.DFSSolver;
import edu.project2.solver.Solver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Random;

public class MazeSolverTest {
    int seed = 5;
    Random random = new Random(seed);
    int height = 10;
    int width = 15;
    Generator generator = new GrowingTreeGenerator(random);
    Renderer renderer = new PrettyRenderer();
    Solver solver;

    Coordinate start;
    Coordinate end;

    @Test
    public void pathNotFoundDFS() {
        start = new Coordinate(0, 0);
        end = new Coordinate(8, 12);
        solver = new DFSSolver();

        Maze maze = generator.generate(height, width);

        var res = solver.solve(maze, start, end);

        assertTrue(res.isEmpty());

    }

    @Test
    public void pathNotFoundBFS() {
        start = new Coordinate(0, 0);
        end = new Coordinate(8, 12);
        solver = new BFSSolver();

        Maze maze = generator.generate(height, width);

        var res = solver.solve(maze, start, end);

        assertTrue(res.isEmpty());

    }

    @Test
    public void pathFoundDFS() {
        start = new Coordinate(1, 1);
        end = new Coordinate(3, 4);
        solver = new DFSSolver();

        Maze maze = generator.generate(height, width);

        var res = solver.solve(maze, start, end);
        System.out.println(renderer.render(maze));
        System.out.println(renderer.render(maze, res));
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

    @Test
    public void pathFoundBFS() {
        start = new Coordinate(1, 1);
        end = new Coordinate(3, 4);
        solver = new BFSSolver();

        Maze maze = generator.generate(height, width);

        var res = solver.solve(maze, start, end);
        System.out.println(renderer.render(maze));
        System.out.println(renderer.render(maze, res));
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
