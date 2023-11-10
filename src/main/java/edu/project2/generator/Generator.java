package edu.project2.generator;

import edu.project2.maze_sekelet.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
