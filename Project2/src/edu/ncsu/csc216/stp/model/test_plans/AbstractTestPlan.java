package edu.ncsu.csc216.stp.model.test_plans;

import java.util.Objects;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * AbstractTestPlan class for common methods between both TestPlan and FailingTestList. Contains methods
 * for getting the number of failing tests, adding and removing test cases, and getting test cases
 * as an array. An abstract test plan can be either a test plan or failing test list, but no
 * test plan can have the same name.
 * 
 * @author Tyler Davis
 */
public abstract class AbstractTestPlan {
	/** Name of a test plan */
	private String testPlanName;
	/** List of test cases in a plan */
	private ISwapList<TestCase> testCases;
	
	/**
	 * Constructor of an Abstract Test Plan.
	 * @param testPlanName of the test plan
	 */
	public AbstractTestPlan(String testPlanName) {
		setTestPlanName(testPlanName);
		testCases = new SwapList<TestCase>();
	}
	
	/**
	 * Sets the test plan name if its valid.
	 * @param testPlanName of a test plan
	 * @throws IllegalArgumentException if the name is invalid
	 */
	public void setTestPlanName(String testPlanName) {
		if (testPlanName == null || "".equals(testPlanName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		this.testPlanName = testPlanName;
	}
	
	/**
	 * Returns the test plan name of a test plan.
	 * @return testPlanName of a test plan
	 */
	public String getTestPlanName() {
		return this.testPlanName;
	}
	
	/**
	 * Gets the test cases of a test plan.
	 * @return testCases of a test plan
	 */
	public ISwapList<TestCase> getTestCases() {
		return this.testCases;
	}
	
	/**
	 * Adds a test case to a test plan.
	 * @param t a testCase to add
	 */
	public void addTestCase(TestCase t) {
		testCases.add(t);
	}
	
	/**
	 * Removes a test case from a plan given an index to remove.
	 * @param idx of a test case to remove
	 * @return testCase that was removed from the test plan
	 */
	public TestCase removeTestCase(int idx) {
		return testCases.remove(idx);
	}
	
	/**
	 * Returns a test case in a test plan given its index
	 * @param idx of a test plan to return
	 * @return testCase at the index in the list of test cases
	 */
	public TestCase getTestCase(int idx) {
		return testCases.get(idx);
	}
	
	/**
	 * Gets the number of failing tests in a test plan.
	 * @return number of failing tests in a test plan
	 */
	public int getNumberOfFailingTests() {
		int failing = 0;
		for (int i = 0; i < testCases.size(); i++) {
			if (!testCases.get(i).isTestCasePassing()) {
				failing++;
			}
		}
		
		return failing;
	}
	
	/**
	 * Adds a test result to a list of test results.
	 * @param idx index of where to add the test result
	 * @param passing whether the test result was passing
	 * @param actualResults of the test result
	 */
	public void addTestResult(int idx, boolean passing, String actualResults) {
		testCases.get(idx).addTestResult(passing, actualResults);
	}
	
	/**
	 * Abstract method to get a test plans list of test cases as an array.
	 * @return 2D array representation of a test plans test cases
	 */
	public abstract String[][] getTestCasesAsArray();

	/**
	 * Hash code method to return the hashcode of a test plan's name.
	 * @return Hashcode of a test plan name
	 */
	@Override
	public int hashCode() {
		return Objects.hash(testPlanName);
	}

	/**
	 * Equals method to determine if a test plan is equal to another based on test plan name.
	 * @return whether the two objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTestPlan other = (AbstractTestPlan) obj;
		return Objects.equals(testPlanName.toLowerCase(), other.testPlanName.toLowerCase());
	}
}
