package edu.ncsu.csc216.stp.model.util;

/**
 * Class stores a list of projects. Used in the TestPlanManager class.
 * @author Jeff Powell
 * @author Jordan Larose
 *
 * @param <E> elements
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
    /** Size of list */
	private int size;
	/** ListNode for front of the linked list */
    private ListNode front;

    /**
     * Constructs a Sorted List
     */
    public SortedList() {
        size = 0;
    }

    /**
 	 * Adds the element to the list in sorted order. Elements must be added in the sorted order and never allowed
     * to get out of the sorted order.
     *
     * @param element element to add
     * @throws NullPointerException     if element is null
     * @throws IllegalArgumentException if element cannot be added
     */
    public void add(E element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null element.");
        }

        if (front == null) {
            front = new ListNode(element, null);
            size++;
            return;
        }

        ListNode obj = front;
        while (true) {
            if (obj.data.equals(element)) {
                throw new IllegalArgumentException("Cannot add duplicate element.");
            }

            if(obj.next == null)
            {
                break;
            }
            obj = obj.next;
        }
        if (element.compareTo(front.data) < 0) {
            ListNode temp = front;
            front = new ListNode(element, temp);
            size++;
            return;
        }

        obj = front;
        int idx = findElementPlacement(element);
        for (int i = 0; i < idx; i++) {
            obj = obj.next;
        }
        obj.next = new ListNode(element, obj.next);
        size++;
    }

    private int findElementPlacement(E element) {
        ListNode current = front;
        int idx = 0;
        for (int i = 0; i < size; i++) {
            if (element.compareTo(current.data) < 0) {
                break;
            }
            idx = i;
            current = current.next;
        }
        return idx;
    }


    /**
     * Returns the element from the given index.  The element is
     * removed from the list.
     *
     * @param idx index to remove element from
     * @return element at given index
     * @throws IndexOutOfBoundsException if the idx is out of bounds
     *                                   for the list
     */
    public E remove(int idx) {
        checkIndex(idx);
        E result;
        if (idx == 0) {
            result = front.data;
            front = front.next;
        }
        else {
            ListNode current = front;
            for (int i = 0; i < idx - 1; i++) {
                current = current.next;
            }
            result = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return result;
    }

    /**
     * Returns true if the element is in the list.
     *
     * @param element element to search for
     * @return true if element is found
     */
    public boolean contains(E element) {
        ListNode current = front;
        for (int i = 0; i < size(); i++) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the element at the given index.
     *
     * @param idx index of the element to retrieve
     * @return element at the given index
     */
    public E get(int idx) {
        checkIndex(idx);
        ListNode current = front;
        if (idx == 0) {
            return front.data;
        }
        for (int i = 0; i < idx; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Method throws an exception if index is greater than the number of elements in the list or less than 0
     * @param idx Index to check
     * @throws IndexOutOfBoundsException if the idx is out of bounds
     *                                   for the list
     */
    private void checkIndex(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }

    private class ListNode {
    	/** Data in the node */
        public E data;
        /** The next node */
        public ListNode next;

        public ListNode(E element, ListNode next) {
            this.next =  next;
            this.data = element;
        }
    }
}
