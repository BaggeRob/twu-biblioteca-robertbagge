package com.twu.biblioteca.IOwrappers;

import com.twu.biblioteca.logic.Library;
import com.twu.biblioteca.exceptions.InvalidMediaIdException;
import com.twu.biblioteca.formatting.TerminalIOFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by rbagge on 05/08/2015.
 */
public class TerminalIOWrapper {


    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca";
    public static final String VALID_OPTION_MESSAGE = "Select a valid option!";
    public static final String QUIT_MESSAGE = "Closing application";
    public static final String SUCCESSFUL_CHECKOUT_MESSAGE_BOOK = "Thank you! Enjoy the book";
    public static final String UNSUCCESFUL_CHECKOUT_MESSAGE_BOOK = "That book is not available.";
    public static final String SUCCESSFUL_RETURN_MESSAGE_BOOK = "Thank you for returning the book.";
    public static final String UNSUCCESFUL_RETURN_MESSAGE_BOOK = "That is not a valid book to return.";

    public static final String SUCCESSFUL_CHECKOUT_MESSAGE_MOVIE = "Thank you! Enjoy the movie";
    public static final String UNSUCCESFUL_CHECKOUT_MESSAGE_MOVIE = "That movie is not available.";
    public static final String SUCCESSFUL_RETURN_MESSAGE_MOVIE = "Thank you for returning the movie.";
    public static final String UNSUCCESFUL_RETURN_MESSAGE_MOVIE = "That is not a valid movie to return.";

    public static final String SUCCESSFUL_LOGIN_MESSAGE = "You are now logged in.";
    public static final String UNSUCCESFUL_LOGIN_MESSAGE = "Wrong user credentials.";

    public final static String LIST_TERMINAL_MENU_OPTIONS = "List menu options";
    public final static String MENU_OPTION_LIST_BOOKS = "List Books";
    public final static String MENU_OPTION_LIST_MOVIES = "List Movies";
    public final static String MENU_OPTION_QUIT = "Quit";
    public final static String MENU_OPTION_CHECKOUT = "Checkout";
    public final static String MENU_OPTION_RETURN = "Return";
    public final static String MENU_OPTION_LOGIN = "Login";
    public static final String MENU_OPTION_SHOW_USER_INFORMATION = "Show user information";


    private Library library;
    private TerminalIOFormatter formatter;

    public TerminalIOWrapper(Library library){
        this.library = library;
        this.formatter = new TerminalIOFormatter();
    }

    public String runCommand(String command) throws UserInducedQuitException, InvalidMenuOptionException {
        String commandAction = command.split(" ")[0];
        if(command.equals(TerminalIOWrapper.LIST_TERMINAL_MENU_OPTIONS)){
            return this.listMenuOptions();
        }else if(command.equals(TerminalIOWrapper.MENU_OPTION_LIST_BOOKS)){
            return listBooks();
        }else if(command.equals(TerminalIOWrapper.MENU_OPTION_QUIT)){
            throw new UserInducedQuitException();
        }else {
            if(commandAction.equals(TerminalIOWrapper.MENU_OPTION_CHECKOUT)){
                return checkoutMedia(command);
            }else if(commandAction.equals(TerminalIOWrapper.MENU_OPTION_RETURN)){
                return returnMedia(command);
            }else if(command.equals(TerminalIOWrapper.MENU_OPTION_LIST_MOVIES)){
                return listMovies();
            }else if(commandAction.equals(TerminalIOWrapper.MENU_OPTION_LOGIN)){
                return userLogin(command);
            }else if(command.equals(TerminalIOWrapper.MENU_OPTION_SHOW_USER_INFORMATION)){
                return printUserInformation();
            }else{
                throw new InvalidMenuOptionException();
            }
        }
    }

    private String printUserInformation() throws InvalidMenuOptionException {
        if(library.onGoingUserSession()) {
            return formatter.formatUserInformation(library.getCurrentUser());
        }else {
            throw new InvalidMenuOptionException();
        }

    }

    private String userLogin(String command) {
        String[] commandArgs = command.split(" ");
        String libraryNumber = commandArgs[1];
        String password = commandArgs[2];
        if(library.userLogin(libraryNumber, password)){
            return TerminalIOWrapper.SUCCESSFUL_LOGIN_MESSAGE;
        }else{
            return TerminalIOWrapper.UNSUCCESFUL_LOGIN_MESSAGE;
        }

    }

    public String listMenuOptions(){
        String menu = "Choose one of the following menu options: \n";
        if(!library.onGoingUserSession()){
            menu += menuItemsForNotLoggedInUsers();
        }else{
            menu += menuItemsForLoggedInUsers();
        }
        menu += menuItemsForAllUsers();
        return menu;
    }

    private String menuItemsForLoggedInUsers(){
        return TerminalIOWrapper.MENU_OPTION_SHOW_USER_INFORMATION + " - '" + TerminalIOWrapper.MENU_OPTION_SHOW_USER_INFORMATION +"'\n";
    }

