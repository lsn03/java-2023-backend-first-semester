package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {
	@Test
	public void testThatPalindromeExample1ReturnTrue() {
//		Arrange
		Task5 task5 = new Task5();
		int input = 11211230;
		boolean expected = true;
//		Act
		boolean res = task5.isPalindromeDescendant(input);
//		Assert
		assertEquals(expected, res);
	}

	@Test
	public void testThatPalindromeExample2ReturnTrue() {
//		Arrange
		Task5 task5 = new Task5();
		int input = 13001120;
		boolean expected = true;
//		Act
		boolean res = task5.isPalindromeDescendant(input);
//		Assert
		assertEquals(expected, res);
	}

	@Test
	public void testThatPalindromeExample3ReturnTrue() {
//		Arrange
		Task5 task5 = new Task5();
		int input = 23336014;
		boolean expected = true;
//		Act
		boolean res = task5.isPalindromeDescendant(input);
//		Assert
		assertEquals(expected, res);
	}

	@Test
	public void testThatPalindromeWithOddNumberReturnTrue() {
//		Arrange
		Task5 task5 = new Task5();
		int input = 516;
		boolean expected = true;
//		Act
		boolean res = task5.isPalindromeDescendant(input);
//		Assert
		assertEquals(expected, res);
	}

	@Test
	public void testThatPalindromeWithOddNumberReturnFalse() {
//		Arrange
		Task5 task5 = new Task5();
		int input = 23018;
		boolean expected = false;
//		Act
		boolean res = task5.isPalindromeDescendant(input);
//		Assert
		assertEquals(expected, res);
	}

	@Test
	public void testThatPalindromeWithNegativeNumbeThrowIllegalArgumentException() {
//		Arrange
		Task5 task5 = new Task5();
		int input = -23018;

//		Act && Assert
		assertThrows(IllegalArgumentException.class, () -> {
			task5.isPalindromeDescendant(input);
		});

	}
}
