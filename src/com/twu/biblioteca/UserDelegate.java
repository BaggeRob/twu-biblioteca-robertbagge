package com.twu.biblioteca;

import com.twu.biblioteca.database.DatabaseHandler;
import com.twu.biblioteca.valueobjects.User;

public class UserDelegate {
    private final Library library;
    User currentUser = null;

    public UserDelegate(Library library) {
        this.library = library;
    }

    public boolean userLogin(String libraryNumber, String password) {
        User user = DatabaseHandler.getInstance().getDatabase().findUser(libraryNumber);
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

    public String[] getCurrentUserInformation() {
        return new String[]{currentUser.getName(), currentUser.getEmail(), currentUser.getPhoneNumber()};
    }

    public User getCurrentUser() {
        if(currentUser != null){
            return currentUser;
        }else{
            return new User();
        }
    }
}