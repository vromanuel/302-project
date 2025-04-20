package WisdomBites.model;

import java.sql.Connection;
import java.sql.Statement;

public class TaskDao {
    // Connection to the database
    private static Connection connection;

    // Initialise the Task Data Access object
    public TaskDao() {
        connection = DBConnection.getInstance();
        createTaskTable();
    }

    private void createTaskTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS task ("
                    +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +"createdBy INTEGER,"
                    +"dateCreated INTEGER NOT NULL,"
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


}
