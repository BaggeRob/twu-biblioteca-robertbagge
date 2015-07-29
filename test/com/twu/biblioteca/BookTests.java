package com.twu.biblioteca;

/**
 * Created by Robert on 29/07/15.
 */

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookTests {

    @Test
    public void testBookName(){
        assertEquals("Harry Potter", new Book("Harry Potter").getName());
    }

    @Test
    public void testNewBookAvailability(){
        assertTrue(new Book("Hairy P").availability);
    }

    @Test
    public void testSetAvailability(){
        assertEquals(false, new Book("Duncan T").setAvailability(false));
        assertEquals(true, new Book("Duncan T").setAvailability(true));
    }

    @Test
    public void testGetAvailability(){
        Book book = new Book("Night Cab");
        assertEquals(true, book.getAvailability());
        book.setAvailability(false);
        assertEquals(false, book.getAvailability());
    }

}
