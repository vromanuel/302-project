package WisdomBites.model;

import java.sql.Connection;
import java.sql.Statement;

public class TaskDao {
    // Connection to the database
    private static Connection connection;


    public TaskDao() {
        // Initialise the Task Data Access object
        connection = DBConnection.getInstance();
        createTaskTable();
    }

    private void createTaskTable() {
        try {
            // Creates a table for Tasks with the appropriate attributes

            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS task ("
                    +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +"createdBy INTEGER,"
                    +"dateCreated TEXT NOT NULL,"
                    +"title TEXT NOT NULL,"
                    +"description TEXT NOT NULL,"
                    +"completed TEXT CHECK (completed IN ('T', 'F')),"
                    +"FOREIGN KEY (createdBy) REFERENCES user(id)"
                    +")";

            statement.execute(query);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean addTask()
    {
        try
        {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO task (createdBy, dateCreated, title, description, completed) VALUES (?,?,?,?,?)";


        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }


}
