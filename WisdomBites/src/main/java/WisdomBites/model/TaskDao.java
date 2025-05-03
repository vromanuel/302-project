package WisdomBites.model;

import WisdomBites.controller.StateController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Task> getTasksByUser(int userID)
    {
        try
        {
            String query = "SELECT * FROM task WHERE createdBy = ? AND completed = ?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, userID);
            statement.setString(2, "F");

            ResultSet resultSet = statement.executeQuery();

            List<Task> toDoTasks = new ArrayList<Task>();


            while(resultSet.next())
            {
                int id = resultSet.getInt(1);
                int createdBy = resultSet.getInt(2);
                String dateCreated = resultSet.getString(3);
                String title = resultSet.getString(4);
                String description = resultSet.getString(5);
                String completed = resultSet.getString(6);
                Task task = new Task(createdBy, dateCreated, title, description, completed);
                task.setId(id);
                toDoTasks.add(task);
            }
            return toDoTasks;


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  null;
    }

    public static void completeTask(int id)
    {
        try
        {
            String query = "UPDATE task SET completed = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, "T");
            statement.setInt(2, id);

            statement.executeUpdate();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
