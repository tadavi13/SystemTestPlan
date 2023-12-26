package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * TestPlanWriter class for a test plan writer in the WolfTestCases application. Contains the method
 * to write a list of test plans to a file. Manager class calls this method.
 * 
 * @author Tyler Davis
 */
public class TestPlanWriter {
	/**
	 * Writes a test plans file given a file to write to and a list of plans.
	 * @param testPlanFile to write to
	 * @param testPlans list to write to a file
	 * @throws IllegalArgumentException if unable to save file
	 */
	public static void writeTestPlanFile(File testPlanFile, ISortedList<TestPlan> testPlans) {
		try {
			FileOutputStream stream = new FileOutputStream(testPlanFile);
			PrintWriter writer = new PrintWriter(stream);
			
			for (int i = 0; i < testPlans.size(); i++) {
				TestPlan currentPlan = testPlans.get(i);
				writer.print("! " + currentPlan.getTestPlanName() + "\n");
				for (int t = 0; t < currentPlan.getTestCases().size(); t++) {
					writer.print(currentPlan.getTestCases().get(t).toString());
				}
			}
			
			writer.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}