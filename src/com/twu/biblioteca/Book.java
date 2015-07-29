package com.twu.biblioteca;

/**
 * Created by Robert on 29/07/15.
 */
public class Book {
    private String author;
    private String name;
    private String yearPublished;
    public boolean availability;


    public Book(String name){
        this(name, "Unknown");
    }

    public Book(String name, String author){
        this(name, author, "Unknown");
    }

    public Book(String name, String author, String yearPublished){
        this.name = name;
        this.availability = true;
        this.author = author;
        this.yearPublished = yearPublished;
    }


    public String getName() {
        return name;
    }

    public boolean setAvailability(boolean availability) {
        this.availability = availability;
        return availability;
    }

    public boolean getAvailability() {
        return availability;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getYearPublished(){
        return yearPublished;
    }
}
