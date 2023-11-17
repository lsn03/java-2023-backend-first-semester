package edu.hw6.filter;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.filter.AbstractFilter.globMatches;
import static edu.hw6.filter.AbstractFilter.IS_REGULAR_FILE;
import static edu.hw6.filter.AbstractFilter.largerThan;
import static edu.hw6.filter.AbstractFilter.magicNumber;
import static edu.hw6.filter.AbstractFilter.regexContains;


public class Example {
    public static void main(String[] args) throws IOException {

        Path dir = Path.of("src/main/resources");

        DirectoryStream.Filter<Path> filter = IS_REGULAR_FILE
                .and(largerThan(0))
                .and(magicNumber(MagicNumbers.JPEG_MAGIC_NUMBERS))
                .and(globMatches("*.jpeg"))
                .and(regexContains(""));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(System.out::println);
        }
    }
}
