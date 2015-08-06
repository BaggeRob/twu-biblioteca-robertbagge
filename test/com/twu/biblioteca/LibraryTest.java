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
            assertTrue(library.loanMedia("3", Library.MEDIA_TYPE_BOOK));
            assertFalse(library.listBooks().contains("Don Quixote"));
            System.out.println(library.listBooks());
            assertTrue(library.loanMedia("9", Library.MEDIA_TYPE_BOOK));
            assertFalse(library.listBooks().contains("The Divine Comedy"));
            assertFalse(library.loanMedia("9", Library.MEDIA_TYPE_BOOK));
            System.out.println(library.listBooks());

            assertFalse(library.loanMedia("10000", Library.MEDIA_TYPE_BOOK));
        } catch (Library.InvalidMediaIdException e) {
            fail();
        } catch (Library.InvalidMediaTypeException e) {
            fail();
        }
    }

    @Test
    public void testReturnBook(){
        try {
            assertTrue(library.listBooks().contains("Hamlet"));
            assertFalse(library.returnMedia("5", Library.MEDIA_TYPE_BOOK));
            assertTrue(library.loanMedia("5", Library.MEDIA_TYPE_BOOK));
            assertFalse(library.listBooks().contains("Hamlet"));
            assertTrue(library.returnMedia("5", Library.MEDIA_TYPE_BOOK));
            assertTrue(library.listBooks().contains("Hamlet"));
        }catch (Library.InvalidMediaIdException e) {
            fail();
        } catch (Library.InvalidMediaTypeException e) {
            fail();
        }
    }

    @Test
    public void testListAllBooksNotNull(){
        try {
            assertNotNull(library.listBooks());
            System.out.println(library.listBooks());
        } catch (Library.InvalidMediaTypeException e) {
            fail();
        }

    }

}
