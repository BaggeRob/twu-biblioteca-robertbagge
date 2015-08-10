package com.twu.biblioteca.logic;

import com.twu.biblioteca.databasehandlers.MediaHandler;
import com.twu.biblioteca.exceptions.InvalidMediaIdException;
import com.twu.biblioteca.valueobjects.Media;

import java.util.ArrayList;

public class MediaDelegate {
    private final static boolean MEDIA_NOT_IN_LIBRARY = false;
    private final static boolean MEDIA_IN_LIBRARY = true;
    private final MediaHandler mediaHandler = new MediaHandler();


    public MediaDelegate() {
    }

    ArrayList<Media> getAvailableMovies() {
        return mediaHandler.getAvailableMovies();

    }

    ArrayList<Media> getAvailableBooks() {
        return mediaHandler.getAvailableBooks();

    }

    public boolean returnBook(String mediaId) throws InvalidMediaIdException {
        return changeBookStatus(mediaId, MediaDelegate.MEDIA_NOT_IN_LIBRARY, MediaDelegate.MEDIA_IN_LIBRARY);
    }

    public boolean returnMovie(String mediaId) throws InvalidMediaIdException {
        return changeMovieStatus(mediaId, MediaDelegate.MEDIA_NOT_IN_LIBRARY, MediaDelegate.MEDIA_IN_LIBRARY);
    }

    public boolean loanBook(String mediaId) throws InvalidMediaIdException {
        return changeBookStatus(mediaId, MediaDelegate.MEDIA_IN_LIBRARY, MediaDelegate.MEDIA_NOT_IN_LIBRARY);
    }

    public boolean loanMovie(String mediaId) throws InvalidMediaIdException {
        return changeMovieStatus(mediaId, MediaDelegate.MEDIA_IN_LIBRARY, MediaDelegate.MEDIA_NOT_IN_LIBRARY);
    }

    private boolean changeBookStatus(String mediaIdString, boolean statusFrom, boolean statusTo) throws InvalidMediaIdException {
        int mediaId = convertMediaIdFromStringToInt(mediaIdString);
        Media media = mediaHandler.getMediaById(mediaId, "Book");
        if (media != null && media.isAvailable() == statusFrom) {
            media.setAvailability(statusTo);
            return true;
        }
        return false;
    }

    private boolean changeMovieStatus(String mediaIdString, boolean statusFrom, boolean statusTo) throws InvalidMediaIdException {
        int mediaId = convertMediaIdFromStringToInt(mediaIdString);
        Media media = mediaHandler.getMediaById(mediaId, "Movie");
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