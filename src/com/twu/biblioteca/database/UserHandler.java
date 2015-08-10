package com.twu.biblioteca.database;

import com.twu.biblioteca.valueobjects.User;

import java.util.Collection;

public class UserHandler {

    public User findUser(String libraryNumber) {
        Collection<User> users = DatabaseHandler.getInstance().getDatabase().getAllUsers();
        for (User user : users) {
            if (user.equals(new User(libraryNumber, "...", "name", "email", "phone"))) {
                return user;
            }
        }
        return null;
    }
}