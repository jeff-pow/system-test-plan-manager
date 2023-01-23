package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Class handles writing state to a file and allows to save state while it is not running.
 * @author Jordan Larose
 */
public class TestPlanReader {

    /**
     * Method is the public method in the TestPlanReader class that allows program to read state from a file. Method is
     * only called by TestPlanManager to preserve encapsulation.
     * @param file File to read records from
     * @return Custom implementation of
     * @throws IllegalArgumentException if the file does not exist
     */
    public static ISortedList<TestPlan> readTestPlansFile(File file) {
    	SortedList<TestPlan> list = new SortedList<>();
    	Scanner s;
    	Scanner scan;
    	try {
    		s = new Scanner(new FileInputStream(file));
    		scan = new Scanner(new FileInputStream(file));
    	} catch(FileNotFoundException e) {
    		throw new IllegalArgumentException("Unable to load file.");
    	}
    	String string = scan.nextLine();
		if (string.charAt(0) != '!') {
			throw new IllegalArgumentException("Unable to load file.");
		}
    	s.useDelimiter("\\r?\\n?[!]");
    	while (s.hasNext()) {
    		String str = s.next();
    		TestPlan t = processTestPlan(str.trim());
    		if (t == null) continue;
    		else list.add(t);
    	}
    	s.close();
    	scan.close();
    	
        return list;
    }

    /**
     * Method takes a String of a test plan as a parameter and breaks it up into test case objects that the program can store.
     * It then stores those cases into a new test plan which it returns
     * @param testPlan test plan in string form from a file
     * @return test plan object with all the test cases contained within
     */
    private static TestPlan processTestPlan(String testPlan) {
    	Scanner testPlanScan = new Scanner(testPlan);
    	TestPlan t = new TestPlan(testPlanScan.nextLine().trim());
    	if (t.getTestPlanName().equals("!")) {
    		testPlanScan.close();
    		return null;
    	}
    	testPlanScan.useDelimiter("\\r?\\n?[#]");
    	while (testPlanScan.hasNext()) {
    		TestCase tc = processTest(t, testPlanScan.next().trim());
    		if (tc == null) continue;
    		else t.addTestCase(tc);
    	}
    	testPlanScan.close();
    	
    	return t;
    }

    /**
     * Method takes a String of a test case and turns it into a test case object that program can store.
     * @param testPlan Type of test that the string falls into (standard or failing)
     * @param testCase Rest of the information about the test (title, results, etc)
     * @return Test Case object that can be placed inside a test plan
     */
    private static TestCase processTest(AbstractTestPlan testPlan, String testCase) {
    	TestCase t;
    	try {
    		Scanner testScan = new Scanner(testCase);
    		//testScan.useDelimiter("\\r?\\n?[*]");
        	//Scanner firstLine = new Scanner(testScan.next());
    		String firstLine = testScan.nextLine();
        	String[] fl = firstLine.split(",");
        	String id = null;
        	String type = null;
        	String description = null;
        	String expectedResults = null;
			if (fl.length != 2) {
				return null;
			}
        	id = fl[0].trim();
        	type = fl[1].trim();
   
        	testScan.useDelimiter("\\r?\\n?[-]");
        	String descriptionResults = testScan.next();
        	String[] dr = descriptionResults.split("\\r?\\n?[*]");
        	if (dr.length != 3) {
        		testScan.close();
				return null;
        	}
			if (dr[1]  == null || dr[2] == null) {
				return null;
			}
        	description = dr[1].trim();
  
			expectedResults = dr[2].trim();
			
        	if (expectedResults == null || description == null || id == null || type == null) {
        		testScan.close();
        		return null;
        	}
			if ("".equals(expectedResults) || "".equals(description) || "".equals(id) || "".equals(type)) {
				return null;
			}
        	t = new TestCase(id, type, description, expectedResults);
        	while (testScan.hasNext()) {
        		Scanner s = new Scanner(testScan.next());
        		s.useDelimiter(":");
        		String pass = s.next().trim();
        		String details = s.next().trim();
        		if ("PASS".equals(pass)) {
    				t.addTestResult(true, details);
    			}
        		else if ("FAIL".equals(pass)) {
    				t.addTestResult(false, details);
    			}
        		else {
    				break;
        		}
        		s.close();
        	}
        	
    		testScan.close();
            
    	} catch(NoSuchElementException e) {
    		return null;
    	}
    	return t;
    }
}