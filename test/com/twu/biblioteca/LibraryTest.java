package com.twu.biblioteca;

/**
 * Created by Robert on 29/07/15.
 */


import com.twu.biblioteca.exceptions.InvalidMediaIdException;
import com.twu.biblioteca.exceptions.InvalidMediaTypeException;
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
    public void testLoanBook(){
        try {
            assertTrue(library.loanBook("3"));
            assertFalse(library.loanBook("3"));
            assertTrue(library.loanBook("9"));
            assertFalse(library.loanBook("9"));

            assertFalse(library.loanBook("10000"));
        } catch (InvalidMediaIdException e) {
            fail();
        } catch (InvalidMediaTypeException e) {
            fail();
        }
    }

    @Test
    public void testReturnBook(){
        try {
            assertFalse(library.returnBook("5"));
            assertTrue(library.loanBook("5"));
            assertTrue(library.returnBook("5"));
        }catch (InvalidMediaIdException e) {
            fail();
        } catch (InvalidMediaTypeException e) {
            fail();
        }
    }

    @Test
    public void testListAllBooksNotNull(){
        assertNotNull(library.listBooks());
    }

    @Test
    public void testUserLogin(){
        assertTrue(library.userLogin("123-4567", "pass123-4567"));
        assertFalse(library.userLogin("123-4567", "pass123-456712421"));
    }

}
