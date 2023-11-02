package edu.project2.renderer;

import edu.project2.maze_sekelet.Cell;
import edu.project2.maze_sekelet.Coordinate;
import edu.project2.maze_sekelet.Maze;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("MultipleStringLiterals")
public class PrettyRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        validate(maze);
        Cell[][] grid = maze.getGrid();
        StringBuilder stringBuilder = new StringBuilder();

        char wall = '\u2591';
        char passage = '\u2593';
        stringBuilder.append("wall - ").append(wall).append("\n");
        stringBuilder.append("passage - ").append(passage).append("\n\n");

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell currentCell = grid[i][j];
                if (currentCell.type() == Cell.Type.WALL) {
                    stringBuilder.append(wall); // ░ - wall

                } else {
                    stringBuilder.append(passage); // ▓  - passage
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        validate(maze, path);
        Cell[][] grid = maze.getGrid();
        StringBuilder stringBuilder = new StringBuilder();

        char wall = '\u2591';
        char passage = '\u2593';
        char pathMarker = '*';
//        char start = '\u25CB';
//        char end = '\u25D0';
        stringBuilder.append("wall - ").append(wall).append("\n");
        stringBuilder.append("passage - ").append(passage).append("\n\n");

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell currentCell = grid[i][j];
                Coordinate currentCoordinate = new Coordinate(i, j);
//
//                if (!path.isEmpty() && currentCoordinate.equals(path.get(0))) {
//                    stringBuilder.append(start);
//                }
//
//                else if (!path.isEmpty() && currentCoordinate.equals(path.get(path.size() - 1))) {
//                    stringBuilder.append(end);
//                }else
                if (path.contains(currentCoordinate)) {
                    stringBuilder.append(pathMarker);
                } else if (currentCell.type() == Cell.Type.WALL) {
                    stringBuilder.append(wall); // ░ - wall
                } else {
                    stringBuilder.append(passage); // ▓  - passage
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private void validate(Maze maze) {
        Objects.requireNonNull(maze);
        Objects.requireNonNull(maze.getGrid());

    }

    private void validate(Maze maze, List<Coordinate> list) {
        Objects.requireNonNull(list);
        if (list.contains(null)) {
            throw new NullPointerException();
        }
        validate(maze);
    }
}
