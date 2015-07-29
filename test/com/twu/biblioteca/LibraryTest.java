package com.twu.biblioteca;

/**
 * Created by Robert on 29/07/15.
 */


import org.junit.Test;

import static org.junit.Assert.*;

public class LibraryTest {

    @Test
    public void testAllBooksSize(){
        assertEquals(20, new Library().numberOfBooks());
    }

    @Test
    public void testListAllBooksNotNull(){
        assertNotNull(new Library().listAllBooks());
        System.out.println(new Library().listAllBooks());
    }

}
