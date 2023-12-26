package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * TestPlanManager class for the main actions performed by users in the WolfTestCases application.
 * Contains methods for loading and saving files, adding and removing test plans, setting the current
 * test plan, and adding test cases and results. The manager is what performs actions for the GUI.
 *
 * @author Tyler Davis
 */
public class TestPlanManager {
	/** Whether the manager has changed since last save */
	private boolean isChanged;
	/** SortedList of test plans in the manager */
	private ISortedList<TestPlan> testPlans;
	/** FailingTestList for the list of failing tests across all plans */
	private FailingTestList failingTestList;
	/** Current test plan being displayed in the manager */
	private AbstractTestPlan currentTestPlan;
	
	/**
	 * Constructor for the TestPlanManager.
	 */
	public TestPlanManager() {
		clearTestPlans();
	}
	
	/**
	 * Loads test plans from a file.
	 * @param testPlanFile to load plans from
	 */
	public void loadTestPlans(File testPlanFile) {
		ISortedList<TestPlan> tests = TestPlanReader.readTestPlansFile(testPlanFile);
		
		for (int i = 0; i < tests.size(); i++) {
			try {
				testPlans.add(tests.get(i));
			} catch (IllegalArgumentException e) {
				setCurrentTestPlan("Failing Tests");
			}
		}
		
		setCurrentTestPlan("Failing Tests");
		isChanged = true;
	}
	
	/**
	 * Saves plans to a file.
	 * @param testPlanFile to save plans to
	 */
	public void saveTestPlans(File testPlanFile) {
		TestPlanWriter.writeTestPlanFile(testPlanFile, testPlans);
		isChanged = false;
	}
	
	/**
	 * Returns whether the manager has changed since last save.
	 * @return whether the manager has changed since last save
	 */
	public boolean isChanged() {
		return this.isChanged;
	}
	
	/**
	 * Add a test plan to the manager given the name for one.
	 * @param testPlanName for a plan to add
	 * @throws IllegalArgumentException if name is a duplicate
	 */
	public void addTestPlan(String testPlanName) {
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().toLowerCase().equals(testPlanName.toLowerCase())) { 
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		TestPlan newPlan = new TestPlan(testPlanName);
		testPlans.add(newPlan);
		setCurrentTestPlan(testPlanName);
		isChanged = true;
	}
	
	/**
	 * Get the names of the test plans in the manager as a string array.
	 * @return array of the test plans names in the manager
	 */
	public String[] getTestPlanNames() {
		String[] testPlanNames = new String[testPlans.size() + 1];
		for (int i = 0; i < testPlans.size() + 1; i++) {
			if (i == 0) {
				testPlanNames[i] = failingTestList.getTestPlanName();
			} else {
				testPlanNames[i] = testPlans.get(i - 1).getTestPlanName();
			}
		}
		
		return testPlanNames;
	}
	
	/**
	 * Gets the failing tests in the manager.
	 */
	private void getFailingTests() {
		failingTestList = new FailingTestList();
		
		for (int i = 0; i < testPlans.size(); i++) {
			for (int t = 0; t < testPlans.get(i).getTestCases().size(); t++) {
				if (!testPlans.get(i).getTestCases().get(t).isTestCasePassing()) {
					failingTestList.addTestCase(testPlans.get(i).getTestCases().get(t));
				}
			}
		}
	}
	
	/**
	 * Sets the current test plan in the manager.
	 * @param testPlanName of the current test plan to set
	 */
	public void setCurrentTestPlan(String testPlanName) {
		boolean found = false;
		
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().equals(testPlanName)) {
				currentTestPlan = testPlans.get(i);
				found = true;
			}
		}
		
		if (!found) {
			getFailingTests();
			currentTestPlan = failingTestList;
		}
	}
	
	/**
	 * Gets the current test plan in the manager.
	 * @return currentTestPlan in the manager
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		return this.currentTestPlan;
	}
	
	/**
	 * Edits the current test plan by assigning a new name.
	 * @param testPlanName for the test being edited
	 * @throws IllegalArgumentException if failing tests list is current test plan
	 * @throws IllegalArgumentException if test plan name is already taken
	 * @throws IllegalArgumentException if failing tests list name is entered
	 */
	public void editTestPlan(String testPlanName) {
		if (currentTestPlan == failingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		}
		
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().toLowerCase().equals(testPlanName.toLowerCase())) { 
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		if (testPlanName.toLowerCase().equals(FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		currentTestPlan.setTestPlanName(testPlanName);
		isChanged = true;
	}
	
	/**
	 * Removes the current test plan in the manager.
	 * @throws IllegalArgumentException if the failing test list is the current test plan.
	 */
	public void removeTestPlan() {
		if (currentTestPlan == failingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		}
		
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().toLowerCase().equals(currentTestPlan.getTestPlanName().toLowerCase())) { 
				testPlans.remove(i);
			}
		}
		
		currentTestPlan = failingTestList;
		isChanged = true;
	}
	
	/**
	 * Add a test case to the current test plan in the manager.
	 * @param t test case to add
	 */
	public void addTestCase(TestCase t) {
		if (currentTestPlan != failingTestList) {
			currentTestPlan.addTestCase(t);
			isChanged = true;
		}

		if (!t.isTestCasePassing()) {
			getFailingTests();
		}
	}
	
	/**
	 * Add test result to a test case in the current test plan in the manager.
	 * @param idx index of test case to add to.
	 * @param passing whether the test result was passing or not
	 * @param actualResults of the test performed
	 */
	public void addTestResult(int idx, boolean passing, String actualResults) {
		currentTestPlan.getTestCase(idx).addTestResult(passing, actualResults);
		
		if (!passing) {
			getFailingTests();
		}
		
		isChanged = true;
	}
	
	/**
	 * Clears all the test plans in the manager.
	 */
	public void clearTestPlans() {
		testPlans = new SortedList<TestPlan>();
		failingTestList = new FailingTestList();
		currentTestPlan = failingTestList;
		isChanged = false;
	}
 }
