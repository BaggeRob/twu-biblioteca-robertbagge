package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BibliotecaAppTest {

    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void testWelcomeMessage() {
        assertEquals("Welcome to Biblioteca", BibliotecaApp.WELCOME_MESSAGE);
    }
}
