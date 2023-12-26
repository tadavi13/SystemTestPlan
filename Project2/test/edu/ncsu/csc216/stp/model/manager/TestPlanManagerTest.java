package edu.ncsu.csc216.stp.model.manager;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;


import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Test class for TestPlanManager.
 */
public class TestPlanManagerTest {
	/**
	 * Tests adding test plans to a manager
	 */
	@Test
	public void testAddTestPlan() {
		TestPlanManager manager = new TestPlanManager();
		
		assertDoesNotThrow(() -> manager.addTestPlan("TestPlan1"));
		assertDoesNotThrow(() -> manager.addTestPlan("TestPlan2"));
	}
	
	/**
	 * Tests editing test plans in a manager
	 */
	@Test
	public void testEditTestPlan() {
		TestPlanManager manager = new TestPlanManager();
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> manager.editTestPlan("NewTestPlan"));
		assertEquals("The Failing Tests list may not be edited.", e.getMessage());
		
		assertDoesNotThrow(() -> manager.addTestPlan("TestPlan1"));
		assertDoesNotThrow(() -> manager.setCurrentTestPlan("TestPlan1"));
		e = assertThrows(IllegalArgumentException.class, () -> manager.editTestPlan("Failing Tests"));
		assertEquals("Invalid name.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> manager.editTestPlan("TestPlan1"));
		assertEquals("Invalid name.", e.getMessage());
		
		assertDoesNotThrow(() -> manager.addTestPlan("TestPlan2"));
		assertDoesNotThrow(() -> manager.setCurrentTestPlan("TestPlan2"));
		e = assertThrows(IllegalArgumentException.class, () -> manager.editTestPlan("TestPlan1"));
		assertEquals("Invalid name.", e.getMessage());
	}
	
	/**
	 * Tests removing test plans in a manager
	 */
	@Test
	public void testRemoveTestPlan() {
		TestPlanManager manager = new TestPlanManager();
		
		assertDoesNotThrow(() -> manager.addTestPlan("TestPlan1"));
		assertDoesNotThrow(() -> manager.addTestPlan("TestPlan2"));
		assertDoesNotThrow(() -> manager.setCurrentTestPlan("TestPlan1"));
		assertDoesNotThrow(() -> manager.removeTestPlan());
		assertDoesNotThrow(() -> manager.setCurrentTestPlan("TestPlan2"));
		assertDoesNotThrow(() -> manager.removeTestPlan());
		assertDoesNotThrow(() -> manager.setCurrentTestPlan("Failing Tests"));
		Exception e = assertThrows(IllegalArgumentException.class, () -> manager.removeTestPlan());
		assertEquals("The Failing Tests list may not be deleted.", e.getMessage());
	}
	
	/**
	 * Tests adding test cases in a manager
	 */
	@Test
	public void testAddTestCase() {
		TestPlanManager manager = new TestPlanManager();
		TestCase newCase = new TestCase("Test", "Test", "Test", "Test");
		
		assertDoesNotThrow(() -> manager.addTestPlan("TestPlan1"));
		assertDoesNotThrow(() -> manager.addTestCase(newCase));
		assertDoesNotThrow(() -> manager.addTestResult(0, true, "Passing"));
		assertDoesNotThrow(() -> manager.setCurrentTestPlan("Failing Tests"));
//		Exception e = assertThrows(IllegalArgumentException.class, () -> manager.addTestCase(newCase));
//		assertEquals("Cannot add passing test case.", e.getMessage());
	}


	/**
	 * Tests getting the test plan names method in manager.
	 */
	@Test
	public void testGetTestPlanNames() {
		File file = new File("test_files/test-plans0.txt");
		TestPlanManager manager = new TestPlanManager();
		
		manager.loadTestPlans(file);
		String[] testPlans = manager.getTestPlanNames();
		assertEquals("Failing Tests", testPlans[0]);
		assertEquals("PackScheduler", testPlans[1]);
		assertEquals("WolfScheduler", testPlans[2]);
	}
}
