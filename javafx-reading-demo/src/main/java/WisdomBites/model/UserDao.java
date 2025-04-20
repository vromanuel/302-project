package WisdomBites.model;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao
{
    // Connection to the database
    private static Connection connection;

    // Initialise the User Data Access object
    public UserDao() {
        // Gets the instance of the database connection
        connection = DBConnection.getInstance();
        createUserTable();
    }

    private void createUserTable() {
        // Creates a table to store user data if the table does not exist
        try {
            // Create a statement with an SQL query
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS user ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "userName VARCHAR NOT NULL UNIQUE,"
                    + "passWord VARCHAR NOT NULL"
                    + ")";

            // Execute the statement with the query
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String registerUser() {
        // Adds a new user to the table of users

        try
        {
            // SQL to insert the user's firstName, lastName, userName, and passWord
            String insertQuery = "INSERT INTO user (firstName, lastName, userName, passWord) VALUES (?,?,?,?)";

            // Creates a new PreparedStatement
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            // Add the values to be inserted into the table into the query where appropriate
            statement.setString(1, "Manny");
            statement.setString(2, "Go");
            statement.setString(3, "manny");
            statement.setString(4, "123");

            statement.executeUpdate();
        } catch(Exception e)
        {
            // Return the exception message
            return e.getMessage();
        }
        return "User successful!";
    }

    public static User login(String userNameSubmitted, String passWordSubmitted) {
        // Authenticate the username and password for the user, check if the password entered matches that of
        // the password stored in the database

        try{
            // SQL code to retrieve the userName entered by the user
            String query = "SELECT FROM user WHERE userName = ?";

            // Create a new PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);


            statement.setString(1, userNameSubmitted);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String userName = resultSet.getString(4);
                String passWord = resultSet.getString(5);
                User loggedInUser = new User(firstName, lastName, userName, passWord);
                loggedInUser.setId(id);
                return loggedInUser;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
