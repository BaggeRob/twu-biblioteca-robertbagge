package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by Robert on 29/07/15.
 */
public class MockDatabase {

    private ArrayList<Book> books;

    public MockDatabase(){
        books = createBooks();
    }

    public ArrayList<Book> getAllBooks(){
        return books;
    }

    private ArrayList<Book> createBooks(){
        ArrayList<Book> tempBooks = new ArrayList<Book>();

/*        for(int i = 0; i < 10; i++){
            Book unavailableBook = new Book("unaivalable book " + i);
            Book availableBook = new Book("available book " + i);
            unavailableBook.setAvailability(false);
            tempBooks.add(unavailableBook);
            tempBooks.add(availableBook);
        }*/

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

    public ArrayList<Book> getBooksOnAvailability(boolean requestedAvailability) {
        ArrayList<Book> availableBooks = new ArrayList<Book>();
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getAvailability() == requestedAvailability){
                availableBooks.add(books.get(i));
            }
        }

        return availableBooks;
    }
}
