package edu.ncsu.csc216.stp.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/**
 * Test class for TestResult class.
 */
public class TestResultTest {
	/**
	 * Tests invalid test result construction
	 */
	@Test
	public void testValidConstruction() {
		assertDoesNotThrow(() -> new TestResult(true, "Result"));
		assertDoesNotThrow(() -> new TestResult(false, "Result"));
		assertDoesNotThrow(() -> new TestResult(true, "R"));
		assertDoesNotThrow(() -> new TestResult(true, "Rasdhfkjashdjkfhasikgfhaokg"
				+ "ad;fglvkad,l;'v,a'sd/cva['dsflgp[a;dskgfl;aldk"));
	}
	
	/**
	 * Tests invalid test result construction
	 */
	@Test
	public void testInvalidConstruction() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new TestResult(true, ""));
		assertEquals("Invalid test results.", e.getMessage());
		
		e = assertThrows(IllegalArgumentException.class, () -> new TestResult(false, null));
		assertEquals("Invalid test results.", e.getMessage());
	}
}
