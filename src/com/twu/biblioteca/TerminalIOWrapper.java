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

    public final static String LIST_TERMINAL_MENU_OPTIONS = "List menu options";
    public final static String MENU_OPTION_LIST_BOOKS = "List Books";
    public final static String MENU_OPTION_QUIT = "Quit";
    public final static String MENU_OPTION_CHECKOUT = "Checkout";

    private Library library;

    public TerminalIOWrapper(Library library){
        this.library = library;
    }

    public String runCommand(String command) throws MenuException {
        if(command.equals(TerminalIOWrapper.LIST_TERMINAL_MENU_OPTIONS)){
            return this.listMenuOptions();
        }else if(command.equals(TerminalIOWrapper.MENU_OPTION_LIST_BOOKS)){
            return library.listAllBooks();
        }else if(command.equals(TerminalIOWrapper.MENU_OPTION_QUIT)){
            throw new MenuException();
        }else if(command.substring(0, 8).equals(TerminalIOWrapper.MENU_OPTION_CHECKOUT)){
            return "Checkout available book 0 successful";
        }else{
            return TerminalIOWrapper.VALID_OPTION_MESSAGE;
        }

    }

    public String listMenuOptions(){
        String menu = "Choose one of the following menu options: \n";
        menu += TerminalIOWrapper.MENU_OPTION_LIST_BOOKS + "\n";
        menu += TerminalIOWrapper.MENU_OPTION_QUIT;
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
                System.out.println(this.runCommand(command));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MenuException e) {
            System.out.println(TerminalIOWrapper.QUIT_MESSAGE);
            System.exit(0);
        }
    }
    public class MenuException extends Exception{

    }
}