    private String menuItemsForNotLoggedInUsers(){
        return TerminalIOWrapper.MENU_OPTION_LOGIN + " - ex: '" + TerminalIOWrapper.MENU_OPTION_LOGIN + " 123-4567 pass123-4567'\n";
    }

    private String menuItemsForAllUsers(){
        String menuItems = "";
        menuItems += TerminalIOWrapper.MENU_OPTION_LIST_BOOKS + " - '" + TerminalIOWrapper.MENU_OPTION_LIST_BOOKS + "'\n";
        menuItems += TerminalIOWrapper.MENU_OPTION_LIST_MOVIES + " - '" + TerminalIOWrapper.MENU_OPTION_LIST_MOVIES + "'\n";
        menuItems += TerminalIOWrapper.MENU_OPTION_CHECKOUT + " - ex: '" + TerminalIOWrapper.MENU_OPTION_CHECKOUT + " Movie 5' (<action> <media type> <media id>)\n";
        menuItems += TerminalIOWrapper.MENU_OPTION_RETURN + " - ex: '" + TerminalIOWrapper.MENU_OPTION_RETURN + " Book 11' (<action> <media type> <media id>)\n";
        menuItems += TerminalIOWrapper.MENU_OPTION_QUIT + " - 'Quit'";
        return menuItems;
    }

    private String listMovies() throws InvalidMenuOptionException {
        return formatter.formatListOfMovies(library.listMovies());
    }

    private String listBooks() throws InvalidMenuOptionException{
        return formatter.formatListOfBooks(library.listBooks());
    }

    private String checkoutMedia(String command) throws InvalidMenuOptionException{
        try {
            if(!library.onGoingUserSession()){
                throw new InvalidMenuOptionException();
            }
            String[] commandArgs = command.split(" ");
            String media_type = commandArgs[1];
            String mediaId = commandArgs[2];
            if(media_type.equals("Book")){
                return loanBook(mediaId);
            }else if(media_type.equals("Movie")){
                return loanMovie(mediaId);
            }
            throw new InvalidMenuOptionException();
        } catch (InvalidMediaIdException e) {
            throw new InvalidMenuOptionException();
        }
    }

    private String loanMovie(String mediaId) throws InvalidMediaIdException {
        if(library.loanMovie(mediaId)){
            return TerminalIOWrapper.SUCCESSFUL_CHECKOUT_MESSAGE_MOVIE;
        }else{
            return TerminalIOWrapper.UNSUCCESFUL_CHECKOUT_MESSAGE_MOVIE;
        }
    }

    private String loanBook(String mediaId) throws InvalidMediaIdException {
        if(library.loanBook(mediaId)){
            return TerminalIOWrapper.SUCCESSFUL_CHECKOUT_MESSAGE_BOOK;
        }else{
            return TerminalIOWrapper.UNSUCCESFUL_CHECKOUT_MESSAGE_BOOK;
        }
    }

    private String returnMedia(String command) throws InvalidMenuOptionException {
        try {
            if(!library.onGoingUserSession()){
                throw new InvalidMenuOptionException();
            }
            String[] commandArgs = command.split(" ");
            String media_type = commandArgs[1];
            String mediaId = commandArgs[2];
            if(media_type.equals("Book")){
                return returnBook(mediaId);
            }else if(media_type.equals("Movie")){
                return returnMovie(mediaId);
            }
            throw new InvalidMenuOptionException();
        } catch (InvalidMediaIdException e) {
            throw new InvalidMenuOptionException();
        }
    }

    private String returnMovie(String mediaId) throws InvalidMediaIdException {
        if(library.returnMovie(mediaId)){
            return TerminalIOWrapper.SUCCESSFUL_RETURN_MESSAGE_MOVIE;
        }else{
            return TerminalIOWrapper.UNSUCCESFUL_RETURN_MESSAGE_MOVIE;
        }
    }

    private String returnBook(String mediaId) throws InvalidMediaIdException {
        if(library.returnBook(mediaId)){
            return TerminalIOWrapper.SUCCESSFUL_RETURN_MESSAGE_BOOK;
        }else{
            return TerminalIOWrapper.UNSUCCESFUL_RETURN_MESSAGE_BOOK;
        }
    }

    public void run(){
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String command = null;
        System.out.println(TerminalIOWrapper.WELCOME_MESSAGE);
        System.out.println(this.listMenuOptions());
        try {
            while(true){
                command = inputReader.readLine();
                try {
                    System.out.println(this.runCommand(command));
                } catch (InvalidMenuOptionException e) {
                    System.out.println(TerminalIOWrapper.VALID_OPTION_MESSAGE);
                }

                System.out.println(this.listMenuOptions());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UserInducedQuitException e) {
            System.out.println(TerminalIOWrapper.QUIT_MESSAGE);
            System.exit(0);
        }
    }

    public class UserInducedQuitException extends Exception{

    }

    public class InvalidMenuOptionException extends Exception {
    }
}
