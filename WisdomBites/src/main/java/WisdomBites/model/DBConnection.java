package WisdomBites.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    // The class for connecting to the database

    // A variable for the connection is created
    private static Connection instance;

    private DBConnection() {
        // initialiser for the database connectioe

        // Gets the connection to the WisdomBites database using the URL
        String url = "jdbc:sqlite:WisdomBites.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch(SQLException exception)
        {
            System.err.println(exception);
        }

    }

    public static Connection getInstance() {
        // If there is no instance, a new DBConnection is initialised
//        blah blah blah
        if (instance == null){
            new DBConnection();
        }
        return instance;
    }

}