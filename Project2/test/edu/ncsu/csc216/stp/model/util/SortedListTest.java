package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for SortedList class.
 */
public class SortedListTest {
	/**
	 * Tests the exceptions in the add method
	 */
	@Test
	public void testAddException() {
		SortedList<String> list = new SortedList<String>();
		
		Exception e = assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals("Cannot add null element.", e.getMessage());
		
		list.add("First");
		e = assertThrows(IllegalArgumentException.class, () -> list.add("First"));
		assertEquals("Cannot add duplicate element.", e.getMessage());
	}
	
	/**
	 * Tests the add method and ensures sorted order
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("Kendrick Lamar");
		assertEquals("Kendrick Lamar", list.get(0));
		assertEquals(1, list.size());
		list.add("Stevie Wonder");
		assertEquals("Kendrick Lamar", list.get(0));
		assertEquals("Stevie Wonder", list.get(1));
		assertEquals(2, list.size());
		list.add("Kid Cudi");
		assertEquals("Kendrick Lamar", list.get(0));
		assertEquals("Kid Cudi", list.get(1));
		assertEquals("Stevie Wonder", list.get(2));
		assertEquals(3, list.size());
		list.add("JPEGMAFIA");
		assertEquals("JPEGMAFIA", list.get(0));
		assertEquals("Kendrick Lamar", list.get(1));
		assertEquals("Kid Cudi", list.get(2));
		assertEquals("Stevie Wonder", list.get(3));
		assertEquals(4, list.size());
	}
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("JPEGMAFIA");
		list.add("Kendrick Lamar");
		list.add("Kid Cudi");
		list.add("Stevie Wonder");
		
		assertEquals("Kendrick Lamar", list.remove(1));
		assertEquals("JPEGMAFIA", list.get(0));
		assertEquals("Kid Cudi", list.get(1));
		assertEquals("Stevie Wonder", list.get(2));
		assertEquals(3, list.size());
		assertEquals("JPEGMAFIA", list.remove(0));
		assertEquals("Kid Cudi", list.get(0));
		assertEquals("Stevie Wonder", list.get(1));
		assertEquals(2, list.size());
		assertEquals("Stevie Wonder", list.remove(1));
		assertEquals("Kid Cudi", list.get(0));
		assertEquals(1, list.size());
		assertEquals("Kid Cudi", list.remove(0));
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests the check index method
	 */
	@Test
	public void testCheckIndex() {
		SortedList<String> list = new SortedList<String>();
		list.add("JPEGMAFIA");
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
		assertEquals("Invalid index.", e.getMessage());
		
		e = assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertEquals("Invalid index.", e.getMessage());
	}
	
	/**
	 * Tests the constructor of a ListNode
	 */
	@Test
	public void testListNodeConstructor() {
		SortedList<String> list = new SortedList<String>();
		list.add("JPEGMAFIA");
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
		assertEquals("Invalid index.", e.getMessage());
		
		e = assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertEquals("Invalid index.", e.getMessage());
	}
}
