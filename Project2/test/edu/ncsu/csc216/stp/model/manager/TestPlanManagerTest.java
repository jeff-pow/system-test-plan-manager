package edu.ncsu.csc216.stp.model.manager;

import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.tests.TestCase;

import java.io.File;

/**
 * Tests TestPlanManager. Many methods are wrappers for methods in other classes to preserve encapsulation of logic.
 * @author Jordan Larose
 */
class TestPlanManagerTest {

	/**
	 * Method ensures getters and setters work as expected. Null and empty strings should throw an exception
	 */
	@Test
	void testTestPlanManager() {
		TestPlanManager testPlanManager = new TestPlanManager();
		assertEquals(testPlanManager.getCurrentTestPlan().getTestPlanName(), FailingTestList.FAILING_TEST_LIST_NAME);
	}

	
//	@Test
//	void testLoadTestPlans() {
//		TestPlanManager testPlanManager = new TestPlanManager();
//		File file = new File("test-files/test-plans0.txt");
//		testPlanManager.loadTestPlans(file);
//		
//		
//		
//	}
//	
//
//	/**
//	 * Method test writing test plans to a file
//	 */
//
//	@Test
//	void testSaveTestPlans() {
//		
//		TestPlanManager testPlanManager = new TestPlanManager();
//		File file = new File("test-files/TEST2.txt");
//		testPlanManager.saveTestPlans(file);
		
		
		
		
	



	/**
	 * Method tests adding a test plan to the program
	 */
	@Test
	void testAddTestPlan() {
		TestPlanManager testPlanManager = new TestPlanManager();
		testPlanManager.addTestPlan("Test1");
		testPlanManager.addTestPlan("Test2");
		testPlanManager.addTestPlan("Test3");
		assertEquals("Test3", testPlanManager.getCurrentTestPlan().getTestPlanName());
		assertThrows(IllegalArgumentException.class, () -> testPlanManager.addTestPlan("Test1"));
		assertThrows(IllegalArgumentException.class, () -> testPlanManager.addTestPlan(null));
		assertThrows(IllegalArgumentException.class, () -> testPlanManager.addTestPlan(""));
	}

	/**
	 * Method tests returning test plan names as an array for use in the GUI
	 */
	@Test
	void testGetTestPlanNames() {
		TestPlanManager testPlanManager = new TestPlanManager();
		testPlanManager.addTestPlan("Test1");
		testPlanManager.addTestPlan("Test2");
		testPlanManager.addTestPlan("Test3");
		String[] arr = testPlanManager.getTestPlanNames();
		assertEquals(4, arr.length);
		assertEquals("Failing Tests", arr[0]);
		assertEquals("Test1", arr[1]);
		assertEquals("Test2", arr[2]);
		assertEquals("Test3", arr[3]);
	}

	/**
	 * Method tests editting current test plan in a variety of ways
	 */
	@Test
	void testEditTestPlan() {
		TestPlanManager manager = new TestPlanManager();
		assertThrows(IllegalArgumentException.class, () -> manager.editTestPlan(""));
	}

	/**
	 * Method tests deleting a test plan
	 */
	@Test
	void testRemoveTestPlan() {
		TestPlanManager testPlanManager = new TestPlanManager();
		testPlanManager.addTestPlan("Test1");
		testPlanManager.addTestPlan("Test2");
		testPlanManager.addTestPlan("Test3");
		testPlanManager.setCurrentTestPlan("Test2");
		testPlanManager.removeTestPlan();
		String[] arr = testPlanManager.getTestPlanNames();
		assertEquals(3, arr.length);
		assertEquals("Failing Tests", arr[0]);
		assertEquals("Test1", arr[1]);
		assertEquals("Test3", arr[2]);
	}

	/**
	 * Method tests addinga  test case from the TestPlanManager class
	 */
	@Test
	void testAddTestCase() {
		TestPlanManager testPlanManager = new TestPlanManager();
		testPlanManager.addTestPlan("Test1");
		testPlanManager.setCurrentTestPlan("Test1");
		testPlanManager.addTestCase(new TestCase("testcaseid", "testtype", "testdescription", "expectedresults"));
		assertEquals("testcaseid", testPlanManager.getCurrentTestPlan().getTestCases().get(0).getTestCaseId());
		assertThrows(IndexOutOfBoundsException.class, () -> testPlanManager.getCurrentTestPlan().getTestCase(1));
	}

	/**
	 * Method tests adding a test result object to a given test case
	 */
	@Test
	void testAddTestResult() {
		TestPlanManager man = new TestPlanManager();
		man.addTestPlan("Test1");
		man.setCurrentTestPlan("Test1");
		man.addTestCase(new TestCase("testid", "testtype", "desc", "exp"));
		man.addTestResult(0, true, "bruh");
		assertEquals(0, man.getCurrentTestPlan().getNumberOfFailingTests());
		man.addTestResult(0, false, "bruh");
		assertEquals(1, man.getCurrentTestPlan().getNumberOfFailingTests());
	}

	/**
	 * Method tests deleting all the test plans in the program
	 */
	@Test
	void testClearTestPlans() {
		TestPlanManager man = new TestPlanManager();
		man.addTestPlan("Test1");
		man.setCurrentTestPlan("Test1");
		man.addTestCase(new TestCase("testid", "testtype", "desc", "exp"));
		man.addTestResult(0, false, "bruh");
		assertEquals(1, man.getCurrentTestPlan().getNumberOfFailingTests());
		
	}

	/**
	 * Method to test adding tests via the testplanmanager
	 */
	@Test
	public void testAdd() {
		TestPlanManager man = new TestPlanManager();
		man.addTestCase(new TestCase("id", "type", "desc", "results"));
		assertFalse(man.isChanged());
	}

	/**
	 * Method to test loading test plans implementing teaching staff test
	 */
	@Test
	public void testLoadTestPlans() {
		TestPlanManager man = new TestPlanManager();
		man.loadTestPlans(new File("test-files/test-plans0.txt"));
		assertEquals("Failing Tests", man.getCurrentTestPlan().getTestPlanName());
		assertEquals(3, man.getTestPlanNames().length);
		assertEquals(3, man.getCurrentTestPlan().getNumberOfFailingTests());
		man.setCurrentTestPlan("PackScheduler");
		assertEquals(3, man.getTestPlanNames().length);
		assertEquals("PackScheduler", man.getCurrentTestPlan().getTestPlanName());
		man.setCurrentTestPlan("WolfScheduler");
		assertEquals(3, man.getTestPlanNames().length);
		assertEquals("WolfScheduler", man.getCurrentTestPlan().getTestPlanName());
		man.loadTestPlans(new File("test-files/test-plans1.txt"));
		assertEquals(3, man.getTestPlanNames().length);
	}

}
