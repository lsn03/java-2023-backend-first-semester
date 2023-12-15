package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskHelloBuddyTest {
    @Test
    public void testByteBuddy() {
        String value = "Hello, ByteBuddy!";
        assertDoesNotThrow(
                () -> {
                    var obj = new ByteBuddy()
                            .subclass(Object.class)
                            .method(ElementMatchers.named("toString"))
                            .intercept(FixedValue.value(value))
                            .make()
                            .load(this.getClass().getClassLoader())
                            .getLoaded().newInstance();
                    assertEquals(value, obj.toString());
                }
        );

    }
}
