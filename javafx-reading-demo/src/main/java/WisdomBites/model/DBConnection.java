package WisdomBites.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static Connection instance = null;

    private DBConnection() {
        String url = "jdbc:sqlite:WisdomBites.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch(SQLException exception)
        {
            System.err.println(exception);
        }

    }

    public static Connection getInstance() {
        if (instance == null){
            new DBConnection();
        }
        return instance;
    }

}
