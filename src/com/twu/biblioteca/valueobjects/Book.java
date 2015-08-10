package com.twu.biblioteca.valueobjects;

/**
 * Created by Robert on 29/07/15.
 */
public class Book extends Media{
    public final static String BOOK_FORMAT = "|%1$-10d|%2$-40s|%3$-25s|%4$-25s|\n";
    public final static String BOOK_FORMAT_FIELDS = "|%1$-10s|%2$-40s|%3$-25s|%4$-25s|\n";

    public Book(String name, String author, String yearPublished, int bookId){
        super(name, bookId, yearPublished, author);

    }

    public String toString(){
        return String.format(Book.BOOK_FORMAT, this.getId(), this.getName(), this.getCreator(), this.getYear());
    }
}
