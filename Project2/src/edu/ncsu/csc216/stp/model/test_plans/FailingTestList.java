
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * Method contains a list of all currently failing system tests in the program. User cannot interact with any of these tests through this list,
 * simply displays them.
 * @author Jeff Powell
 * @author Jordan Larose
 */
public class FailingTestList extends AbstractTestPlan {
	/** Permenant title of failing test list */
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";
	/**List containing the Failing Test Cases in the particular test plan */
	private SwapList<TestCase> failingTestList;


	/**
	 * Method creates a failing test list object and makes the title "Failing Tests"
	 */
	public FailingTestList() {
		super(FAILING_TEST_LIST_NAME);
		failingTestList = new SwapList<>();
	}

	/**
	 * Method returns the test cases contained in FailingTestPlan and returns them as an array so GUI can list them all out.
	 * @return Array of strings containing TestCases in the failing test plan
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		String[][] testCaseArray = new String[failingTestList.size()][3];
		for(int i = 0; i < failingTestList.size(); i++) {
			TestCase t = failingTestList.get(i);
			testCaseArray[i][0] = t.getTestCaseId();
			testCaseArray[i][1] = t.getTestType();
			if (failingTestList.get(i).getTestPlan() == null) {
				testCaseArray[i][2] = "";
			}
			else {
				testCaseArray[i][2] = failingTestList.get(i).getTestPlan().getTestPlanName();
			}
		}
		return testCaseArray;
	}

	/**
	 * Method changes the title of a test plan. Ensures string provided is equivalent to FAILING_TEST_LIST_NAME (case-insensitive)
	 * @param name What to title the test plan
	 */
	@Override
	public void setTestPlanName(String name) {
		if (name.toLowerCase().equals(getTestPlanName().toLowerCase())) {
			super.setTestPlanName(name);
		}
		else {
			throw new IllegalArgumentException("The Failing Tests list cannot be edited.");
		}
	}

	/**
	 * Method deletes all tests within the failing tests list
	 */
	public void clearTests() {
		failingTestList = new SwapList<>();
	}

	/**
	 * Ensures a test case is failing before adding it to the list of test cases. If test case is not failing, not added
	 * @param t Test case to be added to the list of test cases
	 * @throws IllegalArgumentException if test case is passing
	 */
	@Override
	public void addTestCase(TestCase t) {
		if (!t.isTestCasePassing()) {
			failingTestList.add(t);
			super.addTestCase(t);
			return;
		}
		if (t.isTestCasePassing()) {
			throw new IllegalArgumentException("Cannot add passing test case.");
		}
	}
}
