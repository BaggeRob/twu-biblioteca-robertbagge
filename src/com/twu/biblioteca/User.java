package com.twu.biblioteca;

/**
 * Created by rbagge on 06/08/2015.
 */
public class User {

    private String libraryNumber;
    private String passwordHash;
    private String name;
    private String email;
    private String phoneNumber;

    public User(String libraryNumber, String password, String name, String email, String phoneNumber){
        this.libraryNumber = libraryNumber;
        this.passwordHash = createPasswordHash(password);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
