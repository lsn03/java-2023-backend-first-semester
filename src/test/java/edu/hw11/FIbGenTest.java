package edu.hw11;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

public class FIbGenTest {
    public static final String FILE_PATH = "src/test/java/edu/hw11/Fibonacci.class";
    private static Method method;
    private static Object instance;

    @BeforeAll
    public static void setup() throws Exception {
        FibonnachiGenerator.generateFibClass("src/test/java/edu/hw11/");
        String className = "Fibonacci";
        Class<?> fibClass = new CustomClassLoader().defineClassFromFile(className, FILE_PATH);
        var constr = fibClass.getDeclaredConstructor();
        instance = constr.newInstance();
        var methods = fibClass.getDeclaredMethods();
        method = methods[0];
    }

    @AfterAll
    public static void end() throws IOException {
        Path path = Path.of("src/test/java/edu/hw11/Fibonacci.class");
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 1",
            "2, 1",
            "5, 5",
            "6, 8",
            "7, 13",
    })
    public void test(int n, int expectedResult) throws Exception {


        assertEquals(expectedResult, method.invoke(instance, n));
    }

    public static class CustomClassLoader extends ClassLoader {

        public Class<?> defineClassFromFile(String className, String filePath) throws IOException {
            byte[] classData = Files.readAllBytes(Path.of(filePath));
            return defineClass(className, classData, 0, classData.length);
        }
    }
}
