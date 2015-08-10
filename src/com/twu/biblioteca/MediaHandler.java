package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.InvalidMediaIdException;
import com.twu.biblioteca.exceptions.InvalidMediaTypeException;

import java.util.ArrayList;

public class MediaHandler {
    private final static boolean MEDIA_NOT_IN_LIBRARY = false;
    private final static boolean MEDIA_IN_LIBRARY = true;
    private final Library library;


    public MediaHandler(Library library) {
        this.library = library;
    }

    ArrayList<Media> getAvailableMovies() {
        return library.getDatabase().getMoviesOnAvailability(true);

    }

    ArrayList<Media> getAvailableBooks() {
        return library.getDatabase().getBooksOnAvailability(true);

    }

    public boolean returnBook(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return changeBookStatus(mediaId, MediaHandler.MEDIA_NOT_IN_LIBRARY, MediaHandler.MEDIA_IN_LIBRARY);
    }

    public boolean returnMovie(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return changeMovieStatus(mediaId, MediaHandler.MEDIA_NOT_IN_LIBRARY, MediaHandler.MEDIA_IN_LIBRARY);
    }

    public boolean loanBook(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return changeBookStatus(mediaId, MediaHandler.MEDIA_IN_LIBRARY, MediaHandler.MEDIA_NOT_IN_LIBRARY);
    }

    public boolean loanMovie(String mediaId) throws InvalidMediaTypeException, InvalidMediaIdException {
        return changeMovieStatus(mediaId, MediaHandler.MEDIA_IN_LIBRARY, MediaHandler.MEDIA_NOT_IN_LIBRARY);
    }

    private boolean changeBookStatus(String mediaIdString, boolean statusFrom, boolean statusTo) throws InvalidMediaIdException, InvalidMediaTypeException {
        int mediaId = convertMediaIdFromStringToInt(mediaIdString);
        Media media = library.getDatabase().getMediaById(mediaId, "Book");
        if (media != null && media.isAvailable() == statusFrom) {
            media.setAvailability(statusTo);
            return true;
        }
        return false;
    }

    private boolean changeMovieStatus(String mediaIdString, boolean statusFrom, boolean statusTo) throws InvalidMediaIdException, InvalidMediaTypeException {
        int mediaId = convertMediaIdFromStringToInt(mediaIdString);
        Media media = library.getDatabase().getMediaById(mediaId, "Movie");
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