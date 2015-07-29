package com.twu.biblioteca;

public class BibliotecaApp {


    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca";

    public static void main(String[] args) {
        Library library = new Library();
        System.out.println(BibliotecaApp.WELCOME_MESSAGE);

        System.out.println(library.listAllBooks());
    }
}
