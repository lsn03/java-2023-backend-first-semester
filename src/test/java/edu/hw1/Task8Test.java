package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task8Test {
    @Test
    public void testThatFirstArrayFromWebSiteReturnTrue() {
//		Arrange
        Task8 task8 = new Task8();
        boolean expected = true;
        int[][] arr = {
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0}};
//		Act
        boolean result = task8.knightBoardCapture(arr);
//		Assert
        assertEquals(expected, result);
    }

    @Test
    public void testThatSecondArrayFromWebSiteReturnFalse() {
//		Arrange
        Task8 task8 = new Task8();
        boolean expected = false;
        int[][] arr = {
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1}};
//		Act
        boolean result = task8.knightBoardCapture(arr);
//		Assert
        assertEquals(expected, result);
    }

    @Test
    public void testThatThirdArrayFromWebSiteReturnFalse() {
//		Arrange
        Task8 task8 = new Task8();
        boolean expected = false;
        int[][] arr = {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}};
//		Act
        boolean result = task8.knightBoardCapture(arr);
//		Assert
        assertEquals(expected, result);
    }
    @Test
    public void testThatArrayWithHourseInCornerReturnTrue() {
//		Arrange
        Task8 task8 = new Task8();
        boolean expected = true;
        int[][] arr = {
                {1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 1}};
//		Act
        boolean result = task8.knightBoardCapture(arr);
//		Assert
        assertEquals(expected, result);
    }
}
