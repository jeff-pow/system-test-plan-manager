/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests AbstractTestPlan
 * @author Jordan Larose
 * @author Jeff Powell
 */
class AbstractTestPlanTest {

	/**
	 * Method ensures a unique hashCode is generated for each object in the test plan
	 */
	@Test
	void testHashCode() {
		AbstractTestPlan plan1 = new TestPlan("title");
		AbstractTestPlan plan2 = new TestPlan("TITLE");
		assertEquals(plan1.hashCode(), plan2.hashCode());
		AbstractTestPlan plan3 = new TestPlan("different");
		assertNotEquals(plan1.hashCode(), plan3.hashCode());
	}

	/**
	 * Method tests getters and setters for AbstractTestPlan
	 */
	@Test
	void testAbstractTestPlan() {
		AbstractTestPlan plan = new TestPlan("title");
		assertEquals("title", plan.getTestPlanName());
		assertThrows(IllegalArgumentException.class, () -> new TestPlan(""));
		assertThrows(IllegalArgumentException.class, () -> new TestPlan(null));
	}

	/**
	 * Method ensures a test case can be added to the test plan successfully
	 */
	@Test
	void testAddTestCase() {
		AbstractTestPlan plan = new TestPlan("title");
		plan.addTestCase(new TestCase("testcaseid", "testtype", "testdescription", "expectedresults"));
        assertEquals(1, plan.getTestCases().size());
	}

	/**
	 * Method ensures a test case can be removed from the test plan successfully
	 */
	@Test
	void testRemoveTestCase() {
		AbstractTestPlan plan = new TestPlan("title");
		plan.addTestCase(new TestCase("testcaseid", "testtype", "testdescription", "expectedresults"));
		assertEquals(1, plan.getTestCases().size());
		assertThrows(IndexOutOfBoundsException.class, () -> plan.removeTestCase(7));
		plan.removeTestCase(0);
		assertEquals(0, plan.getTestCases().size());
	}

	/**
	 * Method ensures a test case can be fetched from the test plan successfully
	 */
	@Test
	void testGetTestCase() {
		AbstractTestPlan plan = new TestPlan("title");
        plan.addTestCase(new TestCase("testcaseid", "testtype", "testdescription", "expectedresults"));
		plan.addTestCase(new TestCase("testcaseid1", "testtype", "testdescription", "expectedresults"));
		assertEquals("testcaseid1", plan.getTestCase(1).getTestCaseId());
	}

	/**
	 * Method ensures a test plan can count its number of failing tests
	 */
	@Test
	void testGetNumberOfFailingTests() {
		AbstractTestPlan plan = new TestPlan("title");
		plan.addTestCase(new TestCase("testcaseid", "testtype", "testdescription", "expectedresults"));
		plan.addTestResult(0, true, "worked");
		plan.addTestCase(new TestCase("testcaseid1", "testtype", "testdescription", "expectedresults"));
		plan.addTestResult(1, false, "didnt work");
		assertEquals(1, plan.getNumberOfFailingTests());
		plan.addTestCase(new TestCase("testcaseid2", "testtype", "testdescription", "expectedresults"));
		plan.addTestResult(2, false, "didnt work");
		assertFalse(plan.getTestCase(2).isTestCasePassing());
		assertEquals(2, plan.getNumberOfFailingTests());
	}

	/**
	 * Ensures two tests in a test plan are not the same so duplicate tests are not allowed in the same test plan
	 */
	@Test
	void testEqualsObject() {
		AbstractTestPlan plan1 = new TestPlan("title");
		AbstractTestPlan plan2 = new TestPlan("TITLE");
		assertTrue(plan1.equals(plan2));
		AbstractTestPlan plan3 = new TestPlan("different");
		assertFalse(plan1.equals(plan3));
	}

}
