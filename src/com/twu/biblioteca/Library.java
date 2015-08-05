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

    public String listAllBooks() {
        //String format = "|%1$-25s|%2$-25s|%3$-25s|\n";
        String allBooks = String.format(Book.BOOK_FORMAT, "Book Title", "Author", "Year Published");

        for(Book book: database.getAllBooks()){
            allBooks += book.toString();
        }
        return allBooks;
    }
}
