package WisdomBites.model;

import WisdomBites.controller.StateController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;

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

    public static boolean addTask(String title, String description, String completed)
    {
        try
        {

            String query = "INSERT INTO task (createdBy, dateCreated, title, description, completed) VALUES (?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, StateController.currentUser.getId());

            String date = LocalDate.now().toString();
            statement.setString(2, date);

            statement.setString(3, title);

            statement.setString(4, description);

            statement.setString(5, "F");

            statement.executeUpdate();


        } catch (Exception e)
        {
            return false;
        }
        return true;
    }



}
