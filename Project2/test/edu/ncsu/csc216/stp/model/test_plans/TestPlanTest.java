package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Test class for TestPlan class.
 */
public class TestPlanTest {
	/**
	 * Test valid test plan construction
	 */
	@Test
	public void testValidConstruction() {
		assertDoesNotThrow(() -> new TestPlan("TestPlan"));
		assertDoesNotThrow(() -> new TestPlan("T"));
		assertDoesNotThrow(() -> new TestPlan("KJAHDKJDHKJAHKJDHS2876534;,';,"));
	}
	
	/**
	 * Tests invalid test plan construction
	 */
	@Test
	public void testInvalidConstruction() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new TestPlan(FailingTestList.FAILING_TEST_LIST_NAME));
		assertEquals("Invalid name.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> new TestPlan(""));
		assertEquals("Invalid name.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> new TestPlan(null));
		assertEquals("Invalid name.", e.getMessage());
	}
	
	/**
	 * Tests adding test cases to a test plan
	 */
	@Test
	public void testAddingCases() {
		TestPlan plan = new TestPlan("TestPlan");
		
		TestCase test1 = new TestCase("Test1", "Requirements", "Do this", "Get this");
		test1.addTestResult(false, "failed");
		TestCase test2 = new TestCase("Test2", "Boundary", "Test this value", "Get value");
		test2.addTestResult(true, "passed");
		TestCase test3 = new TestCase("Test3", "Invalid", "Enter this", "Get this exception");
		test3.addTestResult(false, "failed");
		plan.addTestCase(test1);
		plan.addTestCase(test2);
		plan.addTestCase(test3);
		
		String[][] actual = plan.getTestCasesAsArray();
		assertEquals("Test1", actual[0][0]);
		assertEquals("Requirements", actual[0][1]);
		assertEquals("FAIL", actual[0][2]);
		assertEquals("Test2", actual[1][0]);
		assertEquals("Boundary", actual[1][1]);
		assertEquals("PASS", actual[1][2]);
		assertEquals("Test3", actual[2][0]);
		assertEquals("Invalid", actual[2][1]);
		assertEquals("FAIL", actual[2][2]);
	}
	
	/**
	 * Tests adding test cases to a test plan with no results
	 */
	@Test
	public void testMovingTestCases() {
		TestPlan plan = new TestPlan("TestPlan");
		
		TestCase test1 = new TestCase("ID1", "type1", "description1", "expected1");
		TestCase test2 = new TestCase("ID2", "type2", "description2", "expected2");
		TestCase test3 = new TestCase("ID3", "type3", "description3", "expected3");
		TestCase test4 = new TestCase("ID4", "type4", "description4", "expected4");
		plan.addTestCase(test1);
		plan.addTestCase(test2);
		plan.addTestCase(test3);
		plan.addTestCase(test4);
		
		String[][] actual = plan.getTestCasesAsArray();
		assertEquals("ID1", actual[0][0]);
		assertEquals("type1", actual[0][1]);
		assertEquals("FAIL", actual[0][2]);
		assertEquals("ID2", actual[1][0]);
		assertEquals("type2", actual[1][1]);
		assertEquals("FAIL", actual[1][2]);
		assertEquals("ID3", actual[2][0]);
		assertEquals("type3", actual[2][1]);
		assertEquals("FAIL", actual[2][2]);
		assertEquals("ID4", actual[3][0]);
		assertEquals("type4", actual[3][1]);
		assertEquals("FAIL", actual[3][2]);
	}
}
