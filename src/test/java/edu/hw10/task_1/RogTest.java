package edu.hw10.task_1;

import edu.hw10.task_1.example.CarClass;
import edu.hw10.task_1.example.PersonClass;
import edu.hw10.task_1.example.PersonRecord;
import edu.hw10.task_1.example.Rogable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class RogTest {
    @Test
    public void test() {
        RandomObjectGenerator rog = new RandomObjectGenerator(5);
        String expectedRogable1 = "CarClass{carModel='RandomString_-1157408321', maxSpeed=-1667228448}";
        String expectedRogable2 = "PersonClass{name='曚', age=24, salary=1774496691}";
        String expectedRogable3 = "PersonRecord{name='糣ﲕ쯫ҙ鹡', age=88, salary=-691131116}";

        assertDoesNotThrow(() -> {
            Rogable rogable1 = rog.nextObject(CarClass.class);
            Rogable rogable2 = rog.nextObject(PersonClass.class, "create");
            Rogable rogable3 = rog.nextObject(PersonRecord.class);

            assertEquals(expectedRogable1, rogable1.toString());
            assertEquals(expectedRogable2, rogable2.toString());
            assertEquals(expectedRogable3, rogable3.toString());

        });


    }

    @Test
    public void failedTests() {
        RandomObjectGenerator rog = new RandomObjectGenerator(5);
        assertThrows(NoSuchMethodException.class, () ->
                rog.nextObject(CarClass.class, "create"));
        assertThrows(NullPointerException.class, () ->
                rog.nextObject(null, "create"));
        assertThrows(IllegalArgumentException.class, () ->
                rog.nextObject(CarClass.class, ""));
        assertThrows(IllegalArgumentException.class, () ->
                rog.nextObject(CarClass.class, "    "));
    }
}
