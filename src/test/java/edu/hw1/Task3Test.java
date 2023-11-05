package edu.hw1;

import edu.homework.hw1.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {
	@Test
	@DisplayName("Первый тест из примера")
	public void testOneTrue() {
//		Arrange
		Task3 task3 = new Task3();
		int[] a = {1, 2, 3, 4};
		int[] b = {0, 6};
//		Act
		var res = task3.isNestable(a, b);
//		Assert
		assertTrue(res);
	}

	@Test
	@DisplayName("Второй тест из примера")
	public void testSecondTrue() {
//		Arrange
		Task3 task3 = new Task3();
		int[] a = {3, 1};
		int[] b = {4, 0};
//		Act
		var res = task3.isNestable(a, b);
//		Assert
		assertTrue(res);
	}

	@Test
	@DisplayName("Третий тест из примера")
	public void testThirdFalse() {
//		Arrange
		Task3 task3 = new Task3();
		int[] a = {9, 9, 8};
		int[] b = {8, 9};
//		Act
		var res = task3.isNestable(a, b);
//		Assert
		assertFalse(res);
	}

	@Test
	@DisplayName("Четвертый тест из примера")
	public void testFourthFalse() {
//		Arrange
		Task3 task3 = new Task3();
		int[] a = {1, 2, 3, 4};
		int[] b = {2, 3};
//		Act
		var res = task3.isNestable(a, b);
//		Assert
		assertFalse(res);
	}

	@Test
	public void testTheEqualsArraysFalse() {
//		Arrange
		Task3 task3 = new Task3();
		int[] a = {1, 2};
		int[] b = {1, 2};
//		Act
		var res = task3.isNestable(a, b);
//		Assert
		assertFalse(res);
	}
	@Test
	public void testTheNullArraysFalse() {
//		Arrange
		Task3 task3 = new Task3();
		int[] a = null;
		int[] b = null;
//		Act
		var res = task3.isNestable(a, b);
//		Assert
		assertFalse(res);
	}
	@Test
	public void testTheEmptyArraysFalse() {
//		Arrange
		Task3 task3 = new Task3();
		int[] a = {};
		int[] b = {};
//		Act
		var res = task3.isNestable(a, b);
//		Assert
		assertFalse(res);
	}
}
