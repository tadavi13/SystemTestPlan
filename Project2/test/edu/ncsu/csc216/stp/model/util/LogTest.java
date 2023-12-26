package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for the Log class.
 */
public class LogTest {
	/**
	 * Tests the exceptions in the add method
	 */
	@Test
	public void testAddException() {
		Log<String> list = new Log<String>();
		
		Exception e = assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals("Cannot add null element.", e.getMessage());
	}
	
	/**
	 * Tests the add method
	 */
	@Test
	public void testAdd() {
		Log<String> list = new Log<String>();
		
		list.add("First");
		assertEquals("First", list.get(0));
		assertEquals(1, list.size());
		list.add("Second");
		assertEquals("First", list.get(0));
		assertEquals("Second", list.get(1));
		assertEquals(2, list.size());
	}
	
	/**
	 * Tests the exceptions in the get method
	 */
	@Test
	public void testGetException() {
		Log<String> list = new Log<String>();
		list.add("First");
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertEquals("Invalid index.", e.getMessage());
		e = assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
		assertEquals("Invalid index.", e.getMessage());
	}
	
	/**
	 * Tests the get method
	 */
	@Test
	public void testGet() {
		Log<String> list = new Log<String>();
		
		list.add("First");
		list.add("Second");
		assertEquals("First", list.get(0));
		assertEquals("Second", list.get(1));
		assertEquals(2, list.size());
	}
	
	/**
	 * Tests the add method when size reaches capacity
	 */
	@Test
	public void testAddSizeEqualsCapacity() {
		Log<String> list = new Log<String>();
		
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		assertEquals("0", list.get(0));
		assertEquals("1", list.get(1));
		assertEquals("2", list.get(2));
		assertEquals("3", list.get(3));
		assertEquals("4", list.get(4));
		assertEquals("5", list.get(5));
		assertEquals("6", list.get(6));
		assertEquals("7", list.get(7));
		assertEquals("8", list.get(8));
		assertEquals("9", list.get(9));
		assertEquals(10, list.size());
		list.add("10");
		assertEquals("10", list.get(10));
		assertEquals(11, list.size());
	}
}
