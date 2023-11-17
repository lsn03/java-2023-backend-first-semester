package edu.hw6;

import edu.hw6.filter.AbstractFilter;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilterTest {
    private static final String TEST_RESOURCE_FOLDER = "src/test/resources/hw6/filter";

    @Test
    public void getAllTxtRegularFile() {
        Path dir = Path.of(TEST_RESOURCE_FOLDER);
        AbstractFilter filter = AbstractFilter.IS_REGULAR_FILE
                .and(AbstractFilter.IS_READABLE_FILE)
                .and(AbstractFilter.globMatches("*.txt"));
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(System.out::println);
        } catch (IOException e) {

        }
    }

}
