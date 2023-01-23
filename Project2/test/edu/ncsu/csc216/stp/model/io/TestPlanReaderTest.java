/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import static edu.ncsu.csc216.stp.model.io.TestPlanReader.readTestPlansFile;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

//import edu.ncsu.csc216.stp.model.util.SwapList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.manager.TestPlanManager;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Tests TestPlanReader
 * @author Jordan Larose
 */
class TestPlanReaderTest { 
	
	/** test file 0 */
	private final String testFile0 = "test-files/test-plans0.txt";
//	/** test file 1 */
//	private final String testFile3 = "test-files/test-plans3.txt";
	/** test file */
	private final File f = new File("");
	/** test file */
	private final File f2 = new File("test-files/test-plans3.txt");
	


	
	/**
	 * Method tests reading a valid test file containing several tests in various projects
	 */
	@Test
	void testReadTestPlansFile() {
		
		assertThrows(IllegalArgumentException.class, () -> readTestPlansFile(f));
		assertThrows(IllegalArgumentException.class, () -> readTestPlansFile(f2));
		
		
		File file = new File(testFile0);
		SortedList<TestPlan> list = (SortedList<TestPlan>) readTestPlansFile(file);
		
		assertEquals(2, list.size());
		
		
		TestPlan tp1 = list.get(0);
		assertEquals(tp1.getTestPlanName(), "PackScheduler");
		String[][] testCaseArray = tp1.getTestCasesAsArray();
		
		TestCase tc2 = tp1.getTestCase(1);
		assertEquals(tc2.getTestType(), "Equivalence Class");
		assertEquals(tc2.getTestCaseId(), "test1");
		
//		String tcId = testCaseArray[1][0];
//		assertEquals("test1", tcId);
//		
//		String tcType = testCaseArray[0][1];
//		assertEquals("Invalid", tcType);
		
		TestCase tc = tp1.getTestCase(0);
		tc.getExpectedResults();
		assertEquals(tc.getTestCaseId(), "test0");
		assertEquals(tc.getTestType(), "Invalid");
//		assertEquals("expected results\nwith multiple lines", tc.getExpectedResults());
		
		
		
		TestPlanManager tpm = new TestPlanManager();
		tpm.loadTestPlans(file);
		File test = new File("test-files/TEST.txt");
		tpm.saveTestPlans(test);
		
		
		
		
	}

	/*
	@Test
	public void tesss() {
		TestPlanReader.readTestPlansFile(new File("test-files/test-plans4.txt"));
		TestPlanReader.readTestPlansFile(new File("test-files/test-plans5.txt"));
		TestPlanReader.readTestPlansFile(new File("test-files/test-plans6.txt"));
		TestPlanReader.readTestPlansFile(new File("test-files/test-plans7.txt"));
	}
	*/


}
