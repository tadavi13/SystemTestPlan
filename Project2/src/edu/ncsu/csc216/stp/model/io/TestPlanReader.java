package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.tests.TestResult;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * TestPlanReader class for a test plan reader. Contains methods to read test plan files into a list of
 * test plans and associated helper methods to process test plan and test case lines. Manager calls
 * the reading of a file.
 * 
 * @author Tyler Davis
 */
public class TestPlanReader {
	/**
	 * Reads in a test plan file into a sorted list of test plans.
	 * @param testPlanFile to read in
	 * @return ISortedList of test plans
	 * @throws IllegalArgumentException if unable to load file
	 */
	public static ISortedList<TestPlan> readTestPlansFile(File testPlanFile) {
		try {
			FileInputStream stream = new FileInputStream(testPlanFile);
			Scanner scan = new Scanner(stream);
			String fileContents = "";
			while (scan.hasNextLine()) {
				fileContents += scan.nextLine() + "\n";
			}
			
			if (fileContents.charAt(0) != '!') {
				scan.close();
				throw new IllegalArgumentException("Unable to load file.");
			}
			
			SortedList<TestPlan> testPlans = new SortedList<TestPlan>();
			Scanner planScan = new Scanner(fileContents);
			planScan.useDelimiter("\\r?\\n?[!]");
			while (planScan.hasNext()) {
				String testPlanLine = planScan.next().trim();
				TestPlan newPlan = processTestPlan(testPlanLine);
				testPlans.add(newPlan);
			}
			
			scan.close();
			planScan.close();
			return testPlans;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}
	
	/**
	 * Processes a test plan given a test plan line as a string.
	 * @param line to process
	 * @return test plan object from the information in the line
	 */
	private static TestPlan processTestPlan(String line) {
		Scanner planScan = new Scanner(line);
		String testPlanName = planScan.nextLine().trim();
		TestPlan newPlan = new TestPlan(testPlanName);
		planScan.useDelimiter("\\r?\\n?[#]");
		
		while (planScan.hasNext()) {
			String testCaseLine = planScan.next().trim();
			TestCase newCase = processTest(newPlan, testCaseLine);
			if (newCase != null) {
				newPlan.addTestCase(newCase);
			}
		}
		
		planScan.close();
		return newPlan;
	}
	
	/**
	 * Processes a test case given a testPlan it is in and a line.
	 * @param testPlan the test case belongs to
	 * @param line representing a test case
	 * @return test case object from the information in the line
	 */
	private static TestCase processTest(AbstractTestPlan testPlan, String line) {
		Scanner testScan = new Scanner(line);
		
		Scanner firstLineScan = null;
		if (testScan.hasNextLine()) {
			String firstLine = testScan.nextLine();
			firstLineScan = new Scanner(firstLine);
		} else {
			testScan.close();
			return null;
		}
		firstLineScan.useDelimiter(",");
		String testCaseID = "";
		String testType = "";
		if (firstLineScan.hasNext()) {
			testCaseID = firstLineScan.next().trim();
		}
		if (firstLineScan.hasNext()) {
			testType = firstLineScan.next().trim();
		}
		
		testScan.useDelimiter("\\r?\\n?[-]");
		String descripExpect = "";
		if (testScan.hasNext()) {
			descripExpect = testScan.next();
		} 
		String actual = "";
		while (testScan.hasNext()) {
			actual += "- " + testScan.next() + "\n";
		}
		
		Scanner descripExpectScan = new Scanner(descripExpect);
		descripExpectScan.useDelimiter("\\r?\\n?[*]");
		String description = "";
		if (descripExpectScan.hasNext()) {
			description = descripExpectScan.next().trim();
		}
		String expected = "";
		if (descripExpectScan.hasNext()) {
			expected = descripExpectScan.next().trim();
		}
		TestCase newCase = null;
		try {
			newCase = new TestCase(testCaseID, testType, description, expected);
		} catch (IllegalArgumentException e) {
			testScan.close();
			firstLineScan.close();
			descripExpectScan.close();
			return newCase;
		}
		
		Scanner actualScan = new Scanner(actual);
		actualScan.useDelimiter("\\r?\\n?[-]");
		while (actualScan.hasNext()) {
			String result = actualScan.next().trim();
			Scanner resultScan = new Scanner(result);
			resultScan.useDelimiter("\\r?\\n?[:]");
			String passing = "";
			if (resultScan.hasNext()) {
				passing = resultScan.next().trim();
			}
			String actualResults = "";
			if (resultScan.hasNext()) {
				actualResults = resultScan.next().trim();
			} else {
				testScan.close();
				firstLineScan.close();
				descripExpectScan.close();
				actualScan.close();
				resultScan.close();
				return null;
			}
			
			if (passing.equals(TestResult.PASS)) {
				newCase.addTestResult(true, actualResults);
			} else if (passing.equals(TestResult.FAIL)) {
				newCase.addTestResult(false, actualResults);
			} else {
				testScan.close();
				firstLineScan.close();
				descripExpectScan.close();
				actualScan.close();
				resultScan.close();
				return null;
			}
			resultScan.close();
		}
		
		testScan.close();
		firstLineScan.close();
		descripExpectScan.close();
		actualScan.close();
		return newCase;
	}
}
