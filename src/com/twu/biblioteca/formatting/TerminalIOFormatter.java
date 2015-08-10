package com.twu.biblioteca.formatting;

import com.twu.biblioteca.valueobjects.Book;
import com.twu.biblioteca.valueobjects.Media;
import com.twu.biblioteca.valueobjects.Movie;
import com.twu.biblioteca.valueobjects.User;

import java.util.ArrayList;

public class TerminalIOFormatter {

    public String formatListOfBooks(ArrayList<Media> booksToFormat){
        String headlineBooks = String.format(Book.BOOK_FORMAT_FIELDS, "Book Id", "Book Title", "Author", "Year Published");
        return headlineBooks + listMedia(booksToFormat);
    }

    public String formatListOfMovies(ArrayList<Media> moviesToFormat) {
        String headlineMovies = String.format(Movie.MOVIE_FORMAT_FIELDS, "Movie Id", "Movie Title", "Director", "Year", "Rating");
        return headlineMovies + listMedia(moviesToFormat);
    }

    private String listMedia(ArrayList<Media> mediaList) {
        String listOfMedia = "";
        for(Media media: mediaList){
            listOfMedia += media.toString();
        }
        return listOfMedia;
    }

    public String formatUserInformation(User user){
        return "User name: " + user.getName() + "\n" +
                "User e-mail: " + user.getEmail() + "\n" +
                "User phone number: " + user.getPhoneNumber() + "\n";

    }

}
