package com.twu.biblioteca;

/**
 * Created by rbagge on 06/08/2015.
 */
public class Movie extends Media{
    private String rating;

    public final static int MAX_RATING = 10;
    public final static int MIN_RATING = 1;
    public final static String UNRATED = "Unrated";

    public final static String MOVIE_FORMAT = "|%1$-10d|%2$-40s|%3$-25s|%4$-10s|%5$-10s|\n";
    public final static String MOVIE_FORMAT_FIELDS = "|%1$-10s|%2$-40s|%3$-25s|%4$-10s|%5$-10s|\n";

    public Movie(String name, int id, String year, String director, String rating) {
        super(name, id, year, director);
        setRating(rating);
    }

    public void setRating(String rating){
        try {
            int ratingInt = Integer.parseInt(rating);
            if(ratingInt >= Movie.MIN_RATING && ratingInt <= Movie.MAX_RATING){
                this.rating = Integer.toString(ratingInt);
            }else{
                this.rating = Movie.UNRATED;
            }
        } catch (NumberFormatException e) {
            this.rating = Movie.UNRATED;
        }
    }

    public String getRating(){
        return rating;
    }


    @Override
    public String toString() {
        return String.format(Movie.MOVIE_FORMAT, getId(), getName(), getCreator(), getYear(), getRating());
    }
}
