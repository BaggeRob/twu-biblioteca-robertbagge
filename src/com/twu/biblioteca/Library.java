package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by Robert on 29/07/15.
 */
public class Library {

    private final static boolean BOOK_IN_LIBRARY = true;
    private final static boolean BOOK_NOT_IN_LIBRARY = false;

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

    private int convertBookIdFromStringToInt(String bookIdString) throws InvalidBookIdException{
        try {
            int bookId = Integer.parseInt(bookIdString);
            return bookId;
        } catch (NumberFormatException e) {
            throw new InvalidBookIdException();
        }

    }

    public boolean loanBook(String bookIdString) throws InvalidBookIdException{
        return changeBookStatus(bookIdString, Library.BOOK_IN_LIBRARY, Library.BOOK_NOT_IN_LIBRARY);
    }


    public boolean returnBook(String bookIdString) throws InvalidBookIdException {
        return changeBookStatus(bookIdString, Library.BOOK_NOT_IN_LIBRARY, Library.BOOK_IN_LIBRARY);
    }

    private boolean changeBookStatus(String bookIdString, boolean statusFrom, boolean statusTo) throws InvalidBookIdException {
        int bookId = convertBookIdFromStringToInt(bookIdString);
        if(database.getBooksOnAvailability(statusFrom).contains(new Book("", "", "", bookId))){
            Book book = database.getBookById(bookId);
            book.setAvailability(statusTo);
            return true;
        }
        return false;

    }

    public class InvalidBookIdException extends Exception{

    }
}
