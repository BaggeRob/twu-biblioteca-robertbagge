package com.twu.biblioteca.logic;

import com.twu.biblioteca.exceptions.InvalidMediaIdException;
import com.twu.biblioteca.valueobjects.Media;
import com.twu.biblioteca.valueobjects.User;

import java.util.ArrayList;

/**
 * Created by Robert on 29/07/15.
 */
public class Library {

    public final static String MEDIA_TYPE_BOOK = "Book";
    public final static String MEDIA_TYPE_MOVIE = "Movie";
    private final MediaDelegate mediaDelegate = new MediaDelegate();
    private final UserDelegate userDelegate = new UserDelegate();

    public ArrayList<Media> listBooks(){
        return mediaDelegate.getAvailableBooks();
    }

    public ArrayList<Media> listMovies(){
        return mediaDelegate.getAvailableMovies();
    }

    public boolean returnBook(String mediaId) throws InvalidMediaIdException {
        return mediaDelegate.returnBook(mediaId);
    }

    public boolean returnMovie(String mediaId) throws InvalidMediaIdException {
        return mediaDelegate.returnMovie(mediaId);
    }

    public boolean loanBook(String mediaId) throws InvalidMediaIdException {
        return mediaDelegate.loanBook(mediaId);
    }

    public boolean loanMovie(String mediaId) throws InvalidMediaIdException {
        return mediaDelegate.loanMovie(mediaId);
    }

    public boolean userLogin(String libraryNumber, String password) {
        return userDelegate.userLogin(libraryNumber, password);
    }

    public boolean onGoingUserSession(){
        return userDelegate.onGoingUserSession();
    }

    public User getCurrentUser() {
        return userDelegate.getCurrentUser();
    }
}
