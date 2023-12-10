package edu.hw9.file;

import edu.hw9.tree.DirectorySearch;
import edu.hw9.tree.FileSearch;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.List;

public class FileTest {
    @Test
    public void failedTests() {

        assertThrows(NullPointerException.class, () -> new DirectorySearch(null, 1));
        assertThrows(IllegalArgumentException.class, () -> new DirectorySearch(Path.of(""), 1));
        assertThrows(IllegalArgumentException.class, () -> new DirectorySearch(Path.of("     "), 1));
        assertThrows(IllegalArgumentException.class, () -> new DirectorySearch(Path.of("spoiler"), -5));

    }

    @Test
    public void findDirTest() {
        List<Path> expected = List.of(
                Path.of("src/test/resources/hw9/temp_dir/dir"),
                Path.of("src/test/resources/hw9/temp_dir/dir/2")
        );

        Path path = Path.of("src/test/resources/hw9/temp_dir/dir");
        DirectorySearch fileSearch = new DirectorySearch(path, 2);
        var ans = fileSearch.launch();
        assertEquals(expected, ans);
    }

    @Test
    public void findDirTest2() {
        List<Path> expected = List.of(
                Path.of("src/test/resources/hw9/temp_dir/dir/2")
        );

        Path path = Path.of("src/test/resources/hw9/temp_dir/dir");
        DirectorySearch fileSearch = new DirectorySearch(path, 4);
        var ans = fileSearch.launch();
        assertEquals(expected, ans);
    }

    @Test
    public void findFilesTest() {
        List<Path> expected = List.of(
                Path.of("src/test/resources/hw9/temp_dir/predicate/pred2/pred3/def3_104b.txt")
        );

        Path path = Path.of("src/test/resources/hw9/temp_dir/predicate/");
        FileSearch fileSearch = new FileSearch(path, ".txt", 100);
        var ans = fileSearch.launch();
        assertEquals(expected, ans);
    }

    @Test
    public void findFilesTest2() {
        List<Path> expected = List.of(
                Path.of("src/test/resources/hw9/temp_dir/predicate/pred2/def2_51b.txt"),
                Path.of("src/test/resources/hw9/temp_dir/predicate/pred2/pred3/def3_104b.txt"),
                Path.of("src/test/resources/hw9/temp_dir/predicate/pred2/pred4/def4_64b.txt")
        );

        Path path = Path.of("src/test/resources/hw9/temp_dir/predicate/");
        FileSearch fileSearch = new FileSearch(path, ".txt", 50);
        var ans = fileSearch.launch();
        assertEquals(expected, ans);
    }
}
