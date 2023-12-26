package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * FailingTestList class for the list of failing tests in all the test plans of the WolfTestCases
 * application. Has methods for adding test cases, setting the failing tests list name, getting the
 * test cases as a 2D array, and clearing the tests. FailingTestList will always have the name
 * "Failing Tests".
 * 
 * @author Tyler Davis
 */
public class FailingTestList extends AbstractTestPlan {
	/** Constant for the name of the FailingTestList */
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";
	
	/**
	 * FailingTestList constructor. Calls the super constructor with constant name.
	 */
	public FailingTestList() {
		super(FAILING_TEST_LIST_NAME);
	}
	
	/**
	 * Adds a test case to the failing tests list.
	 * @param t a test case to add
	 * @throws IllegalArgumentException if a passing test is attempted to be added
	 */
	@Override
	public void addTestCase(TestCase t) {
		if (t.isTestCasePassing()) {
			throw new IllegalArgumentException("Cannot add passing test case.");
		}
		
		super.addTestCase(t);
	}
	
	/**
	 * Sets the test plan name if it is valid.
	 * @param testPlanName of failing test list
	 * @throws IllegalArgumentException if the failing test list name is attempted
	 * to be edited
	 */
	@Override
	public void setTestPlanName(String testPlanName) {
		if (!testPlanName.toLowerCase().equals(FAILING_TEST_LIST_NAME.toLowerCase())) {
			throw new IllegalArgumentException("The Failing Tests list cannot be edited.");
		}
		
		super.setTestPlanName(FAILING_TEST_LIST_NAME);
	}
	
	/**
	 * Gets the 2D display array of the test cases in the failing test list.
	 * @return 2D display array for test cases.
	 */
	public String[][] getTestCasesAsArray() {
		int size = super.getTestCases().size();
		String[][] testCaseArray = new String[size][3];
		
		for (int i = 0; i < size; i++) {
			testCaseArray[i][0] = super.getTestCases().get(i).getTestCaseId();
			testCaseArray[i][1] = super.getTestCases().get(i).getTestType();
			if (super.getTestCases().get(i).getTestPlan() == null) {
				testCaseArray[i][2] = "";
			} else {
				testCaseArray[i][2] = super.getTestCases().get(i).getTestPlan().getTestPlanName();
			}
		}
		
		return testCaseArray;
	}
	
	/**
	 * Clears the tests in the failing tests list.
	 */
	public void clearTests() {
		int size = super.getTestCases().size();
		for (int i = 0; i < size; i++) {
			super.removeTestCase(0);
		}
	}
}
