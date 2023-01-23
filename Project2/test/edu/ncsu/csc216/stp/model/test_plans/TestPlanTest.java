/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Tests TestPlan
 * @author Jordan Larose
 */
class TestPlanTest {
	/** Instance of TestPlan */
	private TestPlan test;
	
	/** test file 0 */
	private final String testFile0 = "test-files/test-plans0.txt";

	/**
	 * Method ensures a test plan can add a test result to a specific test case
	 */
	@Test
	void testAddTestCase() {
		test = new TestPlan("title");
		test.addTestCase(new TestCase("testcaseid", "testtype", "testdescription", "expectedresults"));
        assertEquals(1, test.getTestCases().size());
	}

	/**
	 * Method ensures a test plan can turn its tests into an array for use in the GUI
	 */
	@Test
	void testGetTestCasesAsArray() {
		
		File file = new File(testFile0);
		SortedList<TestPlan> list = (SortedList<TestPlan>) TestPlanReader.readTestPlansFile(file);
		assertEquals(2, list.size());
		
		
		TestPlan tp1 = list.get(0);
		String[][] testCaseArray = tp1.getTestCasesAsArray();
		
		String tcId = testCaseArray[1][0];
		assertEquals("test1", tcId);
		
		String tcType = testCaseArray[0][1];
		assertEquals("Invalid", tcType);

	}

	/**
	 * Method ensures getters and setters work as expected. Null and empty strings should throw exceptions
	 */
	@Test
	void testTestPlan() {
		try {
			test = new TestPlan("Failing Tests");
		} catch(IllegalArgumentException e) {
			assertEquals(null, test);
		}
	}

	/**
	 * Ensures two tests in a test plan are not the same so duplicate tests are not allowed in the same test plan
	 */
	@Test
	void testCompareTo() {
		TestPlan test1 = new TestPlan("title");
		TestPlan test2 = new TestPlan("Title");
		TestPlan test3 = new TestPlan("Jordan");
		
		assertEquals(0, test1.compareTo(test2));
		assertEquals(1, test1.compareTo(test3));
		assertEquals(1, test2.compareTo(test3));
	}

	/**
	 * Recreation of teaching staff test add test case
	 */
	@Test
	public void teachingTestAddTestCase() {
		TestPlan plan = new TestPlan("Test Plan 1");
		plan.addTestCase(new TestCase("ID1", "type1", "desc1", "expected1"));
		plan.addTestCase(new TestCase("ID2", "type2", "desc2", "expected2"));
		plan.addTestCase(new TestCase("ID3", "type3", "desc3", "expected3"));
		plan.addTestCase(new TestCase("ID4", "type4", "desc4", "expected4"));
		plan.addTestCase(new TestCase("ID5", "type5", "desc5", "expected5"));
		assertEquals(5, plan.getTestCases().size());
		plan.getTestCases().moveUp(1);
		assertEquals("ID3", plan.getTestCase(2).getTestCaseId());
		plan.getTestCases().moveDown(2);
		assertEquals("ID4", plan.getTestCase(2).getTestCaseId());
		plan.getTestCases().moveToFront(2);
		assertEquals(plan.getTestCase(0).getTestCaseId(), "ID4");
		plan.getTestCases().moveToBack(0);
		assertEquals("ID4", plan.getTestCase(4).getTestCaseId());
		assertEquals("ID2", plan.getTestCase(0).getTestCaseId());
	}

}
