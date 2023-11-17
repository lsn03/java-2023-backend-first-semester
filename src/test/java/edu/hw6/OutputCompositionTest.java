package edu.hw6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemException;

public class OutputCompositionTest {
    @Test
    public void contentIsEqual() {
        String expected = "Programming is learned by writing programs. ― Brian Kernighan";
        String filePath = "src/test/resources/hw6/output_composition/outputComposition.txt";
        OutputComposition.solve(filePath);
        File file = new File(filePath);
        System.out.println();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String actual = reader.readLine();
            assertEquals(expected, actual);
        } catch (IOException ignored) {
            
        }
    }

    @Test
    public void nullPath() {
        assertThrows(NullPointerException.class, () -> {
            OutputComposition.solve((String) null);
        });

    }

    @Test
    public void directoryCheck() {

        String filePath = "src/test/resources";
        try {
            OutputComposition.solve(filePath);
        } catch (Exception e) {
            assertEquals(FileSystemException.class, e.getCause().getClass());
        }


    }
}
