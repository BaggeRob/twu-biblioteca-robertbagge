package com.twu.biblioteca.logic;

import com.twu.biblioteca.databasehandlers.UserHandler;
import com.twu.biblioteca.valueobjects.User;

public class UserDelegate {
    private final UserHandler userHandler = new UserHandler();
    User currentUser = null;

    public boolean userLogin(String libraryNumber, String password) {
        User user = userHandler.findUser(libraryNumber);
        if (user != null && user.validatePassword(password)) {
            startSession(user);
            return true;
        }
        return false;
    }

    void startSession(User user) {
        currentUser = user;
    }

    public boolean onGoingUserSession() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        if(currentUser != null){
            return currentUser;
        }else{
            return new User();
        }
    }
}