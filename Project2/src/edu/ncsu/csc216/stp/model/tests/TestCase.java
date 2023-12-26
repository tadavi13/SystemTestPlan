package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ILog;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * Test Case class represents a individual test case in the WolfTestCases application. Each test case
 * has an id, type, description, expected results, list of actual test results, and a test plan it
 * belongs to. A test case has methods that allow a user to add a test result, check its passing status,
 * and set its test plan. 
 * 
 * @author Tyler Davis
 */
public class TestCase {
	/** ID of a test case */
	private String testCaseID;
	/** Test case type */
	private String testType;
	/** Description of how to perform test case */
	private String testDescription;
	/** Expected results of the test case */
	private String expectedResults;
	/** Log of actual test results for the test case */
	private ILog<TestResult> testResults;
	/** Test plan that the test case belongs to */
	private TestPlan testPlan;
	
	/**
	 * Constructor for a Test Case.
	 * @param testCaseID of the test case
	 * @param testType of the test case
	 * @param testDescription of how to perform the test case
	 * @param expectedResults of the test case
	 */
	public TestCase(String testCaseID, String testType, String testDescription, String expectedResults) {
		setTestCaseId(testCaseID);
		setTestType(testType);
		setTestDescription(testDescription);
		setExpectedResults(expectedResults);
		testResults = new Log<TestResult>();
		testPlan = null;
	}
	
	/**
	 * Returns the test case ID of the test case.
	 * @return testCaseID of the test case
	 */
	public String getTestCaseId() {
		return this.testCaseID;
	}
	
	/**
	 * Sets the test case ID if it is valid.
	 * @param testCaseId to set
	 */
	private void setTestCaseId(String testCaseId) {
		if (testCaseId == null || "".equals(testCaseId)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		
		this.testCaseID = testCaseId;
	}
	
	/**
	 * Returns the test case type.
	 * @return testType for the test case
	 */
	public String getTestType() {
		return this.testType;
	}
	
	/**
	 * Sets the test type if it is valid.
	 * @param testType to set
	 * @throws IllegalArgumentException if the testtype is invalid
	 */
	private void setTestType(String testType) {
		if (testType == null || "".equals(testType)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		
		this.testType = testType;
	}
	
	/**
	 * Returns the test description of how to perform the test case.
	 * @return testDescription for how to perform the test case.
	 */
	public String getTestDescription() {
		return this.testDescription;
	}
	
	/**
	 * Sets the test description if it is valid.
	 * @param testDescription to set
	 * @throws IllegalArgumentException if the test description is invalid
	 */
	private void setTestDescription(String testDescription) {
		if (testDescription == null || "".equals(testDescription)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		
		this.testDescription = testDescription;
	}
	
	/**
	 * Returns the expected results of the test case.
	 * @return expectedResults for the test case
	 */
	public String getExpectedResults() {
		return this.expectedResults;
	}
	
	/**
	 * Sets the expected results if they are valid.
	 * @param expectedResults to set
	 * @throws IllegalArgumentException if the expected results are invalid
	 */
	private void setExpectedResults(String expectedResults) {
		if (expectedResults == null || "".equals(expectedResults)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		
		this.expectedResults = expectedResults;
	}
	
	/**
	 * Adds test results to a test case given whether the results were passing or not
	 * and what the actual results of the test were.
	 * @param passing whether the test passed
	 * @param actualResults of the test
	 */
	public void addTestResult(boolean passing, String actualResults) {
		TestResult newResult = new TestResult(passing, actualResults);
		
		testResults.add(newResult);
	}
	
	/**
	 * Checks to see if the test case is passing.
	 * @return whether the test case is passing
	 */
	public boolean isTestCasePassing() {
			return testResults.size() > 0 && testResults.get(testResults.size() - 1).isPassing();
	}
	
	/**
	 * Gets the status of the test case as pass or fail.
	 * @return pass or fail depending on the last actual result
	 */
	public String getStatus() {
		if (isTestCasePassing()) {
			return TestResult.PASS;
		} else {
			return TestResult.FAIL;
		}
	}
	
	/**
	 * Returns the actual results log as a string.
	 * @return String of actual results log
	 */
	public String getActualResultsLog() {
		String log = "";
		for (int i = 0; i < testResults.size(); i++) {
			log += "- " + testResults.get(i).toString() + "\n";
		}
		
		return log;
	}
	
	/**
	 * Sets the test plan that the test case belongs to.
	 * @param testPlan that the test case belongs to
	 * @throws IllegalArgumentException if the test plan is invalid
	 */
	public void setTestPlan(TestPlan testPlan) {
		if (testPlan == null) {
			throw new IllegalArgumentException("Invalid test plan.");
		}
		
		this.testPlan = testPlan;
	}
	
	/**
	 * Gets the test plan that the test case belongs to.
	 * @return testPlan that the test case belongs to
	 */
	public TestPlan getTestPlan() {
		return this.testPlan;
	}
	
	/**
	 * Returns a string representation of a test case.
	 * @return String representation of test case
	 */
	public String toString() {
		return "# " + testCaseID + "," + testType + "\n" + "* "
				+ testDescription + "\n" + "* " + expectedResults + "\n" 
				+ getActualResultsLog();
	}
}