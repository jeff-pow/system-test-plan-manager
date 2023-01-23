package edu.ncsu.csc216.stp.model.util;

/**
 * Class stores a history of results from user ran tests. Used in TestCase class.
 * @author Jeff Powell
 * @author Jordan Larose
 *
 * @param <E> elements of type E
 */
public class Log<E> implements ILog<E> {
	/** List of elements */
    private E[] log;
    /** Size of list */
    private int size;
    /** Initial capacity of the list */
    private static final int INIT_CAPACITY = 10;

    /**
     * Creates a new instance of Log. Changes array from generic type E to a specific data type
     */
    @SuppressWarnings("unchecked")
	public Log() {
        log = (E[]) new Object[INIT_CAPACITY];
    }


    /**
     * Adds the element to the end of the list.
     *
     * @param element element to add
     * @throws NullPointerException if element is null
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null element.");
        }
        checkCapacity(size);
        log[size] = element;
        size++;
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
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        return log[idx];
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
        if (idx < log.length) {
            return;
        }
        E[] newArray = (E[]) new Object[size * 2];
        for (int i = 0; i < size(); i++) {
            newArray[i] = log[i];
        }
        log = newArray;
    }
}
