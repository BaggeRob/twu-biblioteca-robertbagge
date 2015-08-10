package com.twu.biblioteca.database;

import com.twu.biblioteca.valueobjects.Media;
import com.twu.biblioteca.valueobjects.User;

import java.util.ArrayList;

/**
 * Created by rbagge on 10/08/2015.
 */
public interface Database {

    public ArrayList<Media> getAllBooks();

    public ArrayList<Media> getAvailableBooks();

    public ArrayList<Media> getAvailableMovies();

    public Media getMediaById(int mediaId, String media_type);

    public Media getBookById(int bookId);

    public User findUser(String libraryNumber);
}
