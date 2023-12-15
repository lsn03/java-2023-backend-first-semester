package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChangeOperandTest {
    private static Class<?> originalClass;
    private static Class<?> modifiedClass;
    private static DynamicType.Unloaded<?> unloaded;

    @BeforeAll
    public static void setup() {
        originalClass = ArithmeticUtils.class;
        unloaded = new ByteBuddy()
                .subclass(originalClass)
                .method(ElementMatchers.named("sum"))
                .intercept(MethodDelegation.to(new Multiply()))
                .make();
        modifiedClass = unloaded.load(unloaded.getClass().getClassLoader()).getLoaded();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 1, 1",
            "2, 5, 10",
            "10, 1, 10",
            "10, -10, -100",
            "-10, -10, 100",
            "-10, 0, 0",
    })
    public void changeTest(int a, int b, int res) throws InstantiationException, IllegalAccessException {


        ArithmeticUtils modified = (ArithmeticUtils) modifiedClass.newInstance();

        assertEquals(res, modified.sum(a, b));
    }

    public static class ArithmeticUtils {

        public int sum(int a, int b) {
            return a + b;
        }

    }

    public static class Multiply {

        public int sum(int a, int b) {
            return a * b;
        }
    }

}
