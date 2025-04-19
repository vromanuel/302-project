package WisdomBites.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    // The instance of the connection to the database
    private static Connection instance = null;

    // Initialises the connection to the database
    private DBConnection() {
        // Url to database on local storage
        String url = "jdbc:sqlite:WisdomBites.db";

        // Attempt to get the connection
        try {
            instance = DriverManager.getConnection(url);
        } catch(SQLException exception)
        {
            System.err.println(exception);
        }

    }

    public static Connection getInstance() {
        // If the instance is null, a new DBConnection is initialised
        if (instance == null){
            new DBConnection();
        }
        return instance;
    }

}
