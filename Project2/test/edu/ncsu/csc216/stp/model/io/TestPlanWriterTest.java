package edu.ncsu.csc216.stp.model.io;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Test class for TestPlanWriter.
 */
public class TestPlanWriterTest {
	/**
	 * Tests the write test plans method in the writer class
	 */
	@Test
	public void testWriteTestPlans() {
		SortedList<TestPlan> testPlans = new SortedList<TestPlan>();
		File file = new File("test_files/inttest");
		
		TestPlan plan = new TestPlan("TestPlan");
		TestCase test1 = new TestCase("Test1", "Requirements", "Do this", "Get this");
		test1.addTestResult(false, "failed");
		TestCase test2 = new TestCase("Test2", "Boundary", "Test this value", "Get value");
		test2.addTestResult(true, "passed");
		plan.addTestCase(test1);
		plan.addTestCase(test2);
		testPlans.add(plan);
		
		assertDoesNotThrow(() -> TestPlanWriter.writeTestPlanFile(file, testPlans));
	}
	
	/**
	 * Tests the write test plans method in the writer class for an invalid file
	 */
	@Test
	public void testWriteTestPlansInvalidFile() {
		SortedList<TestPlan> testPlans = new SortedList<TestPlan>();
		File file = new File("test_files");
		
		TestPlan plan = new TestPlan("TestPlan");
		TestCase test1 = new TestCase("Test1", "Requirements", "Do this", "Get this");
		test1.addTestResult(false, "failed");
		TestCase test2 = new TestCase("Test2", "Boundary", "Test this value", "Get value");
		test2.addTestResult(true, "passed");
		plan.addTestCase(test1);
		plan.addTestCase(test2);
		testPlans.add(plan);
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> TestPlanWriter.writeTestPlanFile(file, testPlans));
		assertEquals("Unable to save file.", e.getMessage());
	}
}
