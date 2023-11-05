package edu.project2.solver;

import edu.project2.maze_sekelet.Cell;
import edu.project2.maze_sekelet.Coordinate;
import edu.project2.maze_sekelet.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFSSolver implements Solver {
    private Maze maze;
    private Cell[][] grid;
    private int height;
    private int width;

    private DFSSolver() {

        grid = null;
        height = Integer.MIN_VALUE;
        width = Integer.MIN_VALUE;
    }

    public DFSSolver(Maze maze) {
        setMaze(maze);
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
        height = maze.getHeight();
        width = maze.getWidth();
        grid = maze.getGrid();
    }

    @Override
    public List<Coordinate> solve(Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();

        boolean[][] visited = new boolean[height][width];

        if (dfs(start, end, visited, path)) {
            Collections.reverse(path);
            return path;
        }

        return List.of();
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
