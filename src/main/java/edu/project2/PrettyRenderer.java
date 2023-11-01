package edu.project2;

import java.util.List;

public class PrettyRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        Cell[][] grid = maze.getGrid();
        StringBuilder stringBuilder = new StringBuilder();

        char wall = '\u2591';
        char passage = '\u2593';
        stringBuilder.append("wall - " + wall+"\n");
        stringBuilder.append("passage - " + passage+"\n\n");

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
        Cell[][] grid = maze.getGrid();
        StringBuilder stringBuilder = new StringBuilder();

        char wall = '\u2591';
        char passage = '\u2593';
        char pathMarker = '*';

        stringBuilder.append("wall - " + wall + "\n");
        stringBuilder.append("passage - " + passage + "\n\n");

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell currentCell = grid[i][j];
                Coordinate currentCoordinate = new Coordinate(i, j);


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
}
