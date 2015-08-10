package com.twu.biblioteca.databasehandlers;

import com.twu.biblioteca.logic.Library;
import com.twu.biblioteca.valueobjects.Media;

import java.util.ArrayList;
import java.util.Collection;

public class MediaHandler {


    public ArrayList<Media> getAvailableBooks() {
        ArrayList<Media> availableBooks = new ArrayList<Media>();
        for(Media media: DatabaseHandler.getInstance().getDatabase().getAllBooks()){
            if(media.isAvailable()){
                availableBooks.add(media);
            }
        }

        return availableBooks;
    }

    public ArrayList<Media> getAvailableMovies() {
        ArrayList<Media> availableMovies = new ArrayList<Media>();
        for(Media media: DatabaseHandler.getInstance().getDatabase().getAllMovies()){
            if(media.isAvailable()){
                availableMovies.add(media);
            }
        }

        return availableMovies;
    }

    Media getMediaByIdWithList(int mediaId, Collection<Media> mediaList) {
        for (Media media : mediaList) {
            if (media.getId() == mediaId) {
                return media;
            }
        }
        return null;
    }

    public Media getMediaById(int mediaId, String media_type) {
        if (media_type.equals(Library.MEDIA_TYPE_MOVIE)) {
            return getMediaByIdWithList(mediaId, DatabaseHandler.getInstance().getDatabase().getAllMovies());
        } else if (media_type.equals(Library.MEDIA_TYPE_BOOK)) {
            return getMediaByIdWithList(mediaId, DatabaseHandler.getInstance().getDatabase().getAllBooks());
        } else {
            return null;
        }
    }

    public Media getBookById(int bookId) {
        for (Media book : DatabaseHandler.getInstance().getDatabase().getAllBooks()) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }
}