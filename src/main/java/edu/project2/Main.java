package edu.project2;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random(7);
        Generator generator = new GrowingTreeGenerator(random);
        Maze maze = generator.generate(15, 20);
        Renderer renderer = new PrettyRenderer();
        System.out.println((renderer.render(maze)));
        Solver solver = new BFSSolver();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(maze.getHeight() - 2, maze.getWidth() - 2);
        List<Coordinate> path = solver.solve(maze, start, end);

        System.out.println((renderer.render(maze, path)));

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
