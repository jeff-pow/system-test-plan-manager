package edu.ncsu.csc216.stp.model.util;

/**
 * Class provides an array based list interface. Used to store test cases in a test plan
 * @author Jeff Powell
 * @author Jordan Larose
 *
 * @param <E> element
 */
public class SwapList<E> implements ISwapList<E> {
    /** Initial capacity of the array containing data */
    private static final int INITIAL_CAPACITY = 10;
    /** List that holds data for SwapList class. Concealed from user */
    private E[] list;
    /** Current number of items in the array */
    private int size;

    /**
     * Method constructs a new array and makes it of size equal to INITIAL_CAPACITY
     */
    @SuppressWarnings("unchecked")
    public SwapList() {
        list = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Adds the element to the end of the list.
     *
     * @param element element to add
     * @throws NullPointerException     if element is null
     * @throws IllegalArgumentException if element cannot be added
     */
    @Override
	public void add(E element) {
        checkCapacity(size());
        if (element == null) {
            throw new NullPointerException("Cannot add null element.");
        }
        list[size++] = element;
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
    @Override
	public E remove(int idx) {
        checkIndex(idx);
        E result = get(idx);
        for (int i = idx; i < size(); i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
        size--;
        return result;
    }

    /**
     * Moves the element at the given index to index-1.  If the element is
     * already at the front of the list, the list is not changed.
     *
     * @param idx index of element to move up
     * @throws IndexOutOfBoundsException if the idx is out of bounds
     *                                   for the list
     */
    @Override
	public void moveUp(int idx) {
        if (idx == 0 && size == 0) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        if (idx == 0) {
            return;
        }
        checkIndex(idx);
        E temp = get(idx);
        list[idx] = list[idx - 1];
        list[idx - 1] = temp;
    }

    /**
     * Moves the element at the given index to index+1.  If the element is
     * already at the end of the list, the list is not changed.
     *
     * @param idx index of element to move down
     * @throws IndexOutOfBoundsException if the idx is out of bounds
     *                                   for the list
     */
    @Override
	public void moveDown(int idx) {
        if (idx == size - 1) return;
        checkIndex(idx);
        E temp = get(idx);
        list[idx] = list[idx + 1];
        list[idx + 1] = temp;
    }

    /**
     * Moves the element at the given index to index 0.  If the element is
     * already at the front of the list, the list is not changed.
     *
     * @param idx index of element to move to the front
     * @throws IndexOutOfBoundsException if the idx is out of bounds
     *                                   for the list
     */
    @Override
	public void moveToFront(int idx) {
        if (idx == 0 && size == 0) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        checkIndex(idx);
        E temp = get(idx);
        remove(idx);
        checkCapacity(size + 1);
        for (int i = size(); i > 0; i--) {
            list[i] = list[i - 1];
        }
        list[0] = temp;
        size++;
    }

    /**
     * Moves the element at the given index to size-1.  If the element is
     * already at the end of the list, the list is not changed.
     *
     * @param idx index of element to move to the back
     * @throws IndexOutOfBoundsException if the idx is out of bounds
     *                                   for the list
     */
    @Override
	public void moveToBack(int idx) {
        if (idx == size - 1) return;
        checkIndex(idx);
        E temp = get(idx);
        remove(idx);
        add(temp);
    }

    /**
     * Returns the element at the given index.
     *
     * @param idx index of the element to retrieve
     * @return element at the given index
     * @throws IndexOutOfBoundsException if the idx is out of bounds
     *                                   for the list
     */
    @Override
	public E get(int idx) {
        checkIndex(idx);
        return list[idx];
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return number of elements in the list
     */
    @Override
	public int size() {
        return size;
    }

    /**
     * Method checks the capacity of the array field before adding a new element to ensure it won't be out of bounds.
     * If element would be out of bounds, grows the array and copies items from old array into the new larger one
     * @param idx Index to check in the array field
     */
    @SuppressWarnings("unchecked")
    private void checkCapacity(int idx) {
        if (idx < list.length - 1) {
            return;
        }
        E[] newArray = (E[]) new Object[size * 2];
        for (int i = 0; i < size(); i++) {
            newArray[i] = list[i];
        }
        list = newArray;
    }

    private void checkIndex(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }
}
