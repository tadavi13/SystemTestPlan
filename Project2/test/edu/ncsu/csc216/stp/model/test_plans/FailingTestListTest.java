package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Test class for FailingTestList.
 */
public class FailingTestListTest {
	/**
	 * Test valid failing test list construction
	 */
	@Test
	public void testValidConstruction() {
		FailingTestList list = new FailingTestList();
		
		assertEquals("Failing Tests", list.getTestPlanName());
	}
	
	/**
	 * Tests adding test cases to a test plan
	 */
	@Test
	public void testAddingCases() {
		FailingTestList plan = new FailingTestList();
		
		TestCase test1 = new TestCase("Test1", "Requirements", "Do this", "Get this");
		test1.addTestResult(false, "failed");
		TestCase test2 = new TestCase("Test2", "Boundary", "Test this value", "Get value");
		test2.addTestResult(true, "passed");
		TestCase test3 = new TestCase("Test3", "Invalid", "Enter this", "Get this exception");
		test3.addTestResult(false, "failed");
		TestCase test4 = new TestCase("Test4", "Invalid", "Enter this", "Get this exception");
		plan.addTestCase(test1);
		Exception e = assertThrows(IllegalArgumentException.class, () -> plan.addTestCase(test2));
		assertEquals("Cannot add passing test case.", e.getMessage());
		plan.addTestCase(test3);
		plan.addTestCase(test4);
		
		String[][] actual = plan.getTestCasesAsArray();
		assertEquals("Test1", actual[0][0]);
		assertEquals("Requirements", actual[0][1]);
		assertEquals("", actual[0][2]);
		assertEquals("Test3", actual[1][0]);
		assertEquals("Invalid", actual[1][1]);
		assertEquals("", actual[1][2]);
		assertEquals("Test4", actual[2][0]);
		assertEquals("Invalid", actual[2][1]);
		assertEquals("", actual[2][2]);
	}
	
	/**
	 * Tests clearing test cases from the failing test list.
	 */
	@Test
	public void testClearCases() {
		FailingTestList plan = new FailingTestList();
		
		TestCase test1 = new TestCase("Test1", "Requirements", "Do this", "Get this");
		test1.addTestResult(false, "failed");
		TestCase test2 = new TestCase("Test2", "Boundary", "Test this value", "Get value");
		test2.addTestResult(true, "passed");
		TestCase test3 = new TestCase("Test3", "Invalid", "Enter this", "Get this exception");
		test3.addTestResult(false, "failed");
		TestCase test4 = new TestCase("Test4", "Invalid", "Enter this", "Get this exception");
		plan.addTestCase(test1);
		Exception e = assertThrows(IllegalArgumentException.class, () -> plan.addTestCase(test2));
		assertEquals("Cannot add passing test case.", e.getMessage());
		plan.addTestCase(test3);
		plan.addTestCase(test4);
		
		String[][] actual = plan.getTestCasesAsArray();
		assertEquals("Test1", actual[0][0]);
		assertEquals("Requirements", actual[0][1]);
		assertEquals("", actual[0][2]);
		assertEquals("Test3", actual[1][0]);
		assertEquals("Invalid", actual[1][1]);
		assertEquals("", actual[1][2]);
		assertEquals("Test4", actual[2][0]);
		assertEquals("Invalid", actual[2][1]);
		assertEquals("", actual[2][2]);
		
		plan.clearTests();
		actual = plan.getTestCasesAsArray();
		assertEquals(0, plan.getTestCases().size());
	}
}
