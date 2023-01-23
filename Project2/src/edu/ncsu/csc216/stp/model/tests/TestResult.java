package edu.ncsu.csc216.stp.model.tests;

/**
 * Class stores the results of a test in a Log and keeps track of the history of these results. Also keeps track of if the result was a pass or a fail
 * @author Jeff Powell
 * @author Jordan Larose
 */
public class TestResult {
    /** String to denote passing */
    public static final String PASS = "PASS";
    /** String to denote failing */
    public static final String FAIL = "FAIL";
    /**
     * Boolean to represent if a test is failing or not. True means passing, false
     * means failing.
     */
    private boolean passing;
    /** String to keep track of the history of actual results a user reports */
    private String actualResults;

    /**
     * Method constructs a TestResult object. Throws an IllegalArgumentException if actualResults
     * is null or empty.
     * 
     * @param passing boolean determining whether test passes. True = pass, false = fail
     * @param actualResults Results of running the test provided by user
     */
    public TestResult(boolean passing, String actualResults) {
        setPassing(passing);
        setActualResults(actualResults);
    }

    /**
     * Method returns true if test passed, and false if not
     * @return Boolean stating whether test passed
     */
    public boolean isPassing() {
        return passing;
    }

    /**
     * Method sets passing field of method
     * @param passing True if passing, false if not
     */
    private void setPassing(boolean passing) {
        this.passing = passing;
    }

    /**
     * Method returns actual results of a test as input by the user.
     * @return String actual results of a test
     */
    public String getActualResults() {
        return actualResults;
    }

    /**
     * Method sets the actual results field of a test. Throws an IllegalArgumentException if field is empty or null
     * @param actualResults String of actual results
     */
    private void setActualResults(String actualResults) {
        if ("".equals(actualResults) || actualResults == null) {
            throw new IllegalArgumentException("Invalid test results.");
        }
        this.actualResults = actualResults;
    }

    /**
     * Method returns test result as a String. Called by TestCase to assemble a string for file output
     * @return TestResult as a string
     */
    @Override
    public String toString() {
        String s = "";
        if (isPassing()) {
            s += PASS;
        }
        else {
            s += FAIL;
        }
        s += ": " + getActualResults();
        return s;
    }
}
