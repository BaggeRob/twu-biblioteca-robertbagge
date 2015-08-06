package com.twu.biblioteca;

/**
 * Created by Robert on 29/07/15.
 */


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LibraryTest {

    Library library;

    @Before
    public void setUp(){
        library = new Library();
    }

    @After
    public void tearDown(){
        library = null;
    }

    @Test
    public void testAllBooksSize(){
        assertEquals(20, library.numberOfBooks());
    }

    @Test
    public void testLoanBook(){
        try {
            assertTrue(library.listBooks().contains("Don Quixote"));
            System.out.println(library.listBooks());
            assertTrue(library.loanBook("3"));
            assertFalse(library.listBooks().contains("Don Quixote"));
            System.out.println(library.listBooks());
            assertTrue(library.loanBook("9"));
            assertFalse(library.listBooks().contains("The Divine Comedy"));
            assertFalse(library.loanBook("9"));
            System.out.println(library.listBooks());

            assertFalse(library.loanBook("10000"));
        } catch (Library.InvalidBookIdException e) {
            fail();
        }
    }

    @Test
    public void testReturnBook(){
        try {
            assertTrue(library.listBooks().contains("Hamlet"));
            assertFalse(library.returnBook("5"));
            assertTrue(library.loanBook("5"));
            assertFalse(library.listBooks().contains("Hamlet"));
            assertTrue(library.returnBook("5"));
            assertTrue(library.listBooks().contains("Hamlet"));
        }catch (Library.InvalidBookIdException e) {
            fail();
        }
    }

    @Test
    public void testListAllBooksNotNull(){
        assertNotNull(library.listBooks());
        System.out.println(library.listBooks());
    }

}
