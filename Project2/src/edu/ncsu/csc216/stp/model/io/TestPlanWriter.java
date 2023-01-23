package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Class handles file input and allows program to read state from a file.
 */
public class TestPlanWriter {
	

	/**
	 * Method writes all test plans to a file. Only called in the TestPlanManager
	 * class to preserve encapsulation. Method calls toString methods created in
	 * TestPlan and TestCase classes to fit the requirements of file IO.
	 * 
	 * @param fileName File to write the list of test plans to
	 * @param list     List containing the test plans and system tests that should
	 *                 be written to a file.
	 */
	public static void writeTestPlanFile(File fileName, ISortedList<TestPlan> list) {
		try {
			FileWriter file = new FileWriter(fileName);
			for (int i = 0; i < list.size(); i++) {
				file.write("! " + list.get(i).getTestPlanName() + "\n");
				for (int j = 0; j < list.get(i).getTestCases().size(); j++) {
					file.write(list.get(i).getTestCase(j).toString());
				}
			}
			file.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
