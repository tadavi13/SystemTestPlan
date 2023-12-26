package edu.ncsu.csc216.stp.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/**
 * Test class for TestCase class.
 */
public class TestCaseTest {
	/**
	 * Test valid test case construction
	 */
	@Test
	public void testValidConstruction() {
		assertDoesNotThrow(() -> new TestCase("Test", "Test", "Test", "Test"));
		assertDoesNotThrow(() -> new TestCase("T", "T", "T", "T"));
		assertDoesNotThrow(() -> new TestCase("sadkjfhlkasjdf", "sadfyas8df7as87df6s87df6", "sdafysa87dfa87sdf0", "as87df0as'sapdufoiu8[u0uasf"));
	}
	
	/**
	 * Tests invalid test case construction
	 */
	@Test
	public void testInvalidConstruction() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new TestCase(null, "Test", "Test", "Test"));
		assertEquals("Invalid test information.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> new TestCase("", "Test", "Test", "Test"));
		assertEquals("Invalid test information.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> new TestCase("Test", null, "Test", "Test"));
		assertEquals("Invalid test information.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> new TestCase("Test", "", "Test", "Test"));
		assertEquals("Invalid test information.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> new TestCase("Test", "Test", null, "Test"));
		assertEquals("Invalid test information.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> new TestCase("Test", "Test", "", "Test"));
		assertEquals("Invalid test information.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> new TestCase("Test", "Test", "Test", null));
		assertEquals("Invalid test information.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> new TestCase("Test", "Test", "Test", ""));
		assertEquals("Invalid test information.", e.getMessage());
	}
	
	/**
	 * Tests adding test results and the toString of the Test Case class
	 */
	@Test
	public void testAddingTestResults() {
		TestCase testCase = new TestCase("ID 1", "type 1", "description 1", "expected results 1");
		
		testCase.addTestResult(false, "Test is failing.");
		assertEquals(TestResult.FAIL, testCase.getStatus());
		assertEquals("- FAIL: Test is failing.\n", testCase.getActualResultsLog());
	}
}
