package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by Robert on 29/07/15.
 */
public class MockDatabase {

    private ArrayList<Book> books;

    public MockDatabase(){
        books = createBooks();
    }

    public ArrayList<Book> getAllBooks(){
        return books;
    }

    private ArrayList<Book> createBooks(){
        ArrayList<Book> tempBooks = new ArrayList<Book>();

        for(int i = 0; i < 10; i++){
            Book unavailableBook = new Book("unaivalable book " + i);
            Book availableBook = new Book("available book " + i);
            unavailableBook.setAvailability(false);
            tempBooks.add(unavailableBook);
            tempBooks.add(availableBook);
        }

        return tempBooks;
    }

    public ArrayList<Book> getBooksOnAvailability(boolean requestedAvailability) {
        ArrayList<Book> availableBooks = new ArrayList<Book>();
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getAvailability() == requestedAvailability){
                availableBooks.add(books.get(i));
            }
        }

        return availableBooks;
    }
}
