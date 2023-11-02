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
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Generator generator = new GrowingTreeGenerator(random);
        Maze maze = generator.generate(10, 10);
        Renderer renderer = new PrettyRenderer();
        System.out.println((renderer.render(maze)));
        Solver solver = new DFSSolver();

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(maze.getHeight() - 3, maze.getWidth() - 2);

        List<Coordinate> path = solver.solve(maze, start, end);

        System.out.println((renderer.render(maze, path)));
        solver = new BFSSolver();
        System.out.println(renderer.render(maze, solver.solve(maze, end, start)));
//        Renderer renderer = new PrettyRenderer();
//
//        CreateMazeFormFile file = new CreateMazeFormFile();
//        Maze maze =  file.create("");
//
//        System.out.println( renderer.render(maze));;
//        Solver solver = new BFSSolver();
//        Coordinate start = new Coordinate(0, 0);
//        Coordinate end = new Coordinate(maze.getHeight() - 2, maze.getWidth() - 2);
//        List<Coordinate> path = solver.solve(maze, start, end);
//
//        System.out.println((renderer.render(maze, path)));

    }

}
