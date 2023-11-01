package edu.project2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSSolver implements Solver {

    private int height;
    private int width;
    private Cell[][] grid;
    private boolean[][] visited;
    private Queue<Coordinate> queue;
    private int[][] parentRow;
    private int[][] parentCol;
    private int[] dr;
    private int[] dc;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        height = maze.getHeight();
        width = maze.getWidth();
        grid = maze.getGrid();

        visited = new boolean[height][width];
        queue = new LinkedList<>();
        parentRow = new int[height][width];
        parentCol = new int[height][width];

        int startRow = start.row();
        int startCol = start.col();
        int endRow = end.row();
        int endCol = end.col();

        queue.add(new Coordinate(startRow, startCol));
        visited[startRow][startCol] = true;

        dr = new int[]{-1, 1, 0, 0};
        dc = new int[]{0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            int currentRow = current.row();
            int currentCol = current.col();

            if (currentRow == endRow && currentCol == endCol) {

                List<Coordinate> path = new ArrayList<>();
                while (currentRow != startRow || currentCol != startCol) {

                    path.add(0, new Coordinate(currentRow, currentCol));

                    int nextRow = parentRow[currentRow][currentCol];
                    int nextCol = parentCol[currentRow][currentCol];

                    currentRow = nextRow;
                    currentCol = nextCol;
                }

                path.add(0, new Coordinate(startRow, startCol));

                return path;
            }

            checkNeighbors(currentRow, currentCol);

        }


        return new ArrayList<>();
    }

    private void checkNeighbors(int currentRow, int currentCol) {
        for (int i = 0; i < 4; i++) {
            int newRow = currentRow + dr[i];
            int newCol = currentCol + dc[i];

            if ( checkThatCellInTHeMaze(newRow,newCol)
                    && !visited[newRow][newCol]
                    && checkThatCellIsPassage(newRow,newCol)
            ) {

                queue.add(new Coordinate(newRow, newCol));
                visited[newRow][newCol] = true;

                parentRow[newRow][newCol] = currentRow;
                parentCol[newRow][newCol] = currentCol;
            }
        }
    }
    private boolean  checkThatCellInTHeMaze(int newRow,int newCol){
        return newRow >= 0 && newRow < height && newCol >= 0 && newCol < width;
    }
    private boolean checkThatCellIsPassage(int newRow,int newCol){
        return grid[newRow][newCol].type() == Cell.Type.PASSAGE;
    }
}