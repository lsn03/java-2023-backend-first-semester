package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public final class OutputComposition {

    private OutputComposition() {
    }

    public static void solve(String filePath) {
        Objects.requireNonNull(filePath);
        Path path = Path.of(filePath);
        solve(path);
    }

    public static void solve(Path filePath) {
        Objects.requireNonNull(filePath);
        try (
                OutputStream fileOutputStream = Files.newOutputStream(filePath);

                CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new CRC32());

                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");

                PrintWriter printWriter = new PrintWriter(outputStreamWriter)
        ) {

            printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
