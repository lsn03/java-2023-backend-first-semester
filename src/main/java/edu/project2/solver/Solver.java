package edu.project2.solver;

import edu.project2.maze_sekelet.Coordinate;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Coordinate start, Coordinate end);
}
