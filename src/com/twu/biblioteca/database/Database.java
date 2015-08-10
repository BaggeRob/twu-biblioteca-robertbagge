package com.twu.biblioteca.database;

import com.twu.biblioteca.valueobjects.Book;
import com.twu.biblioteca.valueobjects.Media;
import com.twu.biblioteca.valueobjects.Movie;
import com.twu.biblioteca.valueobjects.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by rbagge on 10/08/2015.
 */
public interface Database {

    public Collection<User> getAllUsers();
    public Collection<Media> getAllBooks();
    public Collection<Media> getAllMovies();
}
