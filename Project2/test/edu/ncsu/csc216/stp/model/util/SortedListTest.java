/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests SortedList
 * @author Jordan Larose
 */
class SortedListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SortedList#add(java.lang.Comparable)}.
	 */
	@Test
	void testAdd() {
		SortedList<Integer> list = new SortedList<>();
		list.add(1);
		assertEquals(1, list.size());
        list.add(2);
		assertEquals(2, list.size());
		list.add(4);
        assertEquals(3, list.size());
		list.add(3);
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(4, list.get(3));
		assertThrows(NullPointerException.class, () -> list.add(null));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SortedList#remove(int)}.
	 */
	@Test
	void testRemove() {
		SortedList<Integer> list = new SortedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
		list.add(4);
		list.remove(0);
		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(3, list.size());
		list.remove(2);
		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SortedList#contains(java.lang.Comparable)}.
	 */
	@Test
	void testContains() {
		SortedList<Integer> list = new SortedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.contains(1));
		assertFalse(list.contains(4));
	}
	
	/**
	 * Teaching staff test add duplicate
	 */
	@Test
	void testAddTeaching() {
		SortedList<String> list = new SortedList<>();
		list.add("banana");
		list.add("apple");
		list.add("orange");
		list.add("eggplant");
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("eggplant", list.get(2));
		assertEquals("orange", list.get(3));
	}


	/**
	 * Teaching staff test testing remove
	 */
	@Test
	public void teachingTestRemove() {
		SortedList<String> list = new SortedList<>();
		list.add("banana");
		list.add("apple");
		list.add("orange");
		list.add("eggplant");
		assertEquals("apple", list.remove(0));
	}

	@Test
	public void test() {
		SortedList<Integer> list = new SortedList<>();
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(2);
		list.add(0);
		assertEquals(2, list.get(2));
		assertEquals(0, list.get(0));
	}
}
