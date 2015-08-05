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

    public final static String LIST_TERMINAL_MENU_OPTIONS = "List menu options";
    public final static String MENU_OPTION_LIST_BOOKS = "List Books";
    public final static String MENU_OPTION_QUIT = "Quit";
    public final static String MENU_OPTION_CHECKOUT = "Checkout";

    private Library library;

    public TerminalIOWrapper(Library library){
        this.library = library;
    }

    public String runCommand(String command) throws UserInducedQuitException, InvalidMenuOptionException {
        if(command.equals(TerminalIOWrapper.LIST_TERMINAL_MENU_OPTIONS)){
            return this.listMenuOptions();
        }else if(command.equals(TerminalIOWrapper.MENU_OPTION_LIST_BOOKS)){
            return library.listBooks();
        }else if(command.equals(TerminalIOWrapper.MENU_OPTION_QUIT)){
            throw new UserInducedQuitException();
        }else if(commandIsCheckoutCommand(command)){
            return checkoutBook(command);
        }else{
            throw new InvalidMenuOptionException();
        }

    }

    public String listMenuOptions(){
        String menu = "Choose one of the following menu options: \n";
        menu += TerminalIOWrapper.MENU_OPTION_LIST_BOOKS + " - 'List Books'\n";
        menu += TerminalIOWrapper.MENU_OPTION_CHECKOUT + " - 'Checkout <book_id>'\n";
        menu += TerminalIOWrapper.MENU_OPTION_QUIT + " - 'Quit'";
        return menu;
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

    private boolean commandIsCheckoutCommand(String command) throws InvalidMenuOptionException{
        try{
            return command.substring(0, 8).equals(TerminalIOWrapper.MENU_OPTION_CHECKOUT);
        }catch(StringIndexOutOfBoundsException e){
            throw new InvalidMenuOptionException();
        }

    }

    private String prepareCommandForCheckout(String command){
        return command.substring(8).trim();
    }

    private String checkoutBook(String command) throws InvalidMenuOptionException{
        try {
            String stringBookId = prepareCommandForCheckout(command);
            int bookId = Integer.parseInt(stringBookId);
            if(library.loanBook(bookId)){
                return TerminalIOWrapper.SUCCESSFUL_CHECKOUT_MESSAGE;
            }else{
                return TerminalIOWrapper.UNSUCCESFUL_CHECKOUT_MESSAGE;
            }
        } catch (NumberFormatException e) {
            throw new InvalidMenuOptionException();
        }
    }

    public class UserInducedQuitException extends Exception{

    }

    public class InvalidMenuOptionException extends Exception {
    }
}
