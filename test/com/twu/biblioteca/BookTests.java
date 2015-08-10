package com.twu.biblioteca;

/**
 * Created by Robert on 29/07/15.
 */

import com.twu.biblioteca.valueobjects.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookTests {
    Book book;

    @Before
    public void setUp(){
        book = new Book("Harry Potter", "JK Rowling", "1999", 11);
    }

    @After
    public void tearDown(){
        book = null;
    }

    @Test
    public void testBookName(){
        assertEquals("Harry Potter", book.getName());
    }

    @Test
    public void testSetAvailability(){
        assertEquals(false, book.setAvailability(false));
        assertEquals(true, book.setAvailability(true));
    }

    @Test
    public void testGetAvailability(){
        assertEquals(true, book.isAvailable());
        book.setAvailability(false);
        assertEquals(false, book.isAvailable());
    }

    @Test
    public void testAuthor(){
        assertEquals("JK Rowling", book.getCreator());
    }

    @Test
    public void testYearPublished(){
        assertEquals("1999", book.getYear());
    }

    @Test
    public void testBookId(){
        assertEquals(11, book.getId());
    }

    @Test
    public void testEquals(){
        assertEquals(true, new Book("How to kill a mockingbird", "Harper Lee", "1960", 12).equals(new Book("How to kill a mockingbird", "Harper Lee", "1960", 12)));
        assertEquals(false, new Book("How to kill a mockingbird", "Harper Lee", "1960", 11).equals(new Book("How to kill a mockingbird", "Harper Lee", "1960", 12)));

    }

}
