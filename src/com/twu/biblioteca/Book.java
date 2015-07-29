package com.twu.biblioteca;

/**
 * Created by Robert on 29/07/15.
 */
public class Book {
    private String name;
    public boolean availability;

    public Book(String name){
        this.name = name;
        availability = true;

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
}
