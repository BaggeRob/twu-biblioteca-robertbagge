package com.twu.biblioteca;

import com.twu.biblioteca.valueobjects.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by rbagge on 06/08/2015.
 */
public class MovieTest {
    Movie movie;

    @Before
    public void setUp(){
        movie = new Movie("Harry Potter", 33, "1999", "Steven", "2");
    }

    @After
    public void tearDown(){
        movie = null;
    }

    @Test
    public void testMovieName(){
        assertEquals("Harry Potter", movie.getName());
    }

    @Test
    public void testMovieId(){
        movie.setId(1);
        assertEquals(1, movie.getId());
    }

    @Test
    public void testMovieYear(){
        movie.setYear("2000");
        assertEquals("2000", movie.getYear());
    }

    @Test
    public void testNewMovieAvailability(){
        assertTrue(movie.isAvailable());
    }

    @Test
    public void testSetGetAvailability(){
        assertEquals(true, movie.isAvailable());
        movie.setAvailability(false);
        assertEquals(false, movie.isAvailable());
    }

    @Test
    public void testCreator(){
        assertEquals("Steven", movie.getCreator());
    }

    @Test
    public void testRating(){
        movie.setRating("1");
        assertEquals("1", movie.getRating());
        movie.setRating("10");
        assertEquals("10", movie.getRating());
        movie.setRating("Unrated");
        assertEquals("Unrated", movie.getRating());
        movie.setRating("-1");
        assertEquals("Unrated", movie.getRating());
        movie.setRating("11");
        assertEquals("Unrated", movie.getRating());
        movie.setRating("Not a Rating");
        assertEquals("Unrated", movie.getRating());
    }
}
