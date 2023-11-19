package edu.hw6.disk_map_package;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DiskMap implements Map<String, String> {
    private String filePath;
    private Map<String, String> inMemoryMap;

    private boolean isTesting;

    public DiskMap(String filePath) {
        this(filePath, false);
    }


    public DiskMap(String filePath, boolean isTesting) {
        this.isTesting = isTesting;
        this.filePath = filePath;

        inMemoryMap = new HashMap<>();
        loadFromFile();
    }

    public String getFilePath() {
        return filePath;
    }

    public Map<String, String> getInMemoryMap() {
        return inMemoryMap;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int size() {
        return inMemoryMap.size();
    }

    @Override
    public boolean isEmpty() {
        return inMemoryMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return inMemoryMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return inMemoryMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return inMemoryMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        var res = inMemoryMap.put(key, value);
        saveToFile();
        return res;
    }

    @Override
    public String remove(Object key) {
        var res = inMemoryMap.remove(key);
        saveToFile();
        return res;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        inMemoryMap.putAll(m);
        saveToFile();
    }

    @Override
    public void clear() {
        inMemoryMap.clear();
        saveToFile();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return inMemoryMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return inMemoryMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return inMemoryMap.entrySet();
    }

    public void saveToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (var entry : inMemoryMap.entrySet()) {
                String res = entry.getKey() + ":" + entry.getValue() + System.lineSeparator();
                writer.write(res);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadFromFile() {
        Path path = Path.of(filePath);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();

            int countOfLines = 0;

            while (line != null) {

                countOfLines += 1;
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    inMemoryMap.put(parts[0], parts[1]);
                } else {
                    throw new ParseFileException("Wrong format of line at position: " + countOfLines);
                }
                line = reader.readLine();
            }

        } catch (IOException | NullPointerException ex) {
            throw new RuntimeException(ex);
        }

    }


}
