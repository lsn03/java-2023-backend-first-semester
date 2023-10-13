package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
	@Test
	public void testThatZeroReturnOne() {
//		Arrange
		Task2 task2 = new Task2();
//		Act
		int value = 0;
		int result = task2.countDigits(value);
//		Assert
		assertEquals(1,result);
	}
	@Test
	public void testThatGoodPositiveValueReturnSuccess() {
//		Arrange
		Task2 task2 = new Task2();
//		Act
		int value = 1294739;
		int result = task2.countDigits(value);
//		Assert
		assertEquals(7,result);
	}
	@Test
	public void testThatGoodNegativeValueReturnSuccess() {
//		Arrange
		Task2 task2 = new Task2();
//		Act
		int value = -1294739;
		int result = task2.countDigits(value);
//		Assert
		assertEquals(7,result);
	}
	@Test
	public void testThatMaxIntValueReturnSuccess() {
//		Arrange
		Task2 task2 = new Task2();
//		Act
		int value = Integer.MAX_VALUE;
		int result = task2.countDigits(value);
//		Assert
		assertEquals(10,result);
	}

	@Test
	public void testThatMinIntValueReturnSuccess() {
//		Arrange
		Task2 task2 = new Task2();
//		Act
		int value = Integer.MIN_VALUE;
		int result = task2.countDigits(value);
//		Assert
		assertEquals(-1,result);
	}
}
