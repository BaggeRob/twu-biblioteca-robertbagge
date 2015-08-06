package com.twu.biblioteca;

/**
 * Created by rbagge on 06/08/2015.
 */
public class User {

    private String libraryNumber;
    private String passwordHash;
    public User(String libraryNumber, String password){
        this.libraryNumber = libraryNumber;
        this.passwordHash = createPasswordHash(password);
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public boolean equals(Object obj){
        return getLibraryNumber().equals(((User)obj).getLibraryNumber());
    }

    private String createPasswordHash(String password){
        return password;
    }


    public boolean validatePassword(String password) {
        return this.passwordHash.equals(createPasswordHash(password));
    }
}
