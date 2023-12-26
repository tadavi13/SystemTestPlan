package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * TestPlan class for a test plan that is not the FailingTestsList. Contains methods such as
 * the constructor, the implementation of getTestCasesAsArray, adding a test case, and
 * comparing test plans based on names. A test plan cannot have the name "Failing Tests".
 * 
 * @author Tyler Davis
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {
	/**
	 * Constructor for a TestPlan. Calls the super constructor.
	 * @param testPlanName for the test plan
	 * @throws IllegalArgumentException if the name given is the failing test list name
	 */
	public TestPlan(String testPlanName) {
		super(testPlanName);
		
		if (testPlanName.toLowerCase().equals(FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
	}
	
	/**
	 * Gets the test cases of a test plan as a 2D array.
	 * @return 2D array of test cases
	 */
	public String[][] getTestCasesAsArray() {
		int size = super.getTestCases().size();
		String[][] testCaseArray = new String[size][3];
		
		for (int i = 0; i < size; i++) {
			testCaseArray[i][0] = super.getTestCases().get(i).getTestCaseId();
			testCaseArray[i][1] = super.getTestCases().get(i).getTestType();
			testCaseArray[i][2] = super.getTestCases().get(i).getStatus();
		}
		
		return testCaseArray;
	}
	
	/**
	 * Add a test case to a test plan.
	 * @param t test case to add
	 */
	@Override
	public void addTestCase(TestCase t) {
		super.addTestCase(t);
		t.setTestPlan(this);
	}
	
	/**
	 * Compares a test plan to another test plan based on name.
	 * @param testPlan to compare another one to
	 * @return whether the testPlan is the same to the object being compared to
	 */
	public int compareTo(TestPlan testPlan) {
		return this.getTestPlanName().toLowerCase().compareTo(testPlan.getTestPlanName().toLowerCase());
	}
}
