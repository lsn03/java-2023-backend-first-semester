package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {
	@Test
	public void testThatFixedExample1() {
//		Arrange
		Task4 task4 = new Task4();
		String str = "214365";
//		Act
		String result = task4.fixString(str);
		String expected = "123456";
//		Assert
		assertEquals(expected, result);
	}

	@Test
	public void testThatFixedExample2() {
//		Arrange
		Task4 task4 = new Task4();
		String str = "hTsii  s aimex dpus rtni.g";
//		Act
		String result = task4.fixString(str);
		String expected = "This is a mixed up string.";
//		Assert
		assertEquals(expected, result);
	}

	@Test
	public void testThatFixedExample3() {
//		Arrange
		Task4 task4 = new Task4();
		String str = "badce";
//		Act
		String result = task4.fixString(str);
		String expected = "abcde";
//		Assert
		assertEquals(expected, result);
	}

	@Test
	public void testThatEmptyStringReturnEmptyString() {
//		Arrange
		Task4 task4 = new Task4();
		String str = "";
//		Act
		String result = task4.fixString(str);
		String expected = "";
//		Assert
		assertEquals(expected, result);
	}

	@Test
	public void testThatNullStringFailed() {
//		Arrange
		Task4 task4 = new Task4();
		String str = null;

//		Act && Assert
		assertThrows(NullPointerException.class, () -> {
			task4.fixString(str);
		});
	}
}
