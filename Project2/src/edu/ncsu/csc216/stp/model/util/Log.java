package edu.ncsu.csc216.stp.model.util;

/**
 * Log class for a list of actual results in the WolfTestCases application. Contains
 * implementations of the ILog interface methods such as adding an element to the list and
 * getting an element at an index. The Log class is used for storing Test Case actual results
 * each time the test is performed.
 * @param <E> Type of the list
 */
public class Log<E> implements ILog<E> {
	/** Generic array for the log list */
	private E[] log;
	/** Size of the array */
	private int size;
	/** Initial capacity of the array when initialized */
	private static final int INIT_CAPACITY = 10;
	
	/**
	 * Constructor of the Log class.
	 */
	public Log() {
		log = (E[]) new Object[INIT_CAPACITY];
		size = 0;
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null 
	 */
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		if (log.length == size) {
			E[] newList = (E[]) new Object[log.length * 2];
			
			for (int i = 0; i < size; i++) {
				newList[i] = log[i];
			}
			
			log = newList;
		}
		
		log[size] = element;
		size++;
	}
	
	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		return log[idx];
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	public int size() {
		return size;
	}
}
