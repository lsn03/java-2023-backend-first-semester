package edu.project2.solver;

import edu.project2.maze_sekelet.Cell;
import edu.project2.maze_sekelet.Coordinate;
import edu.project2.maze_sekelet.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFSSolver implements Solver {
    Cell[][] grid;
    int height;
    int width;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();


        height = maze.getHeight();
        width = maze.getWidth();
        grid = maze.getGrid();

        boolean[][] visited = new boolean[height][width];

        if (dfs(start, end, visited, path)) {
            Collections.reverse(path);
            return path;
        }

        return new ArrayList<>();
    }

    private boolean dfs(Coordinate current, Coordinate end, boolean[][] visited, List<Coordinate> path) {
        int row = current.row();
        int col = current.col();


        if (checkThatCellNotInTheMaze(row, col)
                || visited[row][col] || grid[row][col].type() == Cell.Type.WALL) {
            return false;
        }


        if (current.equals(end)) {
            path.add(current);
            return true;
        }


        visited[row][col] = true;


        if (dfs(new Coordinate(row - 1, col), end, visited, path)
                || dfs(new Coordinate(row + 1, col), end, visited, path)
                || dfs(new Coordinate(row, col - 1), end, visited, path)
                || dfs(new Coordinate(row, col + 1), end, visited, path)) {
            path.add(current);
            return true;
        }

        return false;
    }

    private boolean checkThatCellNotInTheMaze(int row, int col) {
        return row < 0 || row >= height || col < 0 || col >= width;
    }
}
