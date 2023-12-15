package edu.project5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Project5Test {
    @Test
    public void test() {
        Assertions.assertDoesNotThrow(() -> {
            RefBanchmark.run();
        });
    }
}
