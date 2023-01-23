package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;

import java.util.Objects;

/**
 * Represents a test plan in the SystemTestPlan system
 * @author Jeff Powell
 * @author Jordan Larose
 */
public abstract class AbstractTestPlan {
    /** Name of the test plan */
    private String testPlanName;
    /**List containing the Test Cases in the particular test plan */
    private SwapList<TestCase> testCases;

    /**
     * Constructor for TestPlan sets the title
     * @param title Title to name the test plan
     * @throws IllegalArgumentException if string provided is null or of zero length
     */
    public AbstractTestPlan(String title) {
    	if (title == null || "".equals(title)) {
            throw new IllegalArgumentException("Invalid name.");
        }
        this.testPlanName = title;
        testCases = new SwapList<>();
    }

    /**
     * Method sets the test plan name. Should not be null or of zero length
     * @param title What to title the test plan
     * @throws IllegalArgumentException if string provided is null or of zero length
     */
    public void setTestPlanName(String title) {
    	if (title == null || "".equals(title) || title.toLowerCase().equals(FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase())) {
            throw new IllegalArgumentException("Invalid name.");
        }
        this.testPlanName = title;
    }

    /**
     * Method returns test plan name
     * @return Test plan name
     */
    public String getTestPlanName() {
        return this.testPlanName;
    }

    /**
     * Method returns the test cases contained in a TestPlan as a list. Used by the GUI.
     * @return List of test cases in a test plan
     */
    public ISwapList<TestCase> getTestCases() {
        return testCases;
    }

    /**
     * Method adds a new test case to the list of test cases. Test case should be in a certain order depending on the test case
     * @param t Test case to be added to the list of test cases
     * @throws IllegalArgumentException if the test case cannot be added to the list for any reason
     */
    public void addTestCase(TestCase t) {
        testCases.add(t);
    }

    /**
     * Removes a test case at a certain index from the test plan.
     * @param idx Index of the test case to remove from the test plan
     * @return Test Case removed to ensure it was the correct test case
     * @throws IllegalArgumentException if the test case cannot be removed from the test plan
     */
    public TestCase removeTestCase(int idx) {
        TestCase ts;
        try {
            ts = testCases.remove(idx);
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        return ts;
    }

    /**
     * Method returns the Test case at a certain index in the test plan. Used by the GUI to display information about
     * a test case.
     * @param idx Index of the test case to find
     * @return Test case in question
     */
    public TestCase getTestCase(int idx) {
        return testCases.get(idx);
    }

    /**
     * Method returns the number of failing tests contained within the project. Used in the GUI as a counter.
     * @return Number of failing tests in test plan
     */
    public int getNumberOfFailingTests() {
        int failing = 0;
        for (int i = 0; i < testCases.size(); i++) {
            if (!testCases.get(i).isTestCasePassing()) {
                failing++;
            }
        }
        return failing;
    }

    /**
     * Method adds a result to the result of a certain project, and declares whether test is passing or failing
     * @param idx Index of test to modify
     * @param passing Declares whether test passed or failed
     * @param actualResults Results to be appended to the end of the projects results field
     * @throws IllegalArgumentException if any error is encountered adding the result to the test
     */
    public void addTestResult(int idx, boolean passing, String actualResults) {
        testCases.get(idx).addTestResult(passing, actualResults);
    }

    /**
     * Determines if a test plan is equivalent to the current instance so that duplicate test plans are not placed into
     * sorted list in TestPlanManager
     * @param o object
     * @return true if objects are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTestPlan that = (AbstractTestPlan) o;
        return testPlanName.toLowerCase().equals(that.testPlanName.toLowerCase());
    }

    /**
     * Method hashes a test plan in order to ensure it is not a duplicate of another test plan and allow it to be
     * placed in order into a sorted list in TestPlanManager
     * @return int id unique to each test plan
     */
    @Override
    public int hashCode() {
        return Objects.hash(testPlanName.toLowerCase());
    }

    /**
     * Method returns test cases as an array for the GUI to use. Allows the test cases in a test plan to be displayed in a list
     * @return Array containing test cases in test plan
     */
    public abstract String[][] getTestCasesAsArray();

}
