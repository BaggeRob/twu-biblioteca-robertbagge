package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.InvalidMediaIdException;
import com.twu.biblioteca.exceptions.InvalidMediaTypeException;

import java.util.ArrayList;

/**
 * Created by Robert on 29/07/15.
 */
public class Library {

    public final static String MEDIA_TYPE_BOOK = "Book";
    public final static String MEDIA_TYPE_MOVIE = "Movie";
    private final MediaHandler mediaHandler = new MediaHandler(this);

    private User currentUser = null;
    private MockDatabase database;

    public Library(){
        database = new MockDatabase();
    }

    public int numberOfBooks() {
        return database.getAllBooks().size();
    }

    public String listBooks(){
        String headlineBooks = String.format(Book.BOOK_FORMAT_FIELDS, "Book Id", "Book Title", "Author", "Year Published");
        return headlineBooks + listMedia(mediaHandler.getAvailableBooks());
    }

    public String listMovies() {
        String headlineMovies = String.format(Movie.MOVIE_FORMAT_FIELDS, "Movie Id", "Movie Title", "Director", "Year", "Rating");
        return headlineMovies + listMedia(mediaHandler.getAvailableMovies());
    }

    private String listMedia(ArrayList<Media> mediaList) {
        String listOfMedia = "";
        for(Media media: mediaList){
            listOfMedia += media.toString();
        }
        return listOfMedia;
    }
    private ArrayList<Media> getAvailableMovies() {

        return mediaHandler.getAvailableMovies();
    }

    private ArrayList<Media> getAvailableBooks() {

        return mediaHandler.getAvailableBooks();
    }

    public boolean returnBook(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return mediaHandler.returnBook(mediaId);
    }

    public boolean returnMovie(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return mediaHandler.returnMovie(mediaId);
    }

    public boolean loanBook(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return mediaHandler.loanBook(mediaId);
    }

    public boolean loanMovie(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return mediaHandler.loanMovie(mediaId);
    }

    public boolean userLogin(String libraryNumber, String password) {
        User user = database.findUser(libraryNumber);
        if(user != null && user.validatePassword(password)){
            startSession(user);
            return true;
        }
        return false;
    }

    private void startSession(User user) {
        currentUser = user;
    }

    public boolean onGoingUserSession(){
        return currentUser != null;
    }

    public String[] getCurrentUserInformation() {
        return new String[]{currentUser.getName(), currentUser.getEmail(), currentUser.getPhoneNumber()};
    }

    public MockDatabase getDatabase() {
        return database;
    }
}
