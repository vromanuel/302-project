package WisdomBites.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    // The class for connecting to the database

    // A variable for the connection is created
    private static Connection instance;

    // Initialises the connection to the database
    private DBConnection() {
        try {
            // Dynamic project path (so it would work on any computer)
            String projectDir = System.getProperty("user.dir");
            String dbPath = projectDir + "/javafx-reading-demo/WisdomBites.db";
            String url = "jdbc:sqlite:" + dbPath;

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
