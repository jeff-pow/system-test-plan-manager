package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Class stores and organizes individual test cases.
 * @author Jeff Powell
 * @author Jordan Larose
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {
    /**
     * Method creates a new TestPlan with a title s. If title is "Failing Tests", throws an illegal argument exception
     * @param testPlanName Title of new test plan
     * @throws IllegalArgumentException if param name equals FailingTestList 
     */
    public TestPlan(String testPlanName) {
    	super(testPlanName);
    	if (testPlanName.toLowerCase().equals(FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase())) {
    		throw new IllegalArgumentException("Invalid name.");
    	}
    }

    /**
     * Method returns test cases as an array for the GUI to use. Allows the test cases in a test plan to be displayed in a list
     * @return Array containing test cases in test plan
     */
    @Override
	public String[][] getTestCasesAsArray() {
    	String[][] testCaseArray = new String[getTestCases().size()][3];
    	for(int i = 0; i < getTestCases().size(); i++) {
    	   TestCase t = getTestCases().get(i);
    	   testCaseArray[i][0] = t.getTestCaseId();
    	   testCaseArray[i][1] = t.getTestType();
    	   testCaseArray[i][2] = t.getStatus();
        }
    	return testCaseArray;
    }

    /**
     * Method creates a new test case with super and sets test case's test plan to this instance.
     * @param t Test case to be added to the list of test cases
     */
    public void addTestCase(TestCase t) {
        super.addTestCase(t);
        t.setTestPlan(this);
    }

    /**
     * Compares name of test plans to ensure they are not identical. Case insensitive. Allows for test plans to be
     * placed in proper order in sorted list.
     * @param t Test plan to compare against this current instance
     * @return int, 0 if identical, another number if they aren't
     */
    @Override
	public int compareTo(TestPlan t) {
    	if(getTestPlanName().toLowerCase().compareTo(t.getTestPlanName().toLowerCase()) > 0) {
			return 1;
		}
    	else if(getTestPlanName().toLowerCase().compareTo(t.getTestPlanName().toLowerCase()) == 0) {
    		return 0;
    	}
        return -1;
    }


}
