/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Tests TestPlanWriter
 * @author Jordan Larose
 */
class TestPlanWriterTest { 
	
	/** test file 2*/
	private final String expectedTestFile = "test-files/WriterTestExpected.txt";

	/**
	 * Helper method to compare two files for the same contents
	 * Taken from LR1 repo, StudentRecordIOTest.java
	 *
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	public static void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile))) {

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act);
				// The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Method tests writing several projects containing test cases to a file
	 */
	@Test
	void testWriteTestPlanFile() {
		File file = new File("test-files/actual.txt");
		TestPlan tp = new TestPlan("TestPlanName");
		TestCase ts = new TestCase("testcaseid", "testtype", "desc", "results");
		ts.addTestResult(true, "passed");
		ts.addTestResult(false, "didnt pass");
		tp.addTestCase(ts);
		SortedList<TestPlan> list = new SortedList<TestPlan>();
		list.add(tp);
		
		try {
			TestPlanWriter.writeTestPlanFile(file, list);
		} catch (Exception e) {
			fail("Unable to save file.");
		}
		
		checkFiles(expectedTestFile, "test-files/actual.txt");
		}
	}