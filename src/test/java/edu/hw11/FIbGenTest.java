package edu.hw11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FIbGenTest {
    @Test
    public void test() throws Exception {

        FibonnachiGenerator.generateFibClass("src/test/java/edu/hw11/");
        String className = "Fibonacci";
        Class<?> fibClass = new CustomClassLoader().defineClassFromFile(className,"src/test/java/edu/hw11/Fibonacci.class");
        System.out.println(      fibClass.getDeclaredMethods());

    }
    public class CustomClassLoader extends ClassLoader {

        public Class<?> defineClassFromFile(String className, String filePath) throws IOException {
            byte[] classData = Files.readAllBytes(Path.of(filePath));
            return defineClass(className, classData, 0, classData.length);
        }
    }
}
