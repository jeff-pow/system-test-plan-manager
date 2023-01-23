/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests TestCase class to ensure functionality
 * @author Jordan Larose
 */
class TestCaseTest {
	/**
	 * Tests getter and setter methods in TestCase class
	 */
	@Test
	void testTestCase() {
		TestCase test = new TestCase("testid", "testtype", "testdescription", "expectedresults");
		assertEquals("testid", test.getTestCaseId());
		assertEquals("testtype", test.getTestType());
		assertEquals("testdescription", test.getTestDescription());
		assertEquals("expectedresults", test.getExpectedResults());
		assertThrows(IllegalArgumentException.class, () -> new TestCase(null, "test", "test", "test"));
		assertThrows(IllegalArgumentException.class, () -> new TestCase("", "test", "test", "test"));
		assertThrows(IllegalArgumentException.class, () -> new TestCase("test", null, "test", "test"));
		assertThrows(IllegalArgumentException.class, () -> new TestCase("test", "", "test", "test"));
		assertThrows(IllegalArgumentException.class, () -> new TestCase("test", "test", null, "test"));
		assertThrows(IllegalArgumentException.class, () -> new TestCase("test", "test", "", "test"));
		assertThrows(IllegalArgumentException.class, () -> new TestCase("test", "test", "test", null));
		assertThrows(IllegalArgumentException.class, () -> new TestCase("test", "test", "test", ""));
	}

	/**
	 * Method tests adding a test result to the log list in TestCase
	 */
	@Test
	void testAddTestResult() {
		TestCase test = new TestCase("testid", "testtype", "testdescription", "expectedresults");
		assertFalse(test.isTestCasePassing());
		test.addTestResult(true, "it worked");
		assertTrue(test.isTestCasePassing());
		assertEquals("PASS", test.getStatus());
		test.addTestResult(false, "it didn't work");
		assertFalse(test.isTestCasePassing());
		assertEquals("FAIL", test.getStatus());
	}


	/**
	 * Method tests turning a test case and its test results in a string based on the requirements
	 */
	@Test
	void testToString() {
		TestCase test = new TestCase("testid", "testtype", "testdescription", "expectedresults");
		test.addTestResult(true, "it worked");
		test.addTestResult(false, "it didn't work");
		String s =
				"# testid,testtype\n" +
						"* testdescription\n" +
						"* expectedresults\n" +
						"- PASS: it worked\n" +
						"- FAIL: it didn't work\n";
		assertEquals(s, test.toString());
	}

}
