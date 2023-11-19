package edu.hw6;

import edu.hw6.filter.AbstractFilter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class FilterTest {
    private static final String TEST_RESOURCE_FOLDER = "src/test/resources/hw6/filter";

    @Test
    public void getAllTxtRegularFile() {
        Set<Path> expected = Set.of(
                Path.of(TEST_RESOURCE_FOLDER, "read_only_large.txt"),
                Path.of(TEST_RESOURCE_FOLDER, "read_only_small.txt"),

                Path.of(TEST_RESOURCE_FOLDER, "write_and_read.txt"),
                Path.of(TEST_RESOURCE_FOLDER, "write_and_read_large.txt")


        );


        Set<Path> actual = new HashSet<>();

        Path dir = Path.of(TEST_RESOURCE_FOLDER);
        AbstractFilter filter = AbstractFilter.IS_REGULAR_FILE
                .and(AbstractFilter.globMatches("*.txt"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(actual::add);
        } catch (IOException ignored) {

        }

        assertEquals(expected, actual);


    }

    @Test
    public void getAllLargeTxtFiles() {
        Set<Path> expected = Set.of(
                Path.of(TEST_RESOURCE_FOLDER, "read_only_large.txt"),
                Path.of(TEST_RESOURCE_FOLDER, "write_and_read_large.txt")

        );


        Set<Path> actual = new HashSet<>();

        Path dir = Path.of(TEST_RESOURCE_FOLDER);
        AbstractFilter filter = AbstractFilter.IS_REGULAR_FILE
                .and(AbstractFilter.largerThan(1000))
                .and(AbstractFilter.regexContains("_"))
                .and(AbstractFilter.globMatches("*.txt"));


        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(actual::add);
        } catch (IOException ignored) {

        }

        assertEquals(expected, actual);

    }

    @Test
    public void getAllReadFiles() {
        Set<Path> expected = Set.of(
                Path.of(TEST_RESOURCE_FOLDER, "read_only_large.txt"),
                Path.of(TEST_RESOURCE_FOLDER, "read_only_small.txt"),
                Path.of(TEST_RESOURCE_FOLDER, "write_and_read.txt"),
                Path.of(TEST_RESOURCE_FOLDER, "write_and_read_large.txt")
        );


        Set<Path> actual = new HashSet<>();

        Path dir = Path.of(TEST_RESOURCE_FOLDER);
        AbstractFilter filter = AbstractFilter.IS_REGULAR_FILE
                .and(AbstractFilter.IS_READABLE_FILE)
                .and(AbstractFilter.globMatches("*.txt"));


        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(actual::add);
        } catch (IOException ignored) {

        }

        assertEquals(expected, actual);

    }

    @Test
    public void getAllWriteFiles() {
        Set<Path> expected = Set.of(
                Path.of(TEST_RESOURCE_FOLDER, "write_and_read.txt"),
                Path.of(TEST_RESOURCE_FOLDER, "write_and_read_large.txt")

        );


        Set<Path> actual = new HashSet<>();

        Path dir = Path.of(TEST_RESOURCE_FOLDER);
        AbstractFilter filter = AbstractFilter.IS_REGULAR_FILE
                .and(AbstractFilter.IS_WRITABLE_FILE)
                .and(AbstractFilter.globMatches("*.txt"));


        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(actual::add);
        } catch (IOException ignored) {

        }

        assertEquals(expected, actual);

    }

    @Test
    public void pngMagicNumber() {
        Set<Path> expected = Set.of(
                Path.of(TEST_RESOURCE_FOLDER, "noname.png"),
                Path.of(TEST_RESOURCE_FOLDER, "imageBig.png"),
                Path.of(TEST_RESOURCE_FOLDER, "giraffe.png")
        );

        Set<Path> actual = new HashSet<>();

        Path dir = Path.of(TEST_RESOURCE_FOLDER);
        AbstractFilter filter = AbstractFilter.IS_REGULAR_FILE
                .and(AbstractFilter.magicNumber(0x89, 'P', 'N', 'G'))
                .and(AbstractFilter.globMatches("*.png"));


        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {

            entries.forEach(actual::add);
        } catch (IOException ignored) {

        }

        assertEquals(expected, actual);
    }

    @Test
    public void pngMagicNumberTwo() {
        Set<Path> expected = Set.of(

                Path.of(TEST_RESOURCE_FOLDER, "imageBig.png")

        );

        Set<Path> actual = new HashSet<>();

        Path dir = Path.of(TEST_RESOURCE_FOLDER);
        AbstractFilter filter = AbstractFilter.IS_REGULAR_FILE
                .and(AbstractFilter.largerThan(300_000))
                .and(AbstractFilter.magicNumber(0x89, 'P', 'N', 'G'));


        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {

            entries.forEach(actual::add);
        } catch (IOException ignored) {

        }

        assertEquals(expected, actual);
    }

    @Test
    public void jpegMagicNumber() {
        Set<Path> expected = Set.of(

                Path.of(TEST_RESOURCE_FOLDER, "tren.jpeg")

        );

        Set<Path> actual = new HashSet<>();

        Path dir = Path.of(TEST_RESOURCE_FOLDER);
        AbstractFilter filter = AbstractFilter.IS_REGULAR_FILE

                .and(AbstractFilter.magicNumber(0xff, 0xd8, 0xff))
                .and(AbstractFilter.largerThan(45_000));


        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {

            entries.forEach(actual::add);
        } catch (IOException ignored) {

        }

        assertEquals(expected, actual);
    }

    @Test
    public void jpegMatchGlob() {
        Set<Path> expected = Set.of(

                Path.of(TEST_RESOURCE_FOLDER, "tren.jpeg")

        );

        Set<Path> actual = new HashSet<>();

        Path dir = Path.of(TEST_RESOURCE_FOLDER);
        AbstractFilter filter = AbstractFilter.IS_REGULAR_FILE

                .and(AbstractFilter.globMatches("*.jpeg"))
                .and(AbstractFilter.largerThan(45_000));


        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {

            entries.forEach(actual::add);
        } catch (IOException ignored) {

        }

        assertEquals(expected, actual);
    }
}
