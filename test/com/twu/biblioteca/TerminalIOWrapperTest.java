package com.twu.biblioteca;

import com.sun.xml.internal.xsom.impl.Ref;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
        } catch (TerminalIOWrapper.UserInducedQuitException e) {
            e.printStackTrace();
        } catch (TerminalIOWrapper.InvalidMenuOptionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listBooksTest(){
        try {
            assertEquals(library.listBooks(), terminalIOWrapper.runCommand(TerminalIOWrapper.MENU_OPTION_LIST_BOOKS));
        } catch (TerminalIOWrapper.UserInducedQuitException e) {
            fail();
        } catch (TerminalIOWrapper.InvalidMenuOptionException e) {
            fail();
        } catch (Library.InvalidMediaTypeException e) {
            fail();
        }
    }

    @Test
    public void listMoviesTest(){
        try {
            assertEquals(library.listMovies(), terminalIOWrapper.runCommand(TerminalIOWrapper.MENU_OPTION_LIST_MOVIES));
        } catch (TerminalIOWrapper.UserInducedQuitException e) {
            e.printStackTrace();
            fail();
        } catch (TerminalIOWrapper.InvalidMenuOptionException e) {
            e.printStackTrace();
            fail();
        } catch (Library.InvalidMediaTypeException e) {
            fail();
        }
    }


    @Test
    public void invalidMenuOptionTest(){
        try {
//            assertEquals(TerminalIOWrapper.VALID_OPTION_MESSAGE, terminalIOWrapper.runCommand("Nothing serious at all"));
            terminalIOWrapper.runCommand("Nothing serious at all");
            fail();
        } catch (TerminalIOWrapper.UserInducedQuitException e) {
            fail();
        } catch (TerminalIOWrapper.InvalidMenuOptionException e) {
        }
    }

    @Test
    public void checkoutBook(){
        try {
            terminalIOWrapper.runCommand("Checkout Book 3");
        } catch (TerminalIOWrapper.UserInducedQuitException e) {
            fail();
        } catch (TerminalIOWrapper.InvalidMenuOptionException e) {

        }

        try {

            assertEquals(TerminalIOWrapper.SUCCESSFUL_LOGIN_MESSAGE, terminalIOWrapper.runCommand("Login 123-4567 pass123-4567"));
            assertEquals(TerminalIOWrapper.SUCCESSFUL_CHECKOUT_MESSAGE, terminalIOWrapper.runCommand("Checkout Book 3"));
            assertEquals(TerminalIOWrapper.UNSUCCESFUL_CHECKOUT_MESSAGE, terminalIOWrapper.runCommand("Checkout Book 3"));
        } catch (TerminalIOWrapper.UserInducedQuitException e) {
            fail();
            e.printStackTrace();
        } catch (TerminalIOWrapper.InvalidMenuOptionException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void returnBook(){

        try {
            terminalIOWrapper.runCommand("Login 123-4567 pass123-4567");
            assertEquals(TerminalIOWrapper.UNSUCCESFUL_RETURN_MESSAGE, terminalIOWrapper.runCommand("Return Book 5"));
            assertEquals(TerminalIOWrapper.SUCCESSFUL_CHECKOUT_MESSAGE, terminalIOWrapper.runCommand("Checkout Book 5"));
            assertEquals(TerminalIOWrapper.SUCCESSFUL_RETURN_MESSAGE, terminalIOWrapper.runCommand("Return Book 5"));
        } catch (TerminalIOWrapper.UserInducedQuitException e) {
            fail();
        } catch (TerminalIOWrapper.InvalidMenuOptionException e) {
            fail();
        }
    }

    @Test
    public void checkoutMovie(){
        try {
            terminalIOWrapper.runCommand("Login 123-4567 pass123-4567");
            assertEquals(TerminalIOWrapper.SUCCESSFUL_CHECKOUT_MESSAGE, terminalIOWrapper.runCommand("Checkout Movie 5"));
            assertEquals(TerminalIOWrapper.UNSUCCESFUL_CHECKOUT_MESSAGE, terminalIOWrapper.runCommand("Checkout Movie 5"));
        } catch (TerminalIOWrapper.UserInducedQuitException e) {
            fail();
            e.printStackTrace();
        } catch (TerminalIOWrapper.InvalidMenuOptionException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void returnMovie(){
        try {
            terminalIOWrapper.runCommand("Return Movie 7");
        } catch (TerminalIOWrapper.UserInducedQuitException e) {
            fail();
        } catch (TerminalIOWrapper.InvalidMenuOptionException e) {

        }

        try {
            terminalIOWrapper.runCommand("Login 123-4567 pass123-4567");
            assertEquals(TerminalIOWrapper.UNSUCCESFUL_RETURN_MESSAGE, terminalIOWrapper.runCommand("Return Movie 7"));
            assertEquals(TerminalIOWrapper.SUCCESSFUL_CHECKOUT_MESSAGE, terminalIOWrapper.runCommand("Checkout Movie 7"));
            assertEquals(TerminalIOWrapper.SUCCESSFUL_RETURN_MESSAGE, terminalIOWrapper.runCommand("Return Movie 7"));
        } catch (TerminalIOWrapper.UserInducedQuitException e) {
            fail();
        } catch (TerminalIOWrapper.InvalidMenuOptionException e) {
            fail();
        }
    }



    @Test
    public void testUserLogin(){
        try {
            assertEquals(TerminalIOWrapper.SUCCESSFUL_LOGIN_MESSAGE, terminalIOWrapper.runCommand("Login 123-4567 pass123-4567"));
            assertEquals(TerminalIOWrapper.UNSUCCESFUL_LOGIN_MESSAGE, terminalIOWrapper.runCommand("Login 123-4567 passfas123-4567"));
        } catch (TerminalIOWrapper.UserInducedQuitException e) {
            fail();
        } catch (TerminalIOWrapper.InvalidMenuOptionException e) {
            fail();
        }
    }

}
