package com.twu.biblioteca.database;

import com.twu.biblioteca.*;
import com.twu.biblioteca.valueobjects.Book;
import com.twu.biblioteca.valueobjects.Media;
import com.twu.biblioteca.valueobjects.Movie;
import com.twu.biblioteca.valueobjects.User;

import java.util.ArrayList;

/**
 * Created by Robert on 29/07/15.
 */
public class MockDatabase implements Database {

    private ArrayList<Media> books;
    private ArrayList<Media> movies;
    private ArrayList<User> users;

    public MockDatabase(){
        books = createBooks();
        movies = createMovies();
        users = createUsers();
    }

    private ArrayList<User> createUsers() {
        ArrayList<User> tempUsers = new ArrayList<User>();
        tempUsers.add(new User("123-4567", "pass123-4567", "Robert Bagge", "robert.bagge@example.com", "0751234567"));
        tempUsers.add(new User("234-5678", "pass234-5678", "Test User1", "test.user1@example.com", "0752345678"));
        tempUsers.add(new User("345-6789", "pass345-6789", "Test User2", "test.user2@example.com", "0753456789"));
        tempUsers.add(new User("456-7890", "pass456-7890", "Test User3", "test.user3@example.com", "0754567890"));
        tempUsers.add(new User("567-8901", "pass567-8901", "Test User4", "test.user4@example.com", "0755678901"));

        return tempUsers;
    }

    private ArrayList<Media> createBooks(){
        ArrayList<Media> tempBooks = new ArrayList<Media>();

        tempBooks.add(new Book("In Search of Lost Time", "Marcel Proust", "1955", 1));
        tempBooks.add(new Book("Ulysses", "James Joyce", "1830", 2));
        tempBooks.add(new Book("Don Quixote", "Miguel de Cervantes", "1783", 3));
        tempBooks.add(new Book("Moby Dick", "Herman Melville", "1921", 4));
        tempBooks.add(new Book("Hamlet", "William Shakespeare", "1563", 5));
        tempBooks.add(new Book("War and Peace", "Leo Tolstoy", "1920", 6));
        tempBooks.add(new Book("The Odyssey", "Homer", "300 BC", 7));
        tempBooks.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "1983", 8));
        tempBooks.add(new Book("The Divine Comedy", "Dante Alighieri", "1467", 9));
        tempBooks.add(new Book("Madame Bovary", "Gustave Flaubert", "1963", 10));
        tempBooks.add(new Book("The Brothers Karamazov", "Fyodor Dostoyevsky", "1890", 11));
        tempBooks.add(new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", "1999", 12));
        tempBooks.add(new Book("The Adventures of Huckleberry Finn", "Mark Twain", "1930", 13));
        tempBooks.add(new Book("The Iliad", "Homer", "300 BC", 14));
        tempBooks.add(new Book("Lolita", "Vladimir Nabokov", "1860", 15));
        tempBooks.add(new Book("Anna Karenina", "Leo Tolstoy", "1840", 16));
        tempBooks.add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", "1914", 17));
        tempBooks.add(new Book("The Sound and the Fury", "William Faulkner", "1967", 18));
        tempBooks.add(new Book("Pride and Prejudice", "Jane Austen", "1937", 19));
        tempBooks.add(new Book("The Catcher in the Rye", "J. D. Salinger", "1979", 20));

        return tempBooks;
    }

    private ArrayList<Media> createMovies(){
        ArrayList<Media> tempMovies = new ArrayList<Media>();

        tempMovies.add(new Movie("Shawshank Redemption", 1, "1994", "Frank Darabont", "9"));
        tempMovies.add(new Movie("The Godfather", 2, "1972", "Francis Ford Coppola", "9"));
        tempMovies.add(new Movie("The Godfather:Part II", 3, "1974", "Francis Ford Coppola", "9"));
        tempMovies.add(new Movie("The Dark Knight", 4, "2008", "Christopher Nolan", "9"));
        tempMovies.add(new Movie("Citizen Kane", 5, "1941", "Orson Welles", "8"));
        tempMovies.add(new Movie("Rocky", 6, "1976", "Sylvester Stalone", "8"));
        tempMovies.add(new Movie("Spread", 7, "2009", "David Mackenzi", "6"));
        tempMovies.add(new Movie("Catch Me If You Can", 8, "2002", "Steven Spielberg", "8"));
        tempMovies.add(new Movie("The Human Centipede", 9, "2009", "Tom Six", "Unrated"));
        tempMovies.add(new Movie("Gladiator", 10, "2000", "Ridley Scott", "8"));

        return tempMovies;
    }

    public ArrayList<Media> getAllBooks(){
        return books;
    }

    public ArrayList<Media> getAvailableBooks() {
        ArrayList<Media> availableBooks = new ArrayList<Media>();
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).isAvailable() == true){
                availableBooks.add(books.get(i));
            }
        }

        return availableBooks;
    }

    public ArrayList<Media> getAvailableMovies() {
        ArrayList<Media> availableMovies = new ArrayList<Media>();
        for(int i = 0; i < movies.size(); i++){
            if(movies.get(i).isAvailable() == true){
                availableMovies.add(movies.get(i));
            }
        }

        return availableMovies;
    }


    private Media getMediaByIdWithList(int mediaId, ArrayList<Media> mediaList){
        for(Media media: mediaList){
            if(media.getId() == mediaId){
                return media;
            }
        }
        return null;
    }

    public Media getMediaById(int mediaId, String media_type) {
        if(media_type.equals(Library.MEDIA_TYPE_MOVIE)){
            return this.getMediaByIdWithList(mediaId, movies);
        }else if(media_type.equals(Library.MEDIA_TYPE_BOOK)){
            return this.getMediaByIdWithList(mediaId, books);
        }else{
            return null;
        }
    }



    public Media getBookById(int bookId) {
        for(Media book: books){
            if(book.getId() == bookId){
                return book;
            }
        }
        return null;
    }

    public User findUser(String libraryNumber) {
        for(User user: users){
            if(user.equals(new User(libraryNumber, "...", "name", "email", "phone"))){
                return user;
            }
        }
        return null;
    }
}
