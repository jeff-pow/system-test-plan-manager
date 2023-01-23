/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests TestResult
 * @author Jordan Larose
 */
class TestResultTest {

	/**
	 * Method ensures getters and setters work as expected
	 */
	@Test
	void testTestResult() {
		TestResult result = new TestResult(true, "test");
        assertTrue(result.isPassing());
        assertEquals("test", result.getActualResults());
		TestResult result1 = new TestResult(false, "test");
		assertFalse(result1.isPassing());
		assertEquals("test", result1.getActualResults());
	}

	/**
	 * Method ensures that the test result is turned in a string and formatted correctly
	 */
	@Test
	void testToString() {
		TestResult result = new TestResult(true, "test");
		TestResult result1 = new TestResult(false, "test");
		assertEquals("PASS: test", result.toString());
		assertEquals("FAIL: test", result1.toString());
	}

}
