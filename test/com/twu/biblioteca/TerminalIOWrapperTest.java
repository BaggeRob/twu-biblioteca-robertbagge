package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by rbagge on 05/08/2015.
 */
public class TerminalIOWrapperTest {

    private Library library;
    private TerminalIOWrapper terminalIOWrapper;

    @Before
    public void setUp(){
        library = new Library();
        terminalIOWrapper = new TerminalIOWrapper(library);
    }

    @After
    public void tearDown(){
        library = null;
    }

    @Test
    public void listMenuOptionsTest() {
        try {
            assertEquals(terminalIOWrapper.listMenuOptions(), terminalIOWrapper.runCommand(TerminalIOWrapper.LIST_TERMINAL_MENU_OPTIONS));
        } catch (TerminalIOWrapper.MenuException e) {
            e.printStackTrace();
        }
        System.out.println(terminalIOWrapper.listMenuOptions());
    }

    @Test
    public void listBooksTest(){
        try {
            assertEquals(library.listAllBooks(), terminalIOWrapper.runCommand(TerminalIOWrapper.MENU_OPTION_LIST_BOOKS));
        } catch (TerminalIOWrapper.MenuException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void invalidMenuOptionTest(){
        try {
            assertEquals(TerminalIOWrapper.VALID_OPTION_MESSAGE, terminalIOWrapper.runCommand("Nothing serious at all"));
        } catch (TerminalIOWrapper.MenuException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkoutBookValidBook(){
        try {
            assertEquals("Checkout available book 0 successful", terminalIOWrapper.runCommand("Checkout available book 0"));
        } catch (TerminalIOWrapper.MenuException e) {
            e.printStackTrace();
        }

    }




}
