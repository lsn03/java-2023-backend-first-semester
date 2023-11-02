package edu.project2.generator;

import edu.project2.maze_sekelet.Cell;
import edu.project2.maze_sekelet.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class GrowingTreeGenerator implements Generator {
    private final Random random;
    private final static int MAX_HEIGHT = 100;
    private final static int MAX_WIDTH = 100;
    private final static int MIN_HEIGHT = 0;
    private final static int MIN_WIDTH = 0;

    public GrowingTreeGenerator(Random random) {
        this.random = random;
    }

    public GrowingTreeGenerator() {
        random = new Random();
    }

    @Override
    public Maze generate(int height, int width) {
        validateArgs(height, width);
        Cell[][] grid = initializeGrid(height, width);

        int startRow = random.nextInt(height);
        int startCol = random.nextInt(width);

        Cell startCell = grid[startRow][startCol];
        startCell = new Cell(startCell.row(), startCell.col(), Cell.Type.PASSAGE);


        List<Cell> cellList = new ArrayList<>();
        cellList.add(startCell);

        while (!cellList.isEmpty()) {
            int randomIndex = random.nextInt(cellList.size());
            Cell currentCell = cellList.get(randomIndex);


            List<Cell> neighbors = getUnvisitedNeighbors(currentCell, grid);


            if (neighbors.isEmpty()) {
                cellList.remove(randomIndex);
            } else {
                int neighborIndex = random.nextInt(neighbors.size());
                Cell neighborCell = neighbors.get(neighborIndex);

                removeWall(currentCell, neighborCell, grid);
                cellList.add(neighborCell);

            }
        }

        return new Maze(height, width, grid);
    }

    private Cell[][] initializeGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }
        return grid;
    }

    private void validateArgs(int height, int width) {
        if (height <= MIN_HEIGHT || width <= MIN_WIDTH || height > MAX_HEIGHT || width > MAX_WIDTH) {
            throw new IllegalArgumentException("Incorrect height and width arguments");
        }
    }

    private List<Cell> getUnvisitedNeighbors(Cell currentCell, Cell[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        List<Cell> neighbors = new ArrayList<>();
        if (currentCell.row() > 1) {
            Cell neighbor = grid[currentCell.row() - 2][currentCell.col()];
            if (grid[neighbor.row()][neighbor.col()].type() == Cell.Type.WALL) {
                neighbors.add(neighbor);
            }

        }
        if (currentCell.row() < height - 2) {
            Cell neighbor = grid[currentCell.row() + 2][currentCell.col()];
            if (grid[neighbor.row()][neighbor.col()].type() == Cell.Type.WALL) {
                neighbors.add(neighbor);
            }
        }


        if (currentCell.col() < width - 2) {
            Cell neighbor = grid[currentCell.row()][currentCell.col() + 2];
            if (grid[neighbor.row()][neighbor.col()].type() == Cell.Type.WALL) {
                neighbors.add(neighbor);
            }
        }


        if (currentCell.col() > 1) {
            Cell neighbor = grid[currentCell.row()][currentCell.col() - 2];
            if (grid[neighbor.row()][neighbor.col()].type() == Cell.Type.WALL) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    private void removeWall(Cell currentCell, Cell neighborCell, Cell[][] grid) {

        int neighborCellRow = neighborCell.row();
        int neighborCellCol = neighborCell.col();

        int currentCellRow = currentCell.row();
        int currentCellCol = currentCell.col();

        grid[neighborCellRow][neighborCellCol] = new Cell(neighborCellRow, neighborCellCol, Cell.Type.PASSAGE);
        grid[(currentCellRow + neighborCellRow) / 2][(currentCellCol + neighborCellCol) / 2] =
                new Cell((currentCellRow + neighborCellRow) / 2,
                        (currentCellCol + neighborCellCol) / 2,
                        Cell.Type.PASSAGE
                );


    }
}
