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
        String allBooks = "";

        for(Book book: database.getAllBooks()){
            allBooks += book.getName() + System.lineSeparator();
        }
        return allBooks;
    }
}
