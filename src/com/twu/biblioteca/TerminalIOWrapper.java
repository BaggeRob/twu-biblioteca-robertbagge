package com.twu.biblioteca;

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
    public static final String SUCCESSFUL_CHECKOUT_MESSAGE = "Thank you! Enjoy the book";
    public static final String UNSUCCESFUL_CHECKOUT_MESSAGE = "That book is not available.";
    public static final String SUCCESSFUL_RETURN_MESSAGE = "Thank you for returning the book.";
    public static final String UNSUCCESFUL_RETURN_MESSAGE = "That is not a valid book to return.";

    public final static String LIST_TERMINAL_MENU_OPTIONS = "List menu options";
    public final static String MENU_OPTION_LIST_BOOKS = "List Books";
    public final static String MENU_OPTION_LIST_MOVIES = "List Movies";
    public final static String MENU_OPTION_QUIT = "Quit";
    public final static String MENU_OPTION_CHECKOUT = "Checkout";
    public final static String MENU_OPTION_RETURN = "Return";



    private Library library;

    public TerminalIOWrapper(Library library){
        this.library = library;
    }

    public String runCommand(String command) throws UserInducedQuitException, InvalidMenuOptionException {
        if(command.equals(TerminalIOWrapper.LIST_TERMINAL_MENU_OPTIONS)){
            return this.listMenuOptions();
        }else if(command.equals(TerminalIOWrapper.MENU_OPTION_LIST_BOOKS)){
            return listBooks();
        }else if(command.equals(TerminalIOWrapper.MENU_OPTION_QUIT)){
            throw new UserInducedQuitException();
        }else if(command.split(" ")[0].equals(TerminalIOWrapper.MENU_OPTION_CHECKOUT)){
            return checkoutMedia(command);
        }else if(command.split(" ")[0].equals(TerminalIOWrapper.MENU_OPTION_RETURN)){
            return returnMedia(command);
        }else if(command.equals(TerminalIOWrapper.MENU_OPTION_LIST_MOVIES)){
            return listMovies();
        }else{
            throw new InvalidMenuOptionException();
        }
    }


    public String listMenuOptions(){
        String menu = "Choose one of the following menu options: \n";
        menu += TerminalIOWrapper.MENU_OPTION_LIST_BOOKS + " - '" + TerminalIOWrapper.MENU_OPTION_LIST_BOOKS + "'\n";
        menu += TerminalIOWrapper.MENU_OPTION_LIST_MOVIES + " - '" + TerminalIOWrapper.MENU_OPTION_LIST_MOVIES + "'\n";
        menu += TerminalIOWrapper.MENU_OPTION_CHECKOUT + " - ex: '" + TerminalIOWrapper.MENU_OPTION_CHECKOUT + " Movie 5' (<action> <media type> <media id>)\n";
        menu += TerminalIOWrapper.MENU_OPTION_RETURN + " - ex: '" + TerminalIOWrapper.MENU_OPTION_RETURN + " Book 11' (<action> <media type> <media id>)\n";
        menu += TerminalIOWrapper.MENU_OPTION_QUIT + " - 'Quit'";
        return menu;
    }

    private String listMovies() throws InvalidMenuOptionException {
        try {
            return library.listMovies();
        } catch (Library.InvalidMediaTypeException e) {
            throw new InvalidMenuOptionException();
        }
    }

    private String listBooks() throws InvalidMenuOptionException{
        try {
            return library.listBooks();
        } catch (Library.InvalidMediaTypeException e) {
            throw new InvalidMenuOptionException();
        }
    }

    private String checkoutMedia(String command) throws InvalidMenuOptionException{
        try {
            String[] commandArgs = command.split(" ");
            String media_type = commandArgs[1];
            String mediaId = commandArgs[2];
            if(library.loanMedia(mediaId, media_type)){
                return TerminalIOWrapper.SUCCESSFUL_CHECKOUT_MESSAGE;
            }else{
                return TerminalIOWrapper.UNSUCCESFUL_CHECKOUT_MESSAGE;
            }
        } catch (Library.InvalidMediaIdException e) {
            throw new InvalidMenuOptionException();
        } catch (Library.InvalidMediaTypeException e) {
            throw new InvalidMenuOptionException();
        }
    }

    private String returnMedia(String command) throws InvalidMenuOptionException {
        try {
            String[] commandArgs = command.split(" ");
            String media_type = commandArgs[1];
            String mediaId = commandArgs[2];
            if(library.returnMedia(mediaId, media_type)){
                return TerminalIOWrapper.SUCCESSFUL_RETURN_MESSAGE;
            }else{
                return TerminalIOWrapper.UNSUCCESFUL_RETURN_MESSAGE;
            }
        } catch (Library.InvalidMediaIdException e) {
            throw new InvalidMenuOptionException();
        } catch (Library.InvalidMediaTypeException e) {
            throw new InvalidMenuOptionException();
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
