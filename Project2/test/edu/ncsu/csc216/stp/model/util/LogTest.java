/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests Log
 * @author Jordan Larose
 * @author Jeff Powell
 */
class LogTest {

	/**
	 * Method tests adding a valid element to the log and ensures a null one cannot be added
	 */
	@Test
	void testAdd() {
		Log<String> log = new Log<>();
		log.add("1");
		log.add("2");
        log.add("3");
		assertEquals("2", log.get(1));
		assertEquals("3", log.get(2));
		assertThrows(NullPointerException.class, () -> log.add(null));
	}

	/**
	 * Method tests getting elements from the log. Should throw an exception if index is higher than the number of
	 * items in the list or below zero
	 */
	@Test
	void testGet() {
		Log<String> log = new Log<>();
		log.add("1");
		log.add("2");
		log.add("3");
		assertThrows(IndexOutOfBoundsException.class, () -> log.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> log.get(3));
	}

	/**
	 * Method tries to add more items than the initial capacity of the array
	 */
	@Test
	public void testAddManyItems() {
		Log<Integer> log = new Log();
		for (int i = 0; i < 20; i++) {
			log.add(1000);
		}
		assertEquals(20, log.size());
	}

}
