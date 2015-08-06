package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Robert on 29/07/15.
 */
public class MockDatabaseTest {

    @Test
    public void testMockDatabaseSize(){
        assertEquals(20, new MockDatabase().getAllBooks().size());
    }

    @Test
    public void testGetBookById(){
        int id = 15;
        assertEquals(id, new MockDatabase().getBookById(id).getId());
    }

//    @Test
//    public void testMockDatabaseAvailableBooksSize(){
//        MockDatabase mb = new MockDatabase();
//        int availableSize = 0;
//        int unavailableSize = 0;
//        for(int i = 0; i < mb.getAllBooks().size(); i++){
//            if(mb.getAllBooks().get(i).isAvailable() == true){
//                availableSize++;
//                unavailableSize++;
//            }
//        }
//        assertEquals("available size wrong", 10, availableSize);
//        assertEquals("unavailable size wrong", 10, unavailableSize);
//    }

//    @Test
//    public void testBooksOnAvailability(){
//        MockDatabase mb = new MockDatabase();
//        assertEquals(10, mb.getBooksOnAvailability(true).size());
//        assertEquals(10, mb.getBooksOnAvailability(false).size());
//    }

//    @Test
//    public void testAvailableBooksGotNoUnavailable(){
//        MockDatabase mb = new MockDatabase();
//        ArrayList<Book> availableBooks = mb.getBooksOnAvailability(true);
//        for(Book book: availableBooks){
//            if(!book.isAvailable()){
//                fail();
//            }
//        }
//    }

}
