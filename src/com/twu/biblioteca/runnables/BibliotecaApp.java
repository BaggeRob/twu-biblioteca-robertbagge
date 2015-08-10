package com.twu.biblioteca.runnables;

import com.twu.biblioteca.IOwrappers.TerminalIOWrapper;
import com.twu.biblioteca.logic.Library;

public class BibliotecaApp {

    public static void main(String[] args) {
        Library library = new Library();
        TerminalIOWrapper terminalIOWrapper = new TerminalIOWrapper(library);

        terminalIOWrapper.run();
    }
}
