package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Test class for AbstractTestPlan class.
 */
public class AbstractTestPlanTest {
	/** TestPlan as abstract plan for tests */
	AbstractTestPlan testPlan = new TestPlan("TestPlan1");
	/** Failing Test list as an abstract plan for test */
	AbstractTestPlan testPlan2 = new FailingTestList();
	/**
	 * Test the set test plan name method
	 */
	@Test
	public void testSetTestPlanName() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new TestPlan("Failing Tests"));
		assertEquals("Invalid name.", e.getMessage());
	}
	
	/**
	 * Test getting the number of failing test cases in a test plan
	 */
	@Test
	public void testGetNumberOfFailingTests() {
		testPlan.addTestCase(new TestCase("Test", "Test", "Test", "Test"));
		TestCase testCase2 = new TestCase("Test2", "Test2", "Test2", "Test2");
		testPlan.addTestCase(testCase2);
		testPlan.addTestResult(1, true, "Passing");
		
		assertEquals(0, testPlan2.getNumberOfFailingTests());
		assertEquals(1, testPlan.getNumberOfFailingTests());
	}
}
