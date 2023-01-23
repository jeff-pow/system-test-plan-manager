/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests SwapList
 * @author Jordan Larose
 */
class SwapListTest {

	/**
	 * Tests adding elements to a list. Method should throw an error if element is null
	 */
	@Test
	void testAdd() {
		SwapList<Integer> list = new SwapList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		assertEquals(5, list.size());
		list.add(5);
		assertEquals(6, list.size());
		assertThrows(NullPointerException.class, () -> list.add(null));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(6));
	}

	/**
	 * Method tests removing elements from a list. Should throw an error if index is out of bounds
	 */
	@Test
	void testRemove() {
		SwapList<Integer> list = new SwapList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		assertEquals(5, list.size());
		list.remove(1);
		assertEquals(4, list.size());
		assertEquals(1, list.get(0));
		assertEquals(3, list.get(1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(6));
	}

	/**
	 * Method tests moveDown method for case idx in the middle of the list and idx at the end
	 */
	@Test
	void testMoveDown() {
		SwapList<Integer> list = new SwapList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.moveDown(3);
		assertEquals(5, list.get(3));
		assertEquals(4, list.get(4));
		list.moveDown(4);
		assertEquals(5, list.get(3));
		assertEquals(4, list.get(4));
	}

	/**
	 * Method tests moveUp method for case idx in the middle of the list and idx at the beginning
	 */
	@Test
	void testMoveUp() {
		SwapList<Integer> list = new SwapList<>();
		list.add(0);
		list.add(1);
        list.add(2);
		list.add(3);
        list.add(4);
		list.moveUp(3);
		assertEquals(3, list.get(2));
		assertEquals(2, list.get(3));
	}

	/**
	 * Method tests moving objects to the front of the list
	 */
	@Test
	void testMoveToFront() {
		SwapList<Integer> list = new SwapList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.moveToFront(3);
		assertEquals(3, list.get(0));
		list.moveToFront(0);
		assertEquals(3, list.get(0));
	}

	/**
	 * Method tests moving objects to the back of the list
	 */
	@Test
	void testMoveToBack() {
		SwapList<Integer> list = new SwapList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.moveToBack(4);
		assertEquals(4, list.get(4));
		list.moveToBack(0);
		assertEquals(0, list.get(4));
	}

	/**
	 * Method ensures that more than 10 elements can be added to the array (initial size of the array)
	 */
	@Test
	public void testGrowArray() {
		SwapList<Integer> list = new SwapList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
		list.add(5);
        list.add(6);
		list.add(7);
        list.add(8);
		list.add(9);
        list.add(10);
		list.add(11);
        list.add(12);
		assertEquals(13, list.size());
		assertEquals(12, list.get(12));
		assertEquals(10, list.get(10));
		assertEquals(8, list.get(8));
	}

	/**
	 * Method based off teaching staff test of the similar name
	 */
	@Test
	public void teachingTestMoveUp() {
		SwapList<String> list = new SwapList<>();
		list.add("apple");
		list.add("pear");
		list.add("bannana");
		list.add("cherry");
		list.add("blueberries");
		assertEquals(5, list.size());
		assertEquals("apple", list.get(0));
		list.moveToFront(2);
		assertEquals("bannana", list.get(0));
		assertEquals("apple", list.get(1));

	}
}