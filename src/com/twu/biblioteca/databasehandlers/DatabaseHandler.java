package com.twu.biblioteca.databasehandlers;

import com.twu.biblioteca.database.Database;
import com.twu.biblioteca.database.MockDatabase;

/**
 * Created by rbagge on 10/08/2015.
 */
public class DatabaseHandler {
    private static DatabaseHandler instance = null;
    private Database database;
    protected DatabaseHandler() {
        // Exists only to defeat instantiation.
    }
    public static DatabaseHandler getInstance() {
        if(instance == null) {
            instance = new DatabaseHandler();
            instance.setDatabase(new MockDatabase());
        }
        return instance;
    }

    public static void destroyInstance(){
        if(instance != null){
            instance = null;
        }
    }

    private void setDatabase(Database database) {
        this.database = database;
    }

    public Database getDatabase(){
        return database;
    }
}
