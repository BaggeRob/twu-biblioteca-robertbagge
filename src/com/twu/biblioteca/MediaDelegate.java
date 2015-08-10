package com.twu.biblioteca;

import com.twu.biblioteca.database.DatabaseHandler;
import com.twu.biblioteca.exceptions.InvalidMediaIdException;
import com.twu.biblioteca.exceptions.InvalidMediaTypeException;
import com.twu.biblioteca.valueobjects.Media;

import java.util.ArrayList;

public class MediaDelegate {
    private final static boolean MEDIA_NOT_IN_LIBRARY = false;
    private final static boolean MEDIA_IN_LIBRARY = true;
    private final Library library;


    public MediaDelegate(Library library) {
        this.library = library;
    }

    ArrayList<Media> getAvailableMovies() {
        return DatabaseHandler.getInstance().getDatabase().getAvailableMovies();

    }

    ArrayList<Media> getAvailableBooks() {
        return DatabaseHandler.getInstance().getDatabase().getAvailableBooks();

    }

    public boolean returnBook(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return changeBookStatus(mediaId, MediaDelegate.MEDIA_NOT_IN_LIBRARY, MediaDelegate.MEDIA_IN_LIBRARY);
    }

    public boolean returnMovie(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return changeMovieStatus(mediaId, MediaDelegate.MEDIA_NOT_IN_LIBRARY, MediaDelegate.MEDIA_IN_LIBRARY);
    }

    public boolean loanBook(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return changeBookStatus(mediaId, MediaDelegate.MEDIA_IN_LIBRARY, MediaDelegate.MEDIA_NOT_IN_LIBRARY);
    }

    public boolean loanMovie(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return changeMovieStatus(mediaId, MediaDelegate.MEDIA_IN_LIBRARY, MediaDelegate.MEDIA_NOT_IN_LIBRARY);
    }

    private boolean changeBookStatus(String mediaIdString, boolean statusFrom, boolean statusTo) throws InvalidMediaIdException, InvalidMediaTypeException {
        int mediaId = convertMediaIdFromStringToInt(mediaIdString);
        Media media = DatabaseHandler.getInstance().getDatabase().getMediaById(mediaId, "Book");
        if (media != null && media.isAvailable() == statusFrom) {
            media.setAvailability(statusTo);
            return true;
        }
        return false;
    }

    private boolean changeMovieStatus(String mediaIdString, boolean statusFrom, boolean statusTo) throws InvalidMediaIdException, InvalidMediaTypeException {
        int mediaId = convertMediaIdFromStringToInt(mediaIdString);
        Media media = DatabaseHandler.getInstance().getDatabase().getMediaById(mediaId, "Movie");
        if (media != null && media.isAvailable() == statusFrom) {
            media.setAvailability(statusTo);
            return true;
        }
        return false;
    }

    int convertMediaIdFromStringToInt(String mediaIdString) throws InvalidMediaIdException {
        try {
            int bookId = Integer.parseInt(mediaIdString);
            return bookId;
        } catch (NumberFormatException e) {
            throw new InvalidMediaIdException();
        }
    }
}