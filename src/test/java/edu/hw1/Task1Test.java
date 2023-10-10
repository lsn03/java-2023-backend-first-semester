package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

	@Test
	public void testThatGoodMinutesAndNegativeSecondsFail() {
//		Arrange
		var task = new Task1();
		String inputString = "10:-1";
//		Act
		var result = task.minutesToSeconds(inputString);
//		Assert
		assertEquals(-1, result);
	}

	@Test
	public void testThatNegativeMinutesAndNegativeSecondsFail() {
//		Arrange
		var task = new Task1();
		String inputString = "-10:-1";
//		Act
		var result = task.minutesToSeconds(inputString);
//		Assert
		assertEquals(-1, result);
	}

	@Test
	public void testThatGoodMinutesAndGoodSecondsWithGoodFormatSuccess() {
//		Arrange
		var task = new Task1();
		String inputString = "10:01";
//		Act
		var result = task.minutesToSeconds(inputString);
//		Assert
		assertEquals(601, result);
	}

	@Test
	public void testThatGoodMinutesAndGoodSecondsWithBadFormatFailed() {
//		Arrange
		var task = new Task1();
		String inputString = "10:1";
//		Act
		var result = task.minutesToSeconds(inputString);
//		Assert
		assertEquals(-1, result);
	}

	@Test
	public void testThatGoodMinutesAndGoodSecondsWithBadFormatSuccess() {
//		Arrange
		var task = new Task1();
		String inputString = "1000:00";
//		Act
		var result = task.minutesToSeconds(inputString);
//		Assert
		assertEquals(60000, result);
	}

	@Test
	public void testThatMaxValueMinutesAndGoodSecondsWithGoodFormatFailed() {
//		Arrange
		var task = new Task1();
		String inputString = Integer.MAX_VALUE + ":50";
//		Act
		var result = task.minutesToSeconds(inputString);
//		Assert
		assertEquals(-1, result);
	}

	@Test
	public void testThatMinValueMinutesAndGoodSecondsWithGoodFormatFailedAgain() {
//		Arrange
		var task = new Task1();
		String inputString = Integer.MIN_VALUE + ":50";
//		Act
		var result = task.minutesToSeconds(inputString);
//		Assert
		assertEquals(-1, result);
	}
	@Test
	public void testThatZeroMinuteAndGoodSecondsWithGoodFormatSuccess() {
//		Arrange
		var task = new Task1();
		String inputString = "0:50";
//		Act
		var result = task.minutesToSeconds(inputString);
//		Assert
		assertEquals(50, result);
	}
}
