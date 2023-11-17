package edu.hw6.filter;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    AbstractFilter IS_REGULAR_FILE = Files::isRegularFile;

    AbstractFilter IS_READABLE_FILE = Files::isReadable;
    AbstractFilter IS_WRITABLE_FILE = Files::isWritable;

    @Override
    boolean accept(Path entry) throws IOException;

    default AbstractFilter and(AbstractFilter other) {
        return entry -> this.accept(entry) && other.accept(entry);
    }

    static AbstractFilter magicNumber(int... magicBytes) {
        return entry -> {
            try {
                byte[] fileBytes = Files.readAllBytes(entry);
                if (fileBytes.length >= magicBytes.length) {
                    for (int i = 0; i < magicBytes.length; i++) {
                        if ((byte) magicBytes[i] != fileBytes[i]) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return false;
        };
    }


    static AbstractFilter largerThan(long size) {
        return entry -> Files.size(entry) > size;
    }


    static AbstractFilter globMatches(String glob) {
        return entry -> {
            PathMatcher pathMatcher = entry.getFileSystem().getPathMatcher("glob:" + glob);
            return pathMatcher.matches(entry.getFileName());
        };
    }

    static AbstractFilter regexContains(String regex) {
        return entry -> entry.getFileName().toString().matches(".*" + regex + ".*");
    }

}

