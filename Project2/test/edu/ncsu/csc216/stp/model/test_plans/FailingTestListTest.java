/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests FailingTestList
 * @author Jordan Larose
 */
class FailingTestListTest {
	/** Instance of TestPlan */
	private FailingTestList test;

	/**
	 * Methd ensures a test case can be added to the failing test list
	 */
	@Test
	void testAddTestCase() {
		test = new FailingTestList();
		TestCase ts1 = new TestCase("testcaseid", "testtype", "testdescription", "expectedresults");
		TestCase ts2 = new TestCase("testcaseid2", "testtype", "testdescription", "expectedresults");
		TestCase ts3 = new TestCase("testcaseid3", "testtype", "testdescription", "expectedresults");
		ts1.addTestResult(false, "testresults");
		ts2.addTestResult(false, "testresults");
		ts3.addTestResult(false, "testresults");
		ts3.setTestPlan(new TestPlan("TestPlan"));
		test.addTestCase(ts1);
		test.addTestCase(ts2);
		test.addTestCase(ts3);
		String[][] arr = test.getTestCasesAsArray();
		assertEquals(3, arr.length);
		arr[0][0] = "testcaseid";
		arr[0][1] = "testtype";
		arr[0][2] = "";
		arr[1][0] = "testcaseid1";
		arr[1][1] = "testtype";
		arr[1][2] = "";
		arr[2][0] = "testcaseid2";
		arr[2][1] = "testtype";
		arr[2][2] = "TestPlan";
	}

	/**
	 * Methd ensures a failing test list can be constructed successfully and its name is equal to the class constant FAILING_TESTS_LABEL
	 */
	@Test
	void testFailingTestList() {
		test = new FailingTestList();
		assertEquals(FailingTestList.FAILING_TEST_LIST_NAME, test.getTestPlanName());
		assertThrows(IllegalArgumentException.class, () -> test.setTestPlanName("failing tests"));
	}

	/**
	 * Method replicates teaching staff adding to failing test list
	 */
	@Test
	public void testAdd() {
		FailingTestList list = new FailingTestList();
		list.addTestCase(new TestCase("ID1", "type1", "description1", "expected1"));
		list.addTestCase(new TestCase("ID2", "type2", "description2", "expected2"));
		list.addTestCase(new TestCase("ID3", "type3", "description3", "expected3"));
		list.addTestCase(new TestCase("ID4", "type4", "description4", "expected4"));
		assertEquals(4, list.getNumberOfFailingTests());
		list.clearTests();
		assertEquals(0, list.getTestCasesAsArray().length);
		TestCase ts = new TestCase("ID5", "type5", "description5", "expected5");
		ts.addTestResult(true, "result");
		assertThrows(IllegalArgumentException.class, () -> list.addTestCase(ts));
	}


}