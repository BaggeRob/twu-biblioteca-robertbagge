package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by Robert on 29/07/15.
 */
public class Library {

    private final static boolean MEDIA_IN_LIBRARY = true;
    private final static boolean MEDIA_NOT_IN_LIBRARY = false;

    public final static String MEDIA_TYPE_BOOK = "Book";
    public final static String MEDIA_TYPE_MOVIE = "Movie";

    private User currentUser = null;
    private MockDatabase database;

    public Library(){
        database = new MockDatabase();
    }

    public int numberOfBooks() {
        return database.getAllBooks().size();
    }

    public String listBooks() throws InvalidMediaTypeException {
        String headlineBooks = String.format(Book.BOOK_FORMAT_FIELDS, "Book Id", "Book Title", "Author", "Year Published");
        return headlineBooks + listMedia(Library.MEDIA_TYPE_BOOK);
    }

    public String listMovies() throws InvalidMediaTypeException {
        String headlineMovies = String.format(Movie.MOVIE_FORMAT_FIELDS, "Movie Id", "Movie Title", "Director", "Year", "Rating");
        return headlineMovies + listMedia(Library.MEDIA_TYPE_MOVIE);
    }

    private String listMedia(String mediaType) throws InvalidMediaTypeException {
        String listOfMedia = "";
        ArrayList<Media> mediaList = getAvailableMediaForMediaType(mediaType);
        for(Media media: mediaList){
            listOfMedia += media.toString();
        }
        return listOfMedia;
    }

    public boolean returnMedia(String mediaId, String mediaType) throws InvalidMediaIdException, InvalidMediaTypeException{
        return changeMediaStatus(mediaId, mediaType, Library.MEDIA_NOT_IN_LIBRARY, Library.MEDIA_IN_LIBRARY);
    }

    public boolean loanMedia(String mediaId, String mediaType) throws InvalidMediaIdException, InvalidMediaTypeException{
        return changeMediaStatus(mediaId, mediaType, Library.MEDIA_IN_LIBRARY, Library.MEDIA_NOT_IN_LIBRARY);
    }

    private boolean changeMediaStatus(String mediaIdString, String mediaType, boolean statusFrom, boolean statusTo) throws InvalidMediaIdException, InvalidMediaTypeException {
        validateMediaType(mediaType);
        int mediaId = convertMediaIdFromStringToInt(mediaIdString);
        Media media = database.getMediaById(mediaId, mediaType);
        if(media != null && media.isAvailable() == statusFrom){
            media.setAvailability(statusTo);
            return true;
        }
        return false;
    }

    private void validateMediaType(String mediaType) throws InvalidMediaTypeException{
        if(!mediaType.equals(Library.MEDIA_TYPE_MOVIE) && !mediaType.equals(Library.MEDIA_TYPE_BOOK)){
            throw new InvalidMediaTypeException();
        }

    }

    private int convertMediaIdFromStringToInt(String mediaIdString) throws InvalidMediaIdException {
        try {
            int bookId = Integer.parseInt(mediaIdString);
            return bookId;
        } catch (NumberFormatException e) {
            throw new InvalidMediaIdException();
        }
    }

    public ArrayList<Media> getAvailableMediaForMediaType(String mediaType) throws InvalidMediaTypeException {
        validateMediaType(mediaType);
        if(mediaType.equals(Library.MEDIA_TYPE_MOVIE)){
            return database.getMoviesOnAvailability(true);
        }else if(mediaType.equals(Library.MEDIA_TYPE_BOOK)){
            return  database.getBooksOnAvailability(true);
        }else{
            return new ArrayList<Media>();
        }
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

    public class InvalidMediaIdException extends Exception{

    }

    public class InvalidMediaTypeException extends Exception{

    }
}
