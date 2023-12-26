package edu.ncsu.csc216.stp.model.util;

/**
 * Sorted List class for a sorted list implementation for the WolfTestCases application. Contains
 * implementations of the ISortedList interface methods such as adding a list node, removing
 * a list node, checking for a specific element in the list, and getting the index of a specific
 * element. Contains the inner ListNode class for each node of the list. The Sorted List class is used
 * for storing Test Plans in a sorted order in the manager.
 * @param <E> Type of the list
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	/** Size of the sorted list */
	private int size;
	/** First node in the list */
	private ListNode front;
	
	/**
	 * Constructor of the SortedList class.
	 */
	public SortedList() {
		front = null;
		size = 0;
	}
	
	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		if (this.contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		
		if (front == null || element.compareTo(front.data) < 0) {
			front = new ListNode(element, front);
		} else {
			ListNode current = front;
			while (current.next != null && current.next.data.compareTo(element) < 0) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		
		size++;
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
		
		ListNode current = front;
		E data = null;
		
		if (idx == 0) {
			data = front.data;
			front = front.next;
		} else {
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			
			data = current.next.data;
			current.next = current.next.next;
		}
		
		size--;
		return data;
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
	 * Returns true if the element is in the list.
	 * @param element element to search for
	 * @return true if element is found
	 */
	public boolean contains(E element) {
		ListNode current = front;
		for (int i = 0; i < size; i++) {
			if (element.equals(current.data)) {
				return true;
			}
			current = current.next;
		}
		
		return false;
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
		
		ListNode current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		
		return current.data;
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	public int size() {
		return size;
	}
	
	/** 
	 * Inner class in SortedList for each node within in the list. Contains fields for the 
	 * data of each node and a reference to the next node in the list. Contains the constructor
	 * for a list node as well.
	 */
	public class ListNode {
		/** The data of a list node */
		public E data;
		/** The next list node after this one in a list */
		public ListNode next;
		
		/**
		 * Constructor of a list node given its data and the reference to the next node in the
		 * list.
		 * @param data of the list node
		 * @param next node in the list 
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
