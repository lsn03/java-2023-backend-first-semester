package edu.project2.renderer;

import edu.project2.maze_sekelet.Cell;
import edu.project2.maze_sekelet.Coordinate;
import edu.project2.maze_sekelet.Maze;
import java.util.List;
import java.util.Objects;


public class PrettyRenderer implements Renderer {


    private final char pathMarker = '*';
    private final String newLine = System.lineSeparator();

    private StringBuilder stringBuilder;

    @Override
    public String render(Maze maze) {
        validate(maze);
        Cell[][] grid = maze.getGrid();
        stringBuilder = new StringBuilder();

        renderWallPassageMessage();


        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell currentCell = grid[i][j];
                stringBuilder.append(currentCell.getCellChar());

            }
            stringBuilder.append(newLine);
        }
        return stringBuilder.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        validate(maze, path);
        Cell[][] grid = maze.getGrid();
        stringBuilder = new StringBuilder();


        renderWallPassageMessage();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell currentCell = grid[i][j];
                Coordinate currentCoordinate = new Coordinate(i, j);

                if (path.contains(currentCoordinate)) {
                    stringBuilder.append(pathMarker);
                } else {
                    stringBuilder.append(currentCell.getCellChar());
                }
            }
            stringBuilder.append(newLine);
        }
        return stringBuilder.toString();
    }

    private void renderWallPassageMessage() {
        stringBuilder.append("wall - ").append(Cell.WALL_CHAR).append(newLine);
        stringBuilder.append("passage - ").append(Cell.PASSAGE_CHAR).append(newLine).append(newLine);

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
