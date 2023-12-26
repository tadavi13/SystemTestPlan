package edu.ncsu.csc216.stp.model.io;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Test class for TestPlanReader.
 */
public class TestPlanReaderTest {
	/**
	 * Tests the read plan file method
	 */
	@Test
	public void testReadTestPlansFile0() {
		File file = new File("test_files/test-plans0.txt");
		ISortedList<TestPlan> testPlans = new SortedList<TestPlan>();
		
		testPlans = TestPlanReader.readTestPlansFile(file);
		TestCase testCase00 = testPlans.get(0).getTestCase(0);
		TestCase testCase01 = testPlans.get(0).getTestCase(1);
		TestCase testCase10 = testPlans.get(1).getTestCase(0);
		TestCase testCase11 = testPlans.get(1).getTestCase(1);
		TestCase testCase12 = testPlans.get(1).getTestCase(2);
		
		assertEquals("PackScheduler", testPlans.get(0).getTestPlanName());
		assertEquals("test0", testCase00.getTestCaseId());
		assertEquals("Invalid", testCase00.getTestType());
		assertEquals("description", testCase00.getTestDescription());
		assertEquals("expected results\nwith multiple lines", testCase00.getExpectedResults());
		assertEquals("- PASS: actual results\n- FAIL: actual results\n", testCase00.getActualResultsLog());
		assertEquals("test1", testCase01.getTestCaseId());
		assertEquals("Equivalence Class", testCase01.getTestType());
		assertEquals("description", testCase01.getTestDescription());
		assertEquals("expected results", testCase01.getExpectedResults());
		assertEquals("- PASS: actual results\n", testCase01.getActualResultsLog());
		
		assertEquals("WolfScheduler", testPlans.get(1).getTestPlanName());
		assertEquals("test1", testCase10.getTestCaseId());
		assertEquals("Equivalence Class", testCase10.getTestType());
		assertEquals("description\nwith multiple lines", testCase10.getTestDescription());
		assertEquals("expected results\nwith multiple lines", testCase10.getExpectedResults());
		assertEquals("- PASS: actual results\n- FAIL: actual results on\nmultiple lines\n- PASS: actual results\non three\nlines\n", testCase10.getActualResultsLog());
		assertEquals("test2", testCase11.getTestCaseId());
		assertEquals("Boundary Value", testCase11.getTestType());
		assertEquals("description", testCase11.getTestDescription());
		assertEquals("expected results", testCase11.getExpectedResults());
		assertEquals("", testCase11.getActualResultsLog());
		assertEquals("test3", testCase12.getTestCaseId());
		assertEquals("Requirements", testCase12.getTestType());
		assertEquals("description\non multiple lines", testCase12.getTestDescription());
		assertEquals("expected results", testCase12.getExpectedResults());
		assertEquals("- FAIL: actual results\n", testCase12.getActualResultsLog());
	}
	
	/**
	 * Tests the read plan file method where no test cases are created
	 */
	@Test
	public void testReadTestPlansFile8() {
		File file = new File("test_files/test-plans8.txt");
		ISortedList<TestPlan> testPlans = new SortedList<TestPlan>();
		
		testPlans = TestPlanReader.readTestPlansFile(file);
		TestPlan plan0 = testPlans.get(0);
		
		assertEquals("WolfScheduler", testPlans.get(0).getTestPlanName());
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> plan0.getTestCases().get(0));
		assertEquals("Invalid index.", e.getMessage());
	}
}