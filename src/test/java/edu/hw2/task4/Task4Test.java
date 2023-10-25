package edu.hw2.task4;

import edu.hw2.Task4.CallingInfoUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {

    @Test
    public void testThatMethodWasCalledAtThisMethod() {
//        Arrange
        String currentMethodName = Task4Test.class.getMethods()[0].getName();
//        Act
        var info = CallingInfoUtil.callingInfo();
//        Assert
        assertEquals(currentMethodName, info.methodName());
    }
}
