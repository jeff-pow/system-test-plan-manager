package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;
import edu.ncsu.csc216.stp.model.util.SwapList;


/**
 * Contains a list of test plans, the FailingTestList, 
 * and operations to manipulate these lists
 * 
 * @author Jeff Powell
 * @author Jordan Larose
 */
public class TestPlanManager {
	/** Boolean value to determine change */
    private boolean isChanged;
    /** Sorted list of test plans */
    private ISortedList<TestPlan> testPlans;
    /** Failing test lists */
    private FailingTestList failingTestList;
    /** Current instance of abstract test plan */
    private AbstractTestPlan currentTestPlan;

    /**
     * Constrcts a TestPlanManager
     */
    public TestPlanManager() {
        testPlans = new SortedList<>();
        failingTestList = new FailingTestList();
        currentTestPlan = failingTestList;
        isChanged = false;
    }

    /**
     * Loads test plans from file
     * @param file filename
     */
    public void loadTestPlans(File file) {
    	SortedList<TestPlan> list = (SortedList<TestPlan>) TestPlanReader.readTestPlansFile(file);
        
        for (int i = 0; i < list.size(); i++) {
            try {
                testPlans.add(list.get(i));
            }
            catch (IllegalArgumentException e) {
                continue;
            }
        }
        getFailingTests();
        currentTestPlan = failingTestList;
        isChanged = true;
    }

    /**
     * Saves a test plan to a file
     * @param file filename
     */
    public void saveTestPlans(File file) {

        try {
            TestPlanWriter.writeTestPlanFile(file, testPlans);
        }
        catch (Exception e) {
            throw new IllegalArgumentException();
        }
        isChanged = false;
    }

    /**
     * Determine if the plan has been changed
     * @return true if changed, false if not
     */
    public boolean isChanged() {
        return isChanged;
    }

    /**
     * Adds a test plan
     * @param testPlan testPlan name as a String
     */
    public void addTestPlan(String testPlan) {
        if (testPlan == null || "".equals(testPlan)) {
            throw new IllegalArgumentException("Invalid test plan name.");
        }
        if (testPlan.toLowerCase().equals(FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase())) {
            throw new IllegalArgumentException("Invalid name.");
        }
        for (int i = 0; i < testPlans.size(); i++) {
            if (testPlans.get(i).getTestPlanName().toLowerCase().equals(testPlan.toLowerCase())) {
                throw new IllegalArgumentException("Invalid name.");
            }
        }
        testPlans.add(new TestPlan(testPlan));
        setCurrentTestPlan(testPlan);
        getFailingTests();
        isChanged = true;
    }

    /**
     * Gets the names of the test plans
     * @return name of test plan as String
     */
    public String[] getTestPlanNames() {
         String[] names = new String[testPlans.size() + 1];
         names[0] = FailingTestList.FAILING_TEST_LIST_NAME;
         for (int i = 0; i < testPlans.size(); i++) {
             names[i + 1] = testPlans.get(i).getTestPlanName();
         }
         return names;
    }

    private void getFailingTests() {
        failingTestList = new FailingTestList();
        for (int i = 0; i < testPlans.size(); i++) {
            SwapList<TestCase> list = (SwapList<TestCase>) testPlans.get(i).getTestCases();
            for (int j = 0; j < list.size(); j++) {
                TestCase testCase = (TestCase) list.get(j);
                if (!testCase.isTestCasePassing()) {
                    failingTestList.addTestCase(testCase);
                }
            }
        }
    }

    /**
     * Gets the current test plan
     * @return current test plan
     */
    public AbstractTestPlan getCurrentTestPlan() {
        return currentTestPlan;
    }

    /**
     * Sets the currentTestPlan to the AbstractTestPlan with the given name
     * @param s name of current test plan
     */
    public void setCurrentTestPlan(String s) {
        getFailingTests();
        currentTestPlan = failingTestList;
        for (int i = 0; i < testPlans.size(); i++) {
            if (testPlans.get(i).getTestPlanName().equals(s)) {
                currentTestPlan = testPlans.get(i);
                return;
            }
        }
        currentTestPlan = failingTestList;
    }

    /**
     * Edits the test plan
     * @param s name of test plan to be edited
     */
    public void editTestPlan(String s) {
        if (s.toLowerCase().equals(currentTestPlan.getTestPlanName().toLowerCase())
                || s.toLowerCase().equals(FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase())) {
            throw new IllegalArgumentException("Invalid name.");
        }
        for (int i = 0; i < testPlans.size(); i++) {
            if (s.toLowerCase().equals(testPlans.get(i).getTestPlanName().toLowerCase())) {
                throw new IllegalArgumentException("Invalid name.");
            }
        }
        if (currentTestPlan == failingTestList) {
            throw new IllegalArgumentException("The Failing Tests list may not be edited.");
        }
        else {
            currentTestPlan.setTestPlanName(s);
        }
        isChanged = true;
    }

    /**
     * Removes the test plan
     */
    public void removeTestPlan() {
        if (currentTestPlan == failingTestList) {
            throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
        }
        for (int i = 0; i < testPlans.size(); i++) {
            if (testPlans.get(i).getTestPlanName().toLowerCase().equals(currentTestPlan.getTestPlanName().toLowerCase())) {
                testPlans.remove(i);
            }
        }
        currentTestPlan = failingTestList;
        isChanged = true;
        getFailingTests();
    }

    /**
     * Adds a test case
     * @param ts name of test case to be added
     */
    public void addTestCase(TestCase ts) {
        if (currentTestPlan == failingTestList) {
            return;
        }
        currentTestPlan.addTestCase(ts);
        if (!ts.isTestCasePassing()) {
            getFailingTests();
        }
        isChanged = true;
        getFailingTests();
    }

    /**
     * Adds test result
     * @param idx index
     * @param b boolean value
     * @param s string name
     */
    public void addTestResult(int idx, boolean b, String s) {
        if (!b) {
            getFailingTests();
        }
        currentTestPlan.addTestResult(idx, b, s);
        getFailingTests();
    }

    /**
     * Clears test plans
     */
    public void clearTestPlans() {
        testPlans = new SortedList<>();
        failingTestList = new FailingTestList();
        currentTestPlan = failingTestList;
        isChanged = false;
        getFailingTests();
    }

}