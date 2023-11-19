package edu.hw6;

import edu.hw6.disk_map_package.DiskMap;
import edu.hw6.disk_map_package.ParseFileException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Map;


public class DiskMapTest {
    DiskMap diskMap;
    String source_info_file_readOnly = "src/test/resources/hw6/diskmap/source_info.txt";

    @Test
    public void readFromFileSourceInfo() {


        diskMap = new DiskMap(source_info_file_readOnly, true);

        var expected = new HashMap<>(Map.of(
                "aboba", "bob",
                "тест_кей_2", "тест_значение_2",
                "ключ", "значение",
                "добавить", "убавить"
        ));

        var actual = diskMap.getInMemoryMap();

        assertEquals(expected, actual);
    }

    @Test
    public void removeFromFile() {
        diskMap = new DiskMap(source_info_file_readOnly, true);

        var expected = new HashMap<>(Map.of(

                "ключ", "значение",
                "добавить", "убавить",
                "aboba", "bob"
        ));
        diskMap.setFilePath("src/test/resources/hw6/diskmap/removed_key.txt");
        diskMap.remove("тест_кей_2");
        var actual = diskMap.getInMemoryMap();

        assertEquals(expected, actual);
        assertEquals(3, diskMap.size());
    }

    @Test
    public void removeAllFromFileByClear() {
        diskMap = new DiskMap(source_info_file_readOnly, true);

        diskMap.setFilePath("src/test/resources/hw6/diskmap/removed_all_key_by_clear.txt");
        diskMap.clear();


        assertTrue(diskMap.isEmpty());
    }

    @Test
    public void removeAllFromFileByRemove() {
        diskMap = new DiskMap(source_info_file_readOnly, true);

        diskMap.setFilePath("src/test/resources/hw6/diskmap/removed_all_key_by_remove.txt");
        diskMap.remove("aboba");
        diskMap.remove("тест_кей_2");
        diskMap.remove("ключ");
        diskMap.remove("добавить");


        assertTrue(diskMap.isEmpty());
    }

    @Test
    public void addKeysToFile() {
        diskMap = new DiskMap(source_info_file_readOnly, true);


        diskMap.setFilePath("src/test/resources/hw6/diskmap/add_keys_to_file.txt");
        diskMap.clear();
        diskMap.put("4", "4");
        diskMap.put("3", "3");
        diskMap.put("2", "2");
        diskMap.put("1", "1");

        var expected = new HashMap<>(Map.of(
                "4", "4",
                "1", "1",
                "3", "3",
                "2", "2"

        ));

        assertEquals(expected, diskMap.getInMemoryMap());
        assertFalse(diskMap.isEmpty());
        assertEquals(4, diskMap.size());
    }

    @Test
    public void wrongFileData() {

        assertThrows(ParseFileException.class, () -> {
            new DiskMap("src/test/resources/hw6/diskmap/wrong.txt", true);
        });
    }

    @Test
    public void fileNotExistFileData() {

        try {
            new DiskMap("src/test/resources/hw6/diskmap/1.txt", true);
        } catch (Exception e) {
            assertEquals(NoSuchFileException.class, e.getCause().getClass());
        }
    }
}
