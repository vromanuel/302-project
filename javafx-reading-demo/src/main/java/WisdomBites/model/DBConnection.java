package WisdomBites.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    // The instance of the connection to the database
    private static Connection instance = null;

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
        // If the instance is null, a new DBConnection is initialised
        if (instance == null){
            new DBConnection();
        }
        return instance;
    }

}
