package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        Library library = new Library();
        TerminalIOWrapper terminalIOWrapper = new TerminalIOWrapper(library);

        terminalIOWrapper.run();
    }
}
