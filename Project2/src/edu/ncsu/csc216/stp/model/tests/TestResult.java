package edu.ncsu.csc216.stp.model.tests;

/**
 * Test Result class represents an individual result of a test case being run
 * in the WolfTestCases application. A test result contains whether it passed
 * or not and the actual result of that test. Contains getter and setter methods
 * as well as the toStirng method for a string representation of a test result.
 */
public class TestResult {
	/** Constant string value for a pass result */
	public static final String PASS = "PASS";
	/** Constant string value for a fail result */
	public static final String FAIL = "FAIL";
	/** Whether a test passed or not */
	private boolean passing;
	/** What the actual results of a test were */
	private String actualResults;
	
	/**
	 * Constructor of a Test Result.
	 * @param passing whether a test is passing or not
	 * @param actualResults of a test
	 */
	public TestResult(boolean passing, String actualResults) {
		setActualResults(actualResults);
		setPassing(passing);
	}
	
	/**
	 * Returns the actual results of a test.
	 * @return actual results of a test
	 */
	public String getActualResults() {
		return this.actualResults;
	}
	
	/**
	 * Sets the actual results of a test if they are valid.
	 * @param actualResults of a test
	 * @throws IllegalArgumentException if the test results are invalid
	 */
	private void setActualResults(String actualResults) {
		if (actualResults == null || "".equals(actualResults)) {
			throw new IllegalArgumentException("Invalid test results.");
		}
		
		this.actualResults = actualResults;
	}
	
	/**
	 * Returns whether a test is passing or not
	 * @return whether a test is passing or not
	 */
	public boolean isPassing() {
		return this.passing;
	}
	
	/**
	 * Sets whether a test is passing or not if it is valid.
	 * @param passing whether a test is passing or not
	 */
	private void setPassing(boolean passing) {
		this.passing = passing;
	}
	
	/**
	 * Returns the string form of a test result.
	 * @return String representation of TestResult
	 */
	public String toString() {
		if (passing) {
			return PASS + ": " + actualResults;
		} else {
			return FAIL + ": " + actualResults;
		}
	}
}
