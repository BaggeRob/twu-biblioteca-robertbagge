package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by Robert on 29/07/15.
 */
public class Library {

    private MockDatabase database;

    public Library(){
        database = new MockDatabase();
    }

    public int numberOfBooks() {
        return database.getAllBooks().size();
    }

    public String listBooks() {



        String availableBooks = String.format(Book.BOOK_FORMAT_FIELDS, "Book Id", "Book Title", "Author", "Year Published");

        for(Book book: database.getBooksOnAvailability(true)){
            availableBooks += book.toString();
        }
        return availableBooks;
    }

    public boolean loanBook(int bookId) {
        if(database.getBooksOnAvailability(true).contains(new Book("", "", "", bookId))){
            Book book = database.getBookById(bookId);
            book.setAvailability(false);
            return true;
        }
        return false;
    }
}
