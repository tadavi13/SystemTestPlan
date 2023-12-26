package edu.ncsu.csc216.stp.model.util;

/**
 * Swap List class for a list implementation for the WolfTestCases application. Contains
 * implementations of the ISwapList interface methods such as adding an element, removing
 * an element, checking for a specific element in the list, and getting the index of a specific
 * element. The Swap List class is used for storing test cases in the manager.
 * There are also methods allowing the user to move test cases around inside the manager.
 * @param <E> Generic type of the SwapList
 */
public class SwapList<E> implements ISwapList<E> {
	/** Constant value for the initial capacity of a swap list */
	private static final int INITAL_CAPACITY = 10;
	/** Generic E list for the actual list of swap list */
	private E[] list;
	/** Size of the list */
	private int size;
	
	/**
	 * Constructor of a Swap List.
	 */
	public SwapList() {
		list = (E[]) new Object[INITAL_CAPACITY];
		size = 0;
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		checkCapacity(list.length);
		
		list[size] = element;
		size++;
	}
	
	/**
	 * Checks the capacity of the list. If capacity == size, then the capacity is doubled.
	 * @param capacity the capacity of the list
	 */
	private void checkCapacity(int capacity) {
		if (capacity == size) {
			E[] newList = (E[]) new Object[list.length * 2];
			
			for (int i = 0; i < size; i++) {
				newList[i] = list[i];
			}
			
			list = newList;
		}
	}
	
	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public E remove(int idx) {
		checkIndex(idx);
		
		E element = list[idx];
		for (int i = idx; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
	
		size--;
		return element;
	}
	
	/**
	 * Checks the index for methods that have an index parameter.
	 * Ensure index is inbounds based on the size of the list.
	 * @param idx to check
	 */
	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	/**
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveUp(int idx) {
		checkIndex(idx);
		
		if (idx != 0) {
			E temp = list[idx - 1];
			list[idx - 1] = list[idx];
			list[idx] = temp;
		}
	}
	
	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveDown(int idx) {
		checkIndex(idx);
		
		if (idx != size - 1) {
			E temp = list[idx + 1];
			list[idx + 1] = list[idx];
			list[idx] = temp;
		}
	}
	
	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveToFront(int idx) {
		checkIndex(idx);
		
		if (idx != 0) {
			E temp = list[idx];
			for (int i = idx; i > 0; i--) {
				list[i] = list[i - 1];
			}
			list[0] = temp;
		}
	}
	
	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveToBack(int idx) {
		checkIndex(idx);
		
		if (idx != size - 1) {
			E temp = list[idx];
			for (int i = idx; i < size; i++) {
				list[i] = list[i + 1];
			}
			list[size - 1] = temp;
		}
	}
	
	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public E get(int idx) {
		checkIndex(idx);
		
		return list[idx];
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	public int size() {
		return size;
	}
}
