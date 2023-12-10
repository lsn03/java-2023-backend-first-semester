package edu.hw9.tree;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;

public class DirectorySearch extends RecursiveTask<List<Path>> {
    private final Path directory;
    private final Integer amount;

    public DirectorySearch(Path directory, Integer amount) {
        validate(directory, amount);
        this.directory = directory;
        this.amount = amount;
    }


    public List<Path> launch() {
        return compute();
    }

    @Override
    protected List<Path> compute() {
        List<DirectorySearch> subtasks = new ArrayList<>();
        int fileCount = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path path : stream) {
                if (Files.isDirectory(path)) {
                    DirectorySearch subtask = new DirectorySearch(path, amount);
                    subtask.fork();
                    subtasks.add(subtask);
                } else {
                    fileCount++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> result = new ArrayList<>();
        if (fileCount >= amount) {
            result.add(directory);
        }

        for (var task : subtasks) {
            result.addAll(task.join());
        }

        return result;
    }

    private void validate(Path directory, Integer amount) {
        Objects.requireNonNull(directory);
        Objects.requireNonNull(amount);
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        if (directory.toString().isEmpty() || directory.toString().isBlank()) {
            throw new IllegalArgumentException();
        }
    }

}
