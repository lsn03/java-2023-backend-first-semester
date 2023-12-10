package edu.hw9.tree;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FileSearch extends RecursiveTask<List<Path>> {

    private final Path directory;
    private final String extension;
    private final long size;

    public FileSearch(Path directory, String extension, long size) {

        this.directory = directory;
        this.extension = extension;
        this.size = size;

    }

    public List<Path> launch() {
        return compute();
    }

    @Override
    protected List<Path> compute() {
        List<FileSearch> subtasks = new ArrayList<>();
        List<Path> answer = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (var path : stream) {
                if (Files.isDirectory(path)) {
                    var subtask = new FileSearch(path, extension, size);
                    subtask.fork();
                    subtasks.add(subtask);
                } else {
                    if (path.toString().endsWith(extension) && Files.size(path) > size) {
                        answer.add(path);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (FileSearch subtask : subtasks) {
            answer.addAll(subtask.join());
        }
        return answer;
    }


}
