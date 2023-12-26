package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for SwapList class.
 */
public class SwapListTest {
	/**
	 * Tests the exceptions in the add method
	 */
	@Test
	public void testAddException() {
		SwapList<String> list = new SwapList<String>();
		
		Exception e = assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals("Cannot add null element.", e.getMessage());
	}
	
	/**
	 * Tests the add method
	 */
	@Test
	public void testAdd() {
		SwapList<String> list = new SwapList<String>();
		
		list.add("First");
		assertEquals("First", list.get(0));
		assertEquals(1, list.size());
		list.add("Second");
		assertEquals("First", list.get(0));
		assertEquals("Second", list.get(1));
		assertEquals(2, list.size());
	}
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		SwapList<String> list = new SwapList<String>();
		
		list.add("First");
		list.add("Second");
		list.add("Third");
		
		list.remove(2);
		assertEquals("First", list.get(0));
		assertEquals("Second", list.get(1));
		assertEquals(2, list.size());
		list.remove(1);
		assertEquals("First", list.get(0));
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests the exceptions in the add method
	 */
	@Test
	public void testCheckIndex() {
		SwapList<String> list = new SwapList<String>();
		list.add("First");
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertEquals("Invalid index.", e.getMessage());
		e = assertThrows(IndexOutOfBoundsException.class, () -> list.moveToFront(2));
		assertEquals("Invalid index.", e.getMessage());
	}
	
	/**
	 * Tests the move up method
	 */
	@Test
	public void testMoveUp() {
		SwapList<String> list = new SwapList<String>();
		
		list.add("First");
		list.add("Second");
		list.add("Third");
		
		list.moveUp(2);
		assertEquals("First", list.get(0));
		assertEquals("Third", list.get(1));
		assertEquals("Second", list.get(2));
		assertEquals(3, list.size());
		list.moveUp(1);
		assertEquals("Third", list.get(0));
		assertEquals("First", list.get(1));
		assertEquals("Second", list.get(2));
		assertEquals(3, list.size());
		list.moveUp(0);
		assertEquals("Third", list.get(0));
		assertEquals("First", list.get(1));
		assertEquals("Second", list.get(2));
		assertEquals(3, list.size());
	}
	
	/**
	 * Tests the move down method
	 */
	@Test
	public void testMoveDown() {
		SwapList<String> list = new SwapList<String>();
		
		list.add("First");
		list.add("Second");
		list.add("Third");
		
		list.moveDown(0);
		assertEquals("Second", list.get(0));
		assertEquals("First", list.get(1));
		assertEquals("Third", list.get(2));
		assertEquals(3, list.size());
		list.moveDown(1);
		assertEquals("Second", list.get(0));
		assertEquals("Third", list.get(1));
		assertEquals("First", list.get(2));
		assertEquals(3, list.size());
		list.moveDown(2);
		assertEquals("Second", list.get(0));
		assertEquals("Third", list.get(1));
		assertEquals("First", list.get(2));
		assertEquals(3, list.size());
	}
	
	/**
	 * Tests the move to front method
	 */
	@Test
	public void testMoveToFront() {
		SwapList<String> list = new SwapList<String>();
		
		list.add("First");
		list.add("Second");
		list.add("Third");
		
		list.moveToFront(0);
		assertEquals("First", list.get(0));
		assertEquals("Second", list.get(1));
		assertEquals("Third", list.get(2));
		assertEquals(3, list.size());
		list.moveToFront(1);
		assertEquals("Second", list.get(0));
		assertEquals("First", list.get(1));
		assertEquals("Third", list.get(2));
		assertEquals(3, list.size());
		list.moveToFront(2);
		assertEquals("Third", list.get(0));
		assertEquals("Second", list.get(1));
		assertEquals("First", list.get(2));
		assertEquals(3, list.size());
	}
	
	/**
	 * Tests the move to back method
	 */
	@Test
	public void testMoveToBack() {
		SwapList<String> list = new SwapList<String>();
		
		list.add("First");
		list.add("Second");
		list.add("Third");
		
		list.moveToBack(0);
		assertEquals("Second", list.get(0));
		assertEquals("Third", list.get(1));
		assertEquals("First", list.get(2));
		assertEquals(3, list.size());
		list.moveToBack(1);
		assertEquals("Second", list.get(0));
		assertEquals("First", list.get(1));
		assertEquals("Third", list.get(2));
		assertEquals(3, list.size());
		list.moveToBack(2);
		assertEquals("Second", list.get(0));
		assertEquals("First", list.get(1));
		assertEquals("Third", list.get(2));
		assertEquals(3, list.size());
	}
}
