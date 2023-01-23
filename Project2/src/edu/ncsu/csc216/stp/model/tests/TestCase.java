package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ILog;
import edu.ncsu.csc216.stp.model.util.Log;


/**
 * Class keeps track of a TestCase and qualities suck as the expected results and which test plan it belongs to
 * @author Jeff Powell
 * @author Jordan Larose
 */
public class TestCase {
    /** Name of the test and a brief description of it */
    private String testCaseId;
    /** Type of test that the user sets (ex Boundary Condition, Equivalence Class, etc - User determined) */
    private String testType;
    /** Describes the steps the user has to go through to execute the step */
    private String testDescription;
    /** Describes exactly what the program should do when the testDescription is executed. */
    private String expectedResults;
    /** TestPlan that the test belongs to */
    private TestPlan testPlan;
    /** Log that stores the history of actual test results from when a user preforms the test */
    private ILog<TestResult> testResults;

    /**
     * Method returns testCaseId
     * @return String testCaseId
     */
    public String getTestCaseId() {
        return testCaseId;
    }

    /**
     * Method sets the case id. String should not be null or empty
     * @param testCaseId String to set the field testCaseId to
     * @throws IllegalArgumentException if parameter provided is null or empty
     */
    private void setTestCaseId(String testCaseId) {
        if ("".equals(testCaseId) || testCaseId == null) throw new IllegalArgumentException("Invalid test information.");
        this.testCaseId = testCaseId;
    }

    /**
     * Method returns the test type
     * @return String testType
     */
    public String getTestType() {
        return testType;
    }

    /**
     * Method sets the testType (ex Boundary Condition, Equivalence Class, etc - User determined).
     * Test type should not be null or empty
     * @param testType String testType to set field equal to
     * @throws IllegalArgumentException if parameter provided is null or empty
     */
    private void setTestType(String testType) {
        if ("".equals(testType) || testType == null) throw new IllegalArgumentException("Invalid test information.");
        this.testType = testType;
    }

    /**
     * Method returns testDescription
     * @return String testDescription
     */
    public String getTestDescription() {
        return testDescription;
    }

    /**
     * Method setsTestDescription desribing how user should use test. String should not be empty or null
     * @param testDescription String to set field equal to
     * @throws IllegalArgumentException if parameter provided is null or empty
     */
    private void setTestDescription(String testDescription) {
        if ("".equals(testDescription) || testDescription == null) throw new IllegalArgumentException("Invalid test information.");
        this.testDescription = testDescription;
    }

    /**
     * Method returns expectedResults field
     * @return String expectedResults
     */
    public String getExpectedResults() {
        return expectedResults;
    }

    /**
     * Method sets expected results, which is what a program should do when a test is run. String should not be null or
     * empty
     * @param expectedResults expectedResults to set field equal to
     * @throws IllegalArgumentException if parameter provided is null or empty
     */
    private void setExpectedResults(String expectedResults) {
        if ("".equals(expectedResults) || expectedResults == null) throw new IllegalArgumentException("Invalid test information.");
        this.expectedResults = expectedResults;
    }

    /**
     * Method constructs a test case class instance using setter methods.
     * @param testCaseId Name of the test case
     * @param testType Type of test (user determined)
     * @param testDescription What should be done to run the test
     * @param expectedResults What the program should do in response to the test
     */
    public TestCase(String testCaseId, String testType, String testDescription, String expectedResults) {
        this.testPlan = null;
        testResults = new Log<>();
        setTestCaseId(testCaseId);
        setTestType(testType);
        setTestDescription(testDescription);
        setExpectedResults(expectedResults);
    }

    /**
     * Method appends a test result to the log of test results in this instance of test case
     * @param passing Whether the test passed
     * @param testResult What the program did in response to executing the test
     */
    public void addTestResult(boolean passing, String testResult) {
        TestResult ts;
        try {
            ts = new TestResult(passing, testResult);
        }
        catch (IllegalArgumentException IAE) { throw new IllegalArgumentException("Invalid test results."); }
        testResults.add(ts);
    }

    /**
     * Method returns whether or not most recent test case is passing
     * @return True if passing, false if not
     */
    public boolean isTestCasePassing() {
        try {
            testResults.get(0);
        }
        catch (IndexOutOfBoundsException e) {
            return false;
        }
        return testResults.get(testResults.size() - 1).isPassing();
    }

    /**
     * Method gets status of test as PASS or FAIL using constants from TestResult class
     * @return PASS if passing, FAIL if failing
     */
    public String getStatus() {
        if (isTestCasePassing()) {
            return TestResult.PASS;
        }
        return TestResult.FAIL;
    }

    /**
     * Method returns the actual results as a string. Used by GUI to display history of a test
     * @return String of all actual results from log field
     */
    public String getActualResultsLog() {
        String s = "";
        for (int i = 0; i < testResults.size(); i++) {
            s += "- " + testResults.get(i).toString() + "\n";
        }
        return s;
    }

    /**
     * Method sets testPlan that a test case is associated with
     * @param testPlan TestPlan to associate a testcase with
     */
    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }

    /**
     * Method returns the test plan a test case is associated with
     * @return TestPlan that a TestCase is associated with
     */
    public TestPlan getTestPlan() {
        return testPlan;
    }

    /**
     * Method turns a test case and all its test results into a string for formatting to be output into a file.
     * Calls toString method in TestResult
     * @return Formatted string displaying all information about a test
     */
    @Override
    public String toString() {
        String s = "";
        s += "# " + testCaseId + "," + testType + "\n";
        s += "* " + testDescription + "\n";
        s += "* " + expectedResults + "\n";
        s += getActualResultsLog();
        return s;
    }

}
