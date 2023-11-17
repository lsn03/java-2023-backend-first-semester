package edu.hw6;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CloneFileTest {
    @Test
    public void createFile() throws IOException {
        Path path = Path.of("src/test/resources/hw6/clonefile/tin.txt");
        CloneFile.cloneFile(path);
        path = Path.of("src/test/resources/hw6/clonefile/tin - копия.txt");
        assertTrue(Files.exists(path));
        Files.delete(path);
    }

    @Test
    public void createFileTwice() throws IOException {
        Path path = Path.of("src/test/resources/hw6/clonefile/tin.txt");

        CloneFile.cloneFile(path);
        CloneFile.cloneFile(path);
        CloneFile.cloneFile(path);

        path = Path.of("src/test/resources/hw6/clonefile/tin - копия.txt");
        assertTrue(Files.exists(path));
        Files.delete(path);

        path = Path.of("src/test/resources/hw6/clonefile/tin - копия(2).txt");
        assertTrue(Files.exists(path));
        Files.delete(path);

        path = Path.of("src/test/resources/hw6/clonefile/tin - копия(3).txt");
        assertTrue(Files.exists(path));
        Files.delete(path);
    }

    @Test
    public void createFileTwiceAndDelete() throws IOException {
        Path sourcePath = Path.of("src/test/resources/hw6/clonefile/tin.txt");

        CloneFile.cloneFile(sourcePath);
        CloneFile.cloneFile(sourcePath);


        Path path2 = Path.of("src/test/resources/hw6/clonefile/tin - копия.txt");
        sourcePath = path2;
        assertTrue(Files.exists(sourcePath));
        Files.delete(sourcePath);

        Path path1 = Path.of("src/test/resources/hw6/clonefile/tin - копия(2).txt");

        assertTrue(Files.exists(path1));


        CloneFile.cloneFile(Path.of("src/test/resources/hw6/clonefile/tin.txt"));

        assertTrue(Files.exists(sourcePath));
        Files.delete(path1);
        Files.delete(path2);
    }
}
