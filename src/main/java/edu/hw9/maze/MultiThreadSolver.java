package edu.hw9.maze;

import edu.project2.maze_sekelet.Coordinate;
import edu.project2.maze_sekelet.Maze;
import edu.project2.solver.BFSSolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MultiThreadSolver extends BFSSolver {
    private ExecutorService executorService;

    public MultiThreadSolver(Maze maze, Integer threads) {
        super(maze);
        validateThreads(threads);
        executorService = Executors.newFixedThreadPool(threads);
    }

    public void close() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Coordinate> solve(Coordinate start, Coordinate end) {
        initializer();

        int startRow = start.row();
        int startCol = start.col();

        int endRow = end.row();
        int endCol = end.col();

        queue.add(new Coordinate(startRow, startCol));
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            List<Future<Void>> futures = new ArrayList<>();

            while (!queue.isEmpty()) {
                Coordinate current = queue.poll();
                int currentRow = current.row();
                int currentCol = current.col();

                if (currentRow == endRow && currentCol == endCol) {
                    List<Coordinate> path = reconstructPath(startRow, startCol, currentRow, currentCol);
                    executorService.shutdown();
                    return path;
                }

                futures.add((Future<Void>) executorService.submit(() -> checkNeighbors(currentRow, currentCol)));
            }


            try {
                for (Future<Void> future : futures) {
                    future.get();
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }


        return List.of();


    }

    @SuppressWarnings("ParameterAssignment")
    private List<Coordinate> reconstructPath(int startRow, int startCol, int currentRow, int currentCol) {
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


    private void validateThreads(Integer threads) {
        Objects.requireNonNull(threads);
        if (threads < 0 || threads > Runtime.getRuntime().availableProcessors()) {
            throw new IllegalArgumentException();
        }
    }
}
